package com.web.config.mail;

import com.web.config.CmeResultVO;
import com.web.config.logging.CmeCommonLogger;
import com.web.config.vo.MailInfoVO;
import com.web.front.send.MailDAO;
import com.web.front.mypage.dao.MypageDAO;
import com.web.front.mail.vo.mailHistoryVO;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@SuppressWarnings("deprecation")
@ComponentScan("com.bitkrx")
@Service
public class MailSenderImpl {
    protected CmeCommonLogger log = new CmeCommonLogger(this.getClass());
    
    @Autowired 
    JavaMailSender javaMailSender;

    @Autowired
    MypageDAO mypageDAO;

    @Autowired
    VelocityEngine getVelocityEngine;

    @Autowired
    MailDAO mailDAO;
    
    public CmeResultVO sendEmail(MailInfoVO mailVO) {
        
        CmeResultVO vo = new CmeResultVO();

        mailHistoryVO hvo = new mailHistoryVO();
        String sndCode = mailDAO.getSndCode();
        hvo.setSndCode(sndCode);
        hvo.setUserEmail(mailVO.getOriMail());
        hvo.setSndYn("N");
        hvo.setCtntsCode(mailVO.getMailSubject());
        try{
            int upt = mailDAO.insertMailHis(hvo);
        }catch (Exception e){
            e.printStackTrace();
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        getVelocityEngine.init();

        try {

            mailVO.getModel().put("sndCode",sndCode);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mailVO.getMailSubject());
            mimeMessageHelper.setFrom(mailVO.getMailFrom());
            mimeMessageHelper.setTo(mailVO.getMailTo());
            mailVO.setMailContent(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine, "/templates/"+mailVO.getMailTemplateForm(),"UTF-8", mailVO.getModel()));
            mimeMessageHelper.setText(mailVO.getMailContent(), true);
 
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

            vo.setResultCode(1);
            vo.setResultMsg("정상처리되었습니다.");
        } catch (MessagingException e) {
            e.printStackTrace();
            vo.setResultCode(-1);
            vo.setResultMsg(e.getMessage());
        }

        if(1 == vo.getResultCode()){
            hvo.setSndYn("Y");
        }

        try {
            int upt = mailDAO.updateMailHis(hvo);
            if(upt > 0){
                vo.setResultCode(1);
                vo.setResultMsg("정상처리되었습니다.");
            }else{
                vo.setResultCode(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo;
    }


    

}
