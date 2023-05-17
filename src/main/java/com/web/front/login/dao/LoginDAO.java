package com.web.front.login.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.login.vo.ForeignIpVO;
import com.web.front.login.vo.LoginHistoryVO;
import com.web.front.mypage.vo.CustVO;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO extends CmeComAbstractDAO {

    public CustVO getCustInfo(CustVO vo)  throws Exception {
        return (CustVO) selectByPk("LoginDAO.getCustInfo", vo);
    }

    public void updateSessionData(CustVO vo) throws Exception {
        update("LoginDAO.updateSessionData", vo);
    }

    public void updateSessionDataMobile(CustVO vo) throws Exception {
        update("LoginDAO.updateSessionDataMobile", vo);
    }

    public void historyInsert(LoginHistoryVO vo) throws Exception{
        insert("LoginDAO.historyInsert", vo);
    }

    public ForeignIpVO getForeignIpCheck(String ip) {
        return (ForeignIpVO)selectByPk("LoginDAO.getForeignIpCheck" , ip);
    }

    public void insertCheckIp(ForeignIpVO avo) {
        insert("LoginDAO.insertCheckIp" , avo);
    }
}
