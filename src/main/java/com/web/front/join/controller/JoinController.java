package com.web.front.join.controller;

import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.init.WebConfig;
import com.web.config.mail.MailSenderImpl;
import com.web.config.service.CmeProperties;
import com.web.config.util.ComUtil;
import com.web.config.util.StringUtils;
import com.web.config.vo.MailInfoVO;
import com.web.front.danal.service.DanalAuthService;
import com.web.front.join.JoinConstant;
import com.web.front.join.service.JoinService;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import com.web.front.login.LoginConstant;
import com.web.front.login.service.LoginService;
import com.web.front.login.util.LUtil;
import com.web.front.mail.service.MailService;
import com.web.front.mypage.service.MypageService;
import com.web.front.mypage.vo.CustVO;
import com.web.front.mail.vo.mailHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/front/join/")
public class JoinController extends CmeDefaultExtendController {

    @Autowired
    LoginService loginService;

    @Autowired
    MypageService mypageService;

    @Autowired
    JoinService joinService;

    @Autowired
    MailSenderImpl mailSender;

    @Autowired
    MailService mailService;

    @Autowired
    DanalAuthService danalAuthService;

    LUtil lUtil = LUtil.getInstance();

    @RequestMapping(value="/joinView.dm")
    public ModelAndView newJoin(CustVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{

        vo.setLang(getLocale(request));

        ModelAndView mv = new ModelAndView("/front/join/joinView");
        return mv;

    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : mailOverlap
     * @작성일 :
     * @작성자 :
     * @변경이력 :
     * @Method 설명 :메일중복체크
     */
    @RequestMapping(value = "/mailOverlap.dm")
    public @ResponseBody
    CmeResultVO mailOverlap(@ModelAttribute CustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmeResultVO res = new CmeResultVO();

        CustVO basic = loginService.getCustInfo(vo); // 넘어온 email로 회원정보 검색
        if (basic == null) {
            res.setResultCode(1);
            res.setResultMsg(getLocale(request, "TEXT_389"));
            request.getSession().setAttribute(JoinConstant.JOIN_OVERLAP_ID, vo.getUserEmail());
        } else {  //메일을 안보낸사람
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_390"));
            request.getSession().setAttribute(JoinConstant.JOIN_OVERLAP_ID, "N");
        }
        return res;
    }

    @RequestMapping(value = "/sendOkMail.dm")
    public @ResponseBody
    CmeResultVO sendOkMail(@ModelAttribute JoinAuthVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmeResultVO res = new CmeResultVO();
        String lang = getLocale(request);

        String overLap = (String) request.getSession().getAttribute(JoinConstant.JOIN_OVERLAP_ID);
        if (overLap == null || !vo.getUserEmail().equals(overLap)) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_380"));
            return res;
        }

        MailInfoVO mail = new MailInfoVO();
        mail.setMailFrom(WebConfig.getInstance().MAIL_FROM);
        mail.setMailTo(vo.getUserEmail());
        mail.setMailSubject(getLocale(request, "TEXT_391"));
        mail.setMailTemplateForm("signup_confirm.html");
        mail.setOriMail(vo.getUserEmail());

        Map<String, Object> map = new HashMap<String, Object>();
        String hostUrl = request.getScheme() + "://" + request.getServerName();
        if (request.getServerName().indexOf("localhost") > -1) {
            hostUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        }

        map.put("userEmail", vo.getUserEmail()); //메일에 들어가야할 내용
        map.put("hostUrl", hostUrl);
        map.put("lang", getLocale(request));

        String _logo = CmeProperties.getProperty("MAIL.LOGO.IMG");

        map.put("imgUrl", _logo);
        map.put("content1", getLocale(request, "TEXT_380"));
        map.put("content2", getLocale(request, "02_03"));
        map.put("content3", getLocale(request, "TEXT_42"));
        map.put("content4", "Copyright (c) 2023 RNR Wallet. All rights reserved.");

        mail.setModel(map);
        res = mailSender.sendEmail(mail); //메일전송

        if(res.getResultCode() > 0 ) { //메일전송까지 성공했을경우
            res.setResultCode(1);
            res.setResultMsg(getLocale(request, "TEXT_393"));

            JoinVO avo = new JoinVO();
            avo.setUserEmail(vo.getUserEmail());
            JoinAuthVO resvo = joinService.selectUserJoinAuth(avo);
            vo.setEmailCertYn("N");
            vo.setSmsCertYn("N");
            vo.setRegEmail(vo.getUserEmail());
            if(resvo == null) {
                joinService.insertCertInfo(vo);
            } else {
                joinService.uptUserJoinAuth(vo);
            }
        } else {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_386"));
        }

        return res;

    }

    @RequestMapping(value = "/mailAuthConfirm.dm")
    public @ResponseBody
    ModelAndView mailAuthConfirm(@ModelAttribute mailHistoryVO histVo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmeResultVO res = new CmeResultVO();

        mailHistoryVO mailInfo = mailService.getMailHis(histVo);
        if(null == mailInfo){
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_394"));
            model.addAttribute("res", res);
            return new ModelAndView("/front/join/authEndView");
        }
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date sendDate = transFormat.parse(mailInfo.getSndDt()); //메일발송일시

        Calendar calRsv = new GregorianCalendar(Locale.KOREA);
        Calendar calToday = Calendar.getInstance(); // 지금시각
        calRsv.setTime(sendDate);
        calRsv.add(Calendar.HOUR, 24);
        if (calToday.getTime().compareTo(calRsv.getTime()) > 0) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_395"));
        }else{
            JoinVO jvo = new JoinVO();
            jvo.setUserEmail(histVo.getUserEmail());
            JoinAuthVO avo = joinService.selectUserJoinAuth(jvo);

            if("Y".equals(avo.getEmailCertYn()) || "S".equals(avo.getEmailCertYn())) { // 이미 인증한 상태인 경우
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_396"));
            } else {

                avo.setEmailCertYn("S"); // 발송전 N , 메일에서 확인 클릭시 S , 화면에서 인증확인 Y
                avo.setUptEmail(avo.getUserEmail());
                joinService.uptUserJoinAuth(avo);

                res.setResultCode(1);
                res.setResultMsg(getLocale(request, "TEXT_397"));
            }
        }

        model.addAttribute("res", res);
        return new ModelAndView("/front/join/authEndView");
    }


    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : mailAuthEnd
     * @작성일 :
     * @작성자 :  (주)씨엠이소프트
     * @변경이력 :
     * @Method 설명 :메일인증 완료
     */
    @RequestMapping(value = "/mailAuthEnd.dm")
    public @ResponseBody
    CmeResultVO mailAuthEnd(@ModelAttribute JoinVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmeResultVO res = new CmeResultVO();

        JoinAuthVO avo = joinService.selectUserJoinAuth(vo);
        if ("N".equals(avo.getEmailCertYn().trim())) { // 메일에서 확인 안한사람
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_398"));
        } else if (avo == null || "".equals(avo.getEmailCertYn().trim())) {  //메일을 안보낸사람
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_381"));
        } else {
            avo.setEmailCertYn("Y");// 발송전 N , 메일에서 확인 클릭시 S , 화면에서 인증확인 Y
            avo.setUptEmail(avo.getUserEmail());
            joinService.uptUserJoinAuth(avo);

            res.setResultCode(1);
            res.setResultMsg(getLocale(request, "TEXT_397"));
            request.getSession().setAttribute(JoinConstant.MAIL_AUTH_CHECK, vo.getUserEmail());
        }

        return res;
    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : userInsert
     * @작성일 :
     * @작성자 :  (주)씨엠이소프트
     * @변경이력 :
     * @Method 설명 : userInsert
     */
    @RequestMapping(value = "/userInsert.dm")
    public @ResponseBody
    CmeResultVO userInsert(@ModelAttribute JoinVO vo, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        CmeResultVO ResultVO = new CmeResultVO();// 결과 보낼곳

        CustVO cvo = new CustVO();
        cvo.setUserEmail(vo.getUserEmail());
        CustVO basic = loginService.getCustInfo(cvo); // 넘어온 email로 회원정보 검색

        //메일인증 다시한번 확인
        JoinAuthVO joinAuthVO = joinService.selectUserJoinAuth(vo);
        Object joinOverlapId = request.getSession().getAttribute(JoinConstant.JOIN_OVERLAP_ID);

        if (request.getSession().getAttribute(JoinConstant.JOIN_OVERLAP_ID) == null || !"Y".equals(joinAuthVO.getEmailCertYn())) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request, "TEXT_383"));
        } else if (joinOverlapId == null || "N".equals((String)joinOverlapId)) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request, "TEXT_380"));
        } else if (basic != null && vo.getUserEmail().equals(basic.getUserEmail())) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request, "TEXT_390"));
        } else if (!vo.getUserEmail().equals(request.getSession().getAttribute(JoinConstant.MAIL_AUTH_CHECK))) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request, "TEXT_383"));
        } else if(request.getSession().getAttribute(JoinConstant.MAIL_AUTH_CHECK) == null) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request, "TEXT_383"));
        } else if(request.getSession().getAttribute(JoinConstant.AUTH_CONFIRMED_NUMBER) == null || request.getSession().getAttribute(JoinConstant.AUTH_CONFIRMED_NAME) == null) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request, "TEXT_423"));
        } else if(!vo.getHphone().equals(request.getSession().getAttribute(JoinConstant.AUTH_CONFIRMED_NUMBER))) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request,"02_22"));
        } else if(!vo.getUserNm().equals(request.getSession().getAttribute(JoinConstant.AUTH_CONFIRMED_NAME))) {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request,"02_23"));
        }
        else {
            HttpSession session = request.getSession();
            PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
            if (!"".equals(vo.getUserPwd())) {
                String aceKey = vo.getAceKey();
                aceKey = SecurityUtil.decryptRsa(privateKey, aceKey); // 복호화
                String _userPw = SecurityUtil.Dec256Str(vo.getUserPwd(), aceKey);
                String _suserPw = SecurityUtil.Sha256Encode(_userPw);

                JoinVO newUserVO = new JoinVO();
                newUserVO.setUserEmail(vo.getUserEmail());
                newUserVO.setUserNm(vo.getUserNm());
                newUserVO.setUserPwd(_suserPw);
                newUserVO.setHphone(vo.getHphone());
                newUserVO.setGender("");
                newUserVO.setAge("");
                newUserVO.setChgPwd("N");
                newUserVO.setTestYn("N");
                newUserVO.setFudYn("N");
                newUserVO.setRegEmail(vo.getUserEmail());
                newUserVO.setUseYn("Y");
                newUserVO.setRegIp(ComUtil.getRemoteIP(request));
                newUserVO.setLangCd(StringUtils.checkNull(getLocale(request), "ko"));

                try {
                    joinService.insertNewUser(newUserVO); // 회원 insert

                    CustVO lvo = loginService.getCustInfo(cvo);
                    httpSession.setAttribute(LoginConstant.LOGIN_SESSION, lvo);

                    ResultVO.setResultCode(1);
                    ResultVO.setResultMsg(getLocale(request, "TEXT_78"));
                } catch (Exception e) {
                    ResultVO.setResultCode(-1);
                    ResultVO.setResultMsg(getLocale(request, "TEXT_386"));
                }

            } else {
                ResultVO.setResultCode(-1);
                ResultVO.setResultMsg(getLocale(request, "TEXT_386"));
            }
        }
        return ResultVO;
    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : mobileAuth
     * @작성일 :
     * @작성자 :  (주)씨엠이소프트
     * @변경이력 :
     * @Method 설명 : mobileAuth
     */
    @RequestMapping(value = "/mobileAuth.dm")
    public @ResponseBody
    Map mobileAuth(@ModelAttribute JoinVO vo, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        HashMap<String, String> param = new HashMap<>();
        Map<String, Object> resultParam = new HashMap<>();

        String modify = vo.getModify();

        resultParam.put("resultCode", "1");
        resultParam.put("resultMsg", getLocale(request, "02_15"));
        int count = joinService.matchUserMobile(vo);

        if (count > 0) {
            resultParam.put("resultCode", "-1");
            resultParam.put("resultMsg", getLocale(request, "TEXT_420"));

            return resultParam;
        }

        if("Y".equals(modify)) {
            vo.setUserEmail(lUtil.getLoginId(request, response));
        }

        JoinAuthVO jvo = joinService.selectUserJoinAuth(vo);
        if(jvo == null || !"Y".equals(jvo.getEmailCertYn())) {
            resultParam.put("resultCode", "-1");
            resultParam.put("resultMsg", getLocale(request, "TEXT_421"));
            return resultParam;
        }

        if("Y".equals(modify)) {
            param.put("userEmail", lUtil.getLoginId(request, response));
        } else {
            param.put("userEmail", vo.getUserEmail());
        }

        param.put("hphone", vo.getHphone());
        param.put("modify", vo.getModify());
        param.put("userNm", vo.getUserNm());

        // 다날 인증 패싱
        //resultParam = danalAuthService.reqAuth(request, response, param);
        /*
        * ****************************************************
        * 다날 인증 패싱일 때 전화번호 수정 로직 추가. 인증이 추가 되면, 다날 인증 프로세스에 포함되어 제거필요. YK.2023.05
        * **************************************************
         */
        JoinAuthVO joinAuthVO = new JoinAuthVO();

        int resUpt = 0;
        if("Y".equals(modify)) {
            String userEmail = lUtil.getLoginId(request, response);
            CustVO pVO = new CustVO();
            pVO.setUserEmail(userEmail);
            pVO.setHphone(vo.getHphone());
            resUpt = mypageService.updateUserInfo(pVO);

            System.out.println("###사용자 정보 업데이트 ::" + resUpt);

            if(resUpt > 0){
                CustVO cvo = new CustVO();
                cvo.setUserEmail(lUtil.getLoginId(request, response));
                CustVO lvo = loginService.getCustInfo(cvo);
                httpSession.setAttribute(LoginConstant.LOGIN_SESSION, lvo);
            }

            joinAuthVO.setUserEmail(lUtil.getLoginId(request, response));
        }else{
            request.getSession().setAttribute(JoinConstant.AUTH_CONFIRMED_NUMBER, vo.getHphone());
            request.getSession().setAttribute(JoinConstant.AUTH_CONFIRMED_NAME, vo.getUserNm());
            joinAuthVO.setUserEmail(vo.getUserEmail());
        }

        joinAuthVO.setSmsCertYn("Y");
        resUpt = mypageService.updateCertInfo(joinAuthVO);
        System.out.println("###사용자 SMS인증정보 업데이트 ::" + resUpt);
        /*
         * ****************************************************
         * 다날 인증 패싱 로직 끝............. YK.
         * **************************************************
         */

        return resultParam;
    }

    @RequestMapping(value="/authResult.dm")
    public ModelAndView authResult (HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		/*
			휴대폰 본인인증 결과가 오는곳
			이곳으로 오기만 하면 본인인증에 성공한것으로 간주함
		 */

        Map map = danalAuthService.CPCGI(request, response);

        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            System.out.println("key : " + key + " value : " + map.get(key));
        }

        if("0000".equals(map.get("RETURNCODE"))){

            String userNm = map.get("userNm") == null ? "" : (String) map.get("userNm");        //다날에 보낸값
            String danalUserNm= map.get("NAME").toString();                                     //다날리턴값
            if(!"".equals(userNm)) {
                if (!userNm.equals(danalUserNm)) {
                    map.put("RETURNCODE", "998"); //이름 : 입력값 , 리턴값 같지않음으로 본인인증 실패처리
                    model.addAttribute("map", map);
                    return new ModelAndView("/front/join/authResult");
                }
            }
            /*
                회원 휴대폰번호 변경에 대한 처리
             */
            String modify = map.get("modify") == null ? "" : (String)map.get("modify");

            if(modify.equals("Y"))
            {
                String userEmail = lUtil.getLoginId(request, response);
                String hphone = map.get("IsDstAddr") == null ? "" : (String)map.get("IsDstAddr");

                CustVO pVO = new CustVO();
                pVO.setUserEmail(userEmail);
                pVO.setHphone(hphone);
                mypageService.updateUserInfo(pVO);
            } else {
                request.getSession().setAttribute(JoinConstant.AUTH_CONFIRMED_NUMBER, map.get("IsDstAddr"));
                request.getSession().setAttribute(JoinConstant.AUTH_CONFIRMED_NAME, map.get("NAME"));
            }


            JoinAuthVO joinAuthVO = new JoinAuthVO();
            if("Y".equals(modify)) {
                joinAuthVO.setUserEmail(lUtil.getLoginId(request, response));
            } else {
                String signUpEmail = map.get("userEmail") == null ? "" : (String)map.get("userEmail");
                joinAuthVO.setUserEmail(signUpEmail);
            }
            joinAuthVO.setSmsCertYn("Y");
            mypageService.updateCertInfo(joinAuthVO);

            if("Y".equals(modify)) {
                CustVO cvo = new CustVO();
                cvo.setUserEmail(lUtil.getLoginId(request, response));
                CustVO lvo = loginService.getCustInfo(cvo);
                httpSession.setAttribute(LoginConstant.LOGIN_SESSION, lvo);
            }
        }
        model.addAttribute("map", map);
        return new ModelAndView("/front/join/authResult");
    }

}
