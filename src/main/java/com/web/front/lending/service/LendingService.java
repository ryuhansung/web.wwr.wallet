package com.web.front.lending.service;


import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import com.web.front.lending.vo.LendingVO;

public interface LendingService {

    public LendingVO selectLending(String user_email);

}
