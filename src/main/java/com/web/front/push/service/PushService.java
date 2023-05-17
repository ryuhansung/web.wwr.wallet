package com.web.front.push.service;

import com.web.front.mypage.vo.CustVO;

public interface PushService {

    void updateToken(CustVO vo) throws Exception;

}
