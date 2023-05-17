package com.web.front.send.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.mypage.vo.CustVO;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.vo.WalletVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SendDAO extends CmeComAbstractDAO {

    public List<WalletVO> findAddr(CustVO vo) {
        return (List<WalletVO>)list("SendDAO.findAddr", vo);
    }

    public SendVO selectSendLimitAmt(SendVO vo) { return (SendVO)selectByPk("SendDAO.selectSendLimitAmt", vo); }

}
