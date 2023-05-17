package com.web.front.lending.service.impl;

import com.web.front.join.dao.JoinDAO;
import com.web.front.join.service.JoinService;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import com.web.front.lending.dao.LendingDAO;
import com.web.front.lending.service.LendingService;
import com.web.front.lending.vo.LendingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingServiceImpl implements LendingService {

    @Autowired
    LendingDAO LendingDAO;

    public LendingVO selectLending(String user_email) {
        return LendingDAO.selectLending(user_email);
    }


}
