package com.web.front.wallet.service;

import com.web.config.CmeResultVO;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.vo.WalletVO;

import java.util.List;

public interface WalletService {
    List<WalletVO> getWalletList(WalletVO vo) throws Exception;

    int insertWallet(WalletVO vo) throws Exception;
    void updateAddrPwd(WalletVO vo) throws Exception;
    int selectAddrPwdMatch(WalletVO vo) throws Exception;

    List<WalletVO> selectUseCoinList(WalletVO vo) throws Exception;

    String getLockMny(SendVO vo)throws Exception;

    CmeResultVO getTrcDepositInfo(WalletVO vo) throws Exception;
}
