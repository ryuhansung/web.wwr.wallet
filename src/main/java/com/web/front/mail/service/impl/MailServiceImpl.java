package com.web.front.mail.service.impl;

import com.web.front.send.MailDAO;
import com.web.front.mail.service.MailService;
import com.web.front.mail.vo.mailHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    MailDAO mailDAO;

    @Override
    public mailHistoryVO getMailHis(mailHistoryVO vo) throws Exception {
        return mailDAO.getMailHis(vo);
    }
}
