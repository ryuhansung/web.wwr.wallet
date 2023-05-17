package com.web.front.send;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.mail.vo.mailHistoryVO;
import org.springframework.stereotype.Repository;

@Repository
public class MailDAO extends CmeComAbstractDAO {

    public int insertMailHis(mailHistoryVO hvo) throws Exception {
        return update("MailDAO.insertMailHis",hvo);
    }

    public String getSndCode() {
        return (String) selectByPk("MailDAO.getSndCode",null);
    }

    public int updateMailHis(mailHistoryVO hvo) {
        return update("MailDAO.updateMailHis",hvo);
    }

    public mailHistoryVO getMailHis(mailHistoryVO vo) {
        return (mailHistoryVO) selectByPk("MailDAO.getMailHis",vo);
    }

}
