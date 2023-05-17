package com.web.front.wallet.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.vo.WalletVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WalletDAO extends CmeComAbstractDAO {

    public List<WalletVO> getWalletList(WalletVO vo) throws Exception{
        return list("WalletDAO.getWalletList", vo);
    }

    public int insertWallet(WalletVO vo) throws Exception{
        return (int)insert("WalletDAO.insertWallet", vo);
    }

    public void updateAddrPwd(WalletVO vo) throws Exception {
        update("WalletDAO.updateAddrPwd", vo);
    }

    public int selectAddrPwdMatch(WalletVO vo) throws Exception {
        return (int)selectByPk("WalletDAO.selectAddrPwdMatch", vo);
    }

    public List<WalletVO> selectUseCoinList(WalletVO vo) throws Exception {
        return list("WalletDAO.selectUseCoinList", vo);
    }

    public String getLockMny(SendVO vo) throws Exception {
        return (String)selectByPk("WalletDAO.getLockMny", vo);
    }
}
