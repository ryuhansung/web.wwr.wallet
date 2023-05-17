package com.web.front.login.service;

import com.web.front.login.vo.LoginHistoryVO;
import com.web.front.mypage.vo.CustVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface LoginService {

    CustVO getCustInfo(CustVO vo) throws Exception;

    void updateSessionData(CustVO vo) throws Exception;

    void login(HttpServletRequest request, HttpServletResponse response, CustVO vo) throws Exception;

    void logout(HttpServletRequest request) throws Exception;

    void updateSessionDataMobile(CustVO vo) throws Exception;

    void historyInsert(LoginHistoryVO vo) throws Exception;

    boolean isKoreaYn(String ip) throws Exception;
}
