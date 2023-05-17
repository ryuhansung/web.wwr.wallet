package com.web.front.mypage.service.impl;

import com.web.front.join.vo.JoinAuthVO;
import com.web.front.mypage.dao.MypageDAO;
import com.web.front.mypage.service.MypageService;
import com.web.front.mypage.vo.CustVO;
import com.web.front.send.vo.SendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MypageServiceImpl implements MypageService {

    @Autowired
    MypageDAO mypageDAO;

    @Override
    public int updateUserInfo(CustVO vo) throws Exception {
        return mypageDAO.updateUserInfo(vo);
    }

    @Override
    public int updateCertInfo(JoinAuthVO vo) throws Exception {
        return mypageDAO.updateCertInfo(vo);
    }

    @Override
    public void mergeSafeSendPwd(SendVO vo) throws Exception {
        mypageDAO.mergeSafeSendPwd(vo);
    }

    @Override
    public SendVO selectSafeSendPwd(CustVO vo) throws Exception {
        return mypageDAO.selectSafeSendPwd(vo);
    }

    @Override
    public int matchSafeSendPwd(SendVO vo) throws Exception {
        return mypageDAO.matchSafeSendPwd(vo);
    }

    @Override
    public List<Map<String, Object>> getAssetInOutList(Map<String, Object> paramMap) throws Exception {
        return mypageDAO.getAssetInOutList(paramMap);
    }
}
