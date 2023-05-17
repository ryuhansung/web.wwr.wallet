package com.web.front.login.service.impl;

import com.web.front.lending.service.LendingService;
import com.web.front.lending.vo.LendingVO;
import com.web.front.login.LoginConstant;
import com.web.front.login.dao.LoginDAO;
import com.web.front.login.interceptor.LoginInterceptor;
import com.web.front.login.service.LoginService;
import com.web.front.login.vo.ForeignIpVO;
import com.web.front.login.vo.LoginHistoryVO;
import com.web.front.mypage.vo.CustVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDAO loginDAO;

    @Autowired
    LendingService lendingService;

    @Override
    public CustVO getCustInfo(CustVO vo) throws Exception {
        return loginDAO.getCustInfo(vo);
    }

    @Override
    public void updateSessionData(CustVO vo) throws Exception {
        loginDAO.updateSessionData(vo);
    }

    @Override
    public void updateSessionDataMobile(CustVO vo) throws Exception {
        loginDAO.updateSessionDataMobile(vo);
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response, CustVO vo) throws Exception {
        if("Y".equals(vo.getAutoLogin())) {

            int time = 60 * 60 * 24 * 365; // 365 일

            if(!LoginInterceptor.isDevice(request)) {
                Cookie cookie = new Cookie(LoginConstant.LOGIN_COOKIE, request.getSession().getId());
                cookie.setPath("/");
                cookie.setMaxAge(time);
                response.addCookie(cookie);
            }

            Calendar c = Calendar.getInstance();
            Date date = new Date(System.currentTimeMillis());
            c.setTime(date);
            c.add(Calendar.YEAR, 1);

            if(LoginInterceptor.isDevice(request)) {
                System.out.println("login sid >> " + request.getSession().getId());
                vo.setSessionIdMobile(request.getSession().getId());
                vo.setSessionTimeMobile(c.getTime());
                updateSessionDataMobile(vo);
            } else {
                vo.setSessionId(request.getSession().getId());
                vo.setSessionTime(c.getTime());
                updateSessionData(vo);
            }
        } else {
            if(!LoginInterceptor.isDevice(request)) {
                Cookie loginCookie = WebUtils.getCookie(request, LoginConstant.LOGIN_COOKIE);
                if (loginCookie != null) {
                    loginCookie.setPath("/");
                    loginCookie.setMaxAge(0);
                }
            }

            if(LoginInterceptor.isDevice(request)) {
                System.out.println("not auto login sid >> " + request.getSession().getId());

                vo.setSessionIdMobile("");
                vo.setSessionTimeMobile(new Date(System.currentTimeMillis()));
                updateSessionDataMobile(vo);
            } else {
                vo.setSessionId("");
                vo.setSessionTime(new Date(System.currentTimeMillis()));
                updateSessionData(vo);
            }
        }

        CustVO nvo = new CustVO();
        nvo.setUserEmail(vo.getUserEmail());
        CustVO cvo = getCustInfo(nvo);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(LoginConstant.LOGIN_SESSION, cvo);

    }

    @Override
    public void logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        Object obj = session.getAttribute(LoginConstant.LOGIN_SESSION);
        if(obj != null) {
            CustVO vo = (CustVO)obj;

            if(!LoginInterceptor.isDevice(request)) {
                Cookie loginCookie = WebUtils.getCookie(request, LoginConstant.LOGIN_COOKIE);
                if (loginCookie != null) {
                    loginCookie.setPath("/");
                    loginCookie.setMaxAge(0);
                }

                vo.setSessionId("");
                vo.setSessionTime(new Date(System.currentTimeMillis()));

                updateSessionData(vo);

            } else {
                vo.setSessionIdMobile("");
                vo.setSessionTimeMobile(new Date(System.currentTimeMillis()));

                updateSessionDataMobile(vo);
            }
        }

        session.removeAttribute(LoginConstant.LOGIN_SESSION);
        session.invalidate();
    }


    @Override
    public void historyInsert(LoginHistoryVO vo) throws Exception{
        loginDAO.historyInsert(vo);
    }

    @Override
    public boolean isKoreaYn(String ip) throws Exception {
        /*
        191205임승연추가
        접속 IP 가 한국일경우 return true
        아닐경우  return false
        default true
        */
        boolean koreaYn = true;
        String status = "";
        ForeignIpVO fvo = new ForeignIpVO();
        fvo = loginDAO.getForeignIpCheck(ip);
        if(null == fvo){
            try {
                HttpURLConnection urlcon = (HttpURLConnection) new URL("https://ip2c.org/" + ip).openConnection();
                urlcon.setDefaultUseCaches(false);
                urlcon.setUseCaches(false);
                urlcon.setConnectTimeout(60000);
                urlcon.connect();

                InputStream is = urlcon.getInputStream();
                int c = 0; // 결과값; 0일때는 잘못된것 / 1일때 나라값  / 2일때는 알수없는 IP 값일때
                String s = "";
                while ((c = is.read()) != -1) s += (char) c;
                is.close();// inputStream close
                switch (s.charAt(0)) {
                    case '0':
                        return koreaYn;
                    case '1':
                        String[] reply = s.split(";"); // 결과 예시 1;KR;republic of korea   -- 첫번째는 조회성공 ; 국가코드 ; 국가풀네임
                        if ("KR".equals(reply[1])) {
                            koreaYn = true;
                            status = "N";
                        } else { //한국이 아닐때만 외국이라고 리턴
                            koreaYn = false;
                            status = "Y";
                        }

                        ForeignIpVO avo = new ForeignIpVO();
                        avo.setIp(ip);
                        avo.setInternalYn(status);
                        loginDAO.insertCheckIp(avo);

                        return koreaYn;
                    case '2':
                        return koreaYn;
                }
            } catch (Exception e) {
                e.getMessage();
                koreaYn = false;
            }
        }else{
            if("N".equals(fvo.getInternalYn())){
                koreaYn = true;
            }else{
                koreaYn = false;
            }
        }
        return koreaYn;
    }
}
