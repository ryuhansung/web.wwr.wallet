package com.web.front.send.service.impl;

import com.web.front.mypage.vo.CustVO;
import com.web.front.send.dao.SendDAO;
import com.web.front.send.service.SendService;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.vo.WalletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    SendDAO sendDAO;

    public List<WalletVO> findAddr(CustVO vo) {
        return sendDAO.findAddr(vo);
    }

    @Override
    public SendVO selectSendLimitAmt(SendVO vo) {
        return sendDAO.selectSendLimitAmt(vo);
    }
}
