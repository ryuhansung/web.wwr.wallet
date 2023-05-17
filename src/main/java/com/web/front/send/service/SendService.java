package com.web.front.send.service;

import com.web.front.mypage.vo.CustVO;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.vo.WalletVO;

import java.util.List;

public interface SendService {

    List<WalletVO> findAddr(CustVO vo);
    SendVO selectSendLimitAmt(SendVO vo);

}
