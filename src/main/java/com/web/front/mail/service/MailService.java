package com.web.front.mail.service;

import com.web.front.mail.vo.mailHistoryVO;

public interface MailService {

    mailHistoryVO getMailHis(mailHistoryVO vo) throws Exception;
}
