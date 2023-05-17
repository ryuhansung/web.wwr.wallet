package com.web.front.mypage.service;

import com.web.front.join.vo.JoinAuthVO;
import com.web.front.mypage.vo.CustVO;
import com.web.front.send.vo.SendVO;

import java.util.List;
import java.util.Map;

public interface MypageService {

    int updateUserInfo(CustVO vo) throws Exception;

    int updateCertInfo(JoinAuthVO vo) throws Exception;

    void mergeSafeSendPwd(SendVO vo) throws Exception;

    SendVO selectSafeSendPwd(CustVO vo) throws Exception;

    int matchSafeSendPwd(SendVO vo) throws Exception;
    List<Map<String,Object>> getAssetInOutList(Map<String, Object> paramMap) throws Exception;
}
