package com.web.front.push.service.impl;

import com.web.front.mypage.vo.CustVO;
import com.web.front.push.dao.PushDAO;
import com.web.front.push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushServiceImpl implements PushService {

    @Autowired
    PushDAO pushDAO;

    @Override
    public void updateToken(CustVO vo) throws Exception {
        pushDAO.updateToken(vo);
    }
}
