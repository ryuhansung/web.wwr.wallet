package com.web.front.push.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.mypage.vo.CustVO;
import org.springframework.stereotype.Repository;

@Repository
public class PushDAO extends CmeComAbstractDAO {

    public void updateToken(CustVO vo) {
        update("PushDAO.updateToken", vo);
    }

}
