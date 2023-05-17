package com.web.front.deposit.controller;

import com.web.front.login.util.LUtil;
import com.web.front.mypage.vo.CustVO;
import com.web.front.wallet.service.WalletService;
import com.web.front.wallet.vo.WalletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/front/deposit")
public class DepositController {

    @Autowired
    WalletService walletService;

    LUtil lUtil = LUtil.getInstance();

    @RequestMapping(value = "/depositView.dm")
    public ModelAndView depositView(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        String loginId = lUtil.getLoginId(request, response); // 로그인검사 겸용

        WalletVO vo = new WalletVO();
        //vo.setCoinNm("SCV"); // 초기값
        vo.setUserEmail(loginId);
        List<WalletVO> walletList = walletService.getWalletList(vo); //사용자 지갑주소 조회
        model.addAttribute("walletList", walletList);

        WalletVO wavo = new WalletVO();
        List<WalletVO> coinInfo = walletService.selectUseCoinList(wavo);
        model.addAttribute("coinInfo", coinInfo);

        return new ModelAndView("/front/deposit/depositView");

    }
}
