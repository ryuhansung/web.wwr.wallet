package com.web.front.lending.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import com.web.front.lending.vo.LendingVO;
import org.springframework.stereotype.Repository;

@Repository
public class LendingDAO extends CmeComAbstractDAO {

    public LendingVO selectLending(String user_email){
        return (LendingVO) selectByPk("LendingDAO.selectLending", user_email);
    }
}
