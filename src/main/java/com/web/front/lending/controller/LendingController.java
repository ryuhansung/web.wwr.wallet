package com.web.front.lending.controller;

import com.web.front.lending.service.LendingService;
import com.web.front.lending.vo.LendingVO;
import com.web.front.login.LoginConstant;
import com.web.front.mypage.vo.CustVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/front/lend/")
public class LendingController {

    @Autowired
    LendingService lendingService;

    @RequestMapping(value="/lendingView.dm")
    public ModelAndView lending(HttpServletRequest request, ModelMap model)throws Exception{

        HttpSession httpSession = request.getSession();
        Object res = httpSession.getAttribute(LoginConstant.LOGIN_SESSION);

        if(res != null){
            CustVO vo = (CustVO)res;
            LendingVO lvo = lendingService.selectLending(vo.getUserEmail());

            model.addAttribute("lvo", lvo);
        }

        return new ModelAndView("/front/lend/lendingView");
    }
}
