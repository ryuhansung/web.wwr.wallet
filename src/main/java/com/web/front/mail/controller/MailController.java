package com.web.front.mail.controller;

import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.init.WebConfig;
import com.web.config.mail.MailSenderImpl;
import com.web.config.service.CmeProperties;
import com.web.config.vo.MailInfoVO;
import com.web.front.login.service.LoginService;
import com.web.front.login.util.LUtil;
import com.web.front.mail.service.MailService;
import com.web.front.mypage.service.MypageService;
import com.web.front.mypage.vo.CustVO;
import com.web.front.mail.vo.mailHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/front/mail")
public class MailController extends CmeDefaultExtendController {

    @Autowired
    MypageService mypageService;

    @Autowired
    LoginService loginService;

    @Autowired
    MailSenderImpl mailSender;

    @Autowired
    MailService mailService;

    LUtil lUtil = LUtil.getInstance();

    @RequestMapping(value = "/pwMailSend.dm")
    public @ResponseBody
    CmeResultVO pwMailSend(CustVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CmeResultVO res = new CmeResultVO();
        if("".equals(vo.getUserEmail())){
            vo.setUserEmail(lUtil.getLoginId(request, response));
        }
        CustVO cvo = loginService.getCustInfo(vo);

        if (null == cvo) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_394"));
            return res;
        }

        vo.setChgPwd("Y");
        int update = mypageService.updateUserInfo(vo);

        if(update > 0 ){
            //메일전송
            MailInfoVO mail = new MailInfoVO();
            mail.setMailFrom(WebConfig.getInstance().MAIL_FROM);

            if(!"".equals(cvo.getSubUserEmail())){ // 보조이메일이 있으면
                mail.setMailTo(cvo.getSubUserEmail());
            }else{
                mail.setMailTo(vo.getUserEmail()); //기존 사용자 email
            }

            mail.setMailSubject(getLocale(request, "TEXT_246"));
            mail.setMailTemplateForm("pw_reset.html");
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
            map.put("content1", getLocale(request, "TEXT_246"));
            map.put("content2", getLocale(request, "TEXT_392"));
            map.put("content3", getLocale(request, "TEXT_246"));
            map.put("content4", "Copyright (c) 2023 RNR Wallet. All rights reserved.");

            mail.setModel(map);
            res = mailSender.sendEmail(mail); //메일전송

            if(res.getResultCode() > 0 ) { //메일전송까지 성공했을경우
                res.setResultCode(1);
                res.setResultMsg(getLocale(request, "TEXT_379"));
            } else {
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_386"));
            }

        }else{
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_386"));
        }

        return res;
    }

    @RequestMapping(value="/pwResetView.dm")
    public ModelAndView pwResetView(@RequestParam HashMap<String, String> param, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
        setLocale(request,response,param.get("lang"));//메일보낼때 보냈던 값으로 언어팩 설정
        return new ModelAndView("/front/mail/pw_reset");
    }


    @RequestMapping(value = "/mailCheck.dm")
    public @ResponseBody CmeResultVO mailCheck(mailHistoryVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CmeResultVO res = new CmeResultVO();
        CustVO svo = new CustVO();
        svo.setUserEmail(vo.getUserEmail());
        CustVO cvo = loginService.getCustInfo(svo);

        if (null == cvo) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_394"));
            return res;
        }else{
            if("N".equals(cvo.getChgPwd())){
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_408"));
                return res;
            }else{
                mailHistoryVO mailInfo = mailService.getMailHis(vo); //넘어온 email로 회원정보 검색
                if(null == mailInfo){
                    res.setResultCode(-1);
                    res.setResultMsg(getLocale(request, "TEXT_394"));
                    return res;
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
                    res.setResultCode(1);
                }
            }
        }

        return res;

    }

    @RequestMapping(value = "/changePwd.dm")
    public @ResponseBody CmeResultVO changePwd(CustVO vo,HttpServletRequest request, HttpServletResponse response) throws Exception {
        CmeResultVO res = new CmeResultVO();
        CustVO cvo = loginService.getCustInfo(vo);

        if (null == cvo) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_394"));
            return res;
        }else{
            if("N".equals(cvo.getUserPwd())){
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_408"));
                return res;
            }
        }

        vo.setChgPwd("N");
        PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
        String aceKey = vo.getAceKey();
        aceKey = SecurityUtil.decryptRsa(privateKey, aceKey);
        String _userPw = SecurityUtil.Dec256Str(vo.getUserPwd(), aceKey);
        String _suserPw = SecurityUtil.Sha256Encode(_userPw);
        vo.setUserPwd(_suserPw);

        int update = mypageService.updateUserInfo(vo); //회원정보변경

        if(update > 0){
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_384"));
        }else{
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_386"));
        }

        return res;
    }
}
