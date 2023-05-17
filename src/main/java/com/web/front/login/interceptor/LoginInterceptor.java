package com.web.front.login.interceptor;

import com.web.front.login.LoginConstant;
import com.web.front.login.service.LoginService;
import com.web.front.mypage.vo.CustVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    LoginService loginService;

    public static final String IS_MOBILE = "MOBILE";
    public static final String IS_PC = "PC";

    public static boolean isDevice(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent").toUpperCase();

        if(userAgent.indexOf(IS_MOBILE) > -1) {
            return true;
        } else
            return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(LoginConstant.LOGIN_SESSION);

        if(obj == null) {

            if(!LoginInterceptor.isDevice(request)) {
                Cookie loginCookie = WebUtils.getCookie(request, LoginConstant.LOGIN_COOKIE);
                if (loginCookie != null) {
                    String sessionId = loginCookie.getValue();

                    CustVO vo = new CustVO();
                    vo.setSessionId(sessionId);
                    CustVO cvo = loginService.getCustInfo(vo);
                    if (cvo != null) {
                        cvo.setAutoLogin("Y");
                        loginService.login(request, response, cvo);
                        return true;
                    } else {
                        response.sendRedirect("/front.login.loginView.dp/proc.go");
                        return false;
                    }
                } else {
                    response.sendRedirect("/front.login.loginView.dp/proc.go");
                    return false;
                }
            } else {
                return true;
            }
        }

        return true;

    }
}
