package com.web.front.join.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import org.springframework.stereotype.Repository;

@Repository
public class JoinDAO extends CmeComAbstractDAO {

    public JoinAuthVO selectUserJoinAuth(JoinVO avo) {
        return (JoinAuthVO) selectByPk("JoinDAO.selectUserJoinAuth", avo);
    }

    public void insertNewUser(JoinVO vo) {
        insert("JoinDAO.insertNewUser", vo);
    }

    public void insertCertInfo(JoinAuthVO vo) { insert("JoinDAO.insertCertInfo", vo); }

    public void uptUserJoinAuth(JoinAuthVO vo) { update("JoinDAO.uptUserJoinAuth", vo); }

    public int matchUserMobile(JoinVO vo) { return (int)selectByPk("JoinDAO.matchUserMobile", vo); }

}
