/**
 * 씨엠이 소프트 자체 프로젝트 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)씨엠이소프트
 * CopyRight 씨엠이소프트 - 2017
 */
package com.web.front.mypage.control;


import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.mail.MailSenderImpl;
import com.web.front.join.service.JoinService;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import com.web.front.login.service.LoginService;
import com.web.front.login.util.LUtil;
import com.web.front.mypage.service.MypageService;
import com.web.front.mypage.vo.CustVO;
import com.web.front.send.vo.SendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;

@Controller
@RequestMapping(value = "/front/mypage")
public class MypageController extends CmeDefaultExtendController {

    @Autowired
    MypageService mypageService;

    @Autowired
    LoginService loginService;

    @Autowired
    MailSenderImpl mailSender;

    @Autowired
    JoinService joinService;

    LUtil lUtil = LUtil.getInstance();

    @RequestMapping(value="/myInfoView.dm")
    public ModelAndView custInfo(CustVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
        vo.setUserEmail(lUtil.getLoginId(request, response));
        SendVO svo = mypageService.selectSafeSendPwd(vo);

        if(svo != null) {
            model.addAttribute("sendPwdUse", "Y");
        } else {
            model.addAttribute("sendPwdUse", "N");
        }

        //2019.12.02 윤원식 추가
        //회원정보, 회원인증정보 조회
        CustVO userInfo = loginService.getCustInfo(vo);
        model.addAttribute("userInfo",userInfo);

        JoinVO joinVO = new JoinVO();
        joinVO.setUserEmail(vo.getUserEmail());
        JoinAuthVO userAuthInfo = joinService.selectUserJoinAuth(joinVO);
        model.addAttribute("userAuthInfo",userAuthInfo);

        vo.setLang(getLocale(request));
        return new ModelAndView("/front/mypage/myInfoView");
    }

    @RequestMapping(value="/setSendPwd.dm")
    public @ResponseBody
    CmeResultVO setSendPwd(SendVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        CmeResultVO res = new CmeResultVO();
        res.setResultCode(-1);

        try {
            vo.setUserEmail(lUtil.getLoginId(request, response));
            PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");

            if("reset".equals(vo.getSetPwdType())) {
                String oldAceKey = vo.getOldAceKey();
                oldAceKey = SecurityUtil.decryptRsa(privateKey, oldAceKey);
                String _oldPw = SecurityUtil.Dec256Str(vo.getSafeOldSendPwd(), oldAceKey);
                String __oldPw = SecurityUtil.Sha256Encode(_oldPw);
                vo.setSafeSendPwd(__oldPw);

                int cnt = mypageService.matchSafeSendPwd(vo);
                if(cnt == 0) {
                    res.setResultCode(-3);
                    res.setResultMsg("현재 출금암호가 일치하지 않습니다.");
                    return res;
                }
            }

            String aceKey = vo.getAceKey();
            aceKey = SecurityUtil.decryptRsa(privateKey, aceKey);
            String _userPw = SecurityUtil.Dec256Str(vo.getSafeNewSendPwd(), aceKey);
            String _suserPw = SecurityUtil.Sha256Encode(_userPw);
            vo.setSafeNewSendPwd(_suserPw);

            mypageService.mergeSafeSendPwd(vo);

            res.setResultCode(1);
            res.setResultMsg(getLocale(request, "TEXT_121"));
        } catch(Exception e) {
            res.setResultCode(-2);
        }

        return res;

    }

}
