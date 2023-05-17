package com.web.front.mypage.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.mypage.vo.CustVO;
import com.web.front.send.vo.SendVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MypageDAO extends CmeComAbstractDAO {

    public int updateUserInfo(CustVO vo) throws Exception {
        return update("MypageDAO.updateUserInfo",vo);
    }

    public int updateCertInfo(JoinAuthVO vo) { return update("MypageDAO.updateCertInfo", vo); }

    public void mergeSafeSendPwd(SendVO vo) { update("MypageDAO.mergeSafeSendPwd", vo); }

    public SendVO selectSafeSendPwd(CustVO vo) {
        return (SendVO)selectByPk("MypageDAO.selectSafeSendPwd", vo);
    }

    public int matchSafeSendPwd(SendVO vo) { return (int)selectByPk("MypageDAO.matchSafeSendPwd", vo); }

    public List<Map<String,Object>> getAssetInOutList(Map<String, Object> paramMap) throws Exception{

        return (List<Map<String,Object>>)list("MypageDAO.getAssetInOutList", paramMap);

    }
}
