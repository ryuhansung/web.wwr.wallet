package com.web.front.join.service.impl;

import com.web.front.join.dao.JoinDAO;
import com.web.front.join.service.JoinService;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    JoinDAO joinDAO;

    public JoinAuthVO selectUserJoinAuth(JoinVO vo) {
        return joinDAO.selectUserJoinAuth(vo);
    }

    public void insertNewUser(JoinVO vo) {
        joinDAO.insertNewUser(vo);
    }

    public void insertCertInfo(JoinAuthVO vo) { joinDAO.insertCertInfo(vo); }

    public void uptUserJoinAuth(JoinAuthVO vo) { joinDAO.uptUserJoinAuth(vo); }

    public int matchUserMobile(JoinVO vo) { return joinDAO.matchUserMobile(vo); }

}
