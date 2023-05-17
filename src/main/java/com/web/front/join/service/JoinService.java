package com.web.front.join.service;


import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;

public interface JoinService {

    public JoinAuthVO selectUserJoinAuth(JoinVO avo);

    public void insertNewUser(JoinVO vo);

    public void insertCertInfo(JoinAuthVO vo);

    public void uptUserJoinAuth(JoinAuthVO vo);

    public int matchUserMobile(JoinVO vo);

}
