package com.pactera.gcw.common.mail;

import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailHelper {
    
    @Value("${mail.ServerHost}")
    private String mailServerHost;

    @Value("${mail.SenderAddress}")
    private String mailSenderAddress;

    @Value("${mail.SenderNick}")
    private String mailSenderNick;

    @Value("${mail.SenderUsername}")
    private String mailSenderUsername;

    @Value("${mail.SenderPassword}")
    private String mailSenderPassword;

    private static final Logger logger = LoggerFactory.getLogger(MailHelper.class);

    public MailHelper() {

    }

    public MailHelper(String mailServerHost, String mailSenderAddress, String mailSenderUsername,
            String mailSenderPassword) {
        this.mailServerHost = mailServerHost;
        this.mailSenderAddress = mailSenderAddress;
        this.mailSenderUsername = mailSenderUsername;
        this.mailSenderPassword = mailSenderPassword;
        this.mailSenderNick = "VMFLEX";
    }

    public MailHelper(String mailServerHost, String mailSenderAddress, String mailSenderNick,
            String mailSenderUsername, String mailSenderPassword) {
        this.mailServerHost = mailServerHost;
        this.mailSenderAddress = mailSenderAddress;
        this.mailSenderNick = mailSenderNick;
        this.mailSenderUsername = mailSenderUsername;
        this.mailSenderPassword = mailSenderPassword;
    }

    /**
     * 发送 邮件方法 (Html格式，支持附件)
     * 
     * @return void
     */
    public void sendEmail(MailInfo mailInfo) {
         
        try {
            HtmlEmail email = new HtmlEmail();
            // 配置信息
            email.setHostName(mailServerHost);
            email.setFrom(mailSenderAddress,mailSenderNick);
            email.setAuthentication(mailSenderUsername,mailSenderPassword);
            email.setCharset("UTF-8");
            email.setSubject(mailInfo.getSubject());
            email.setHtmlMsg(mailInfo.getContent());

            // 添加附件
            List<EmailAttachment> attachments = mailInfo.getAttachments();
            if (null != attachments && attachments.size() > 0) {
                for (int i = 0; i < attachments.size(); i++) {
                    email.attach(attachments.get(i));
                }
            }
            
            // 收件人
            List<String> toAddress = mailInfo.getToAddress();
            if (null != toAddress && toAddress.size() > 0) {
                for (int i = 0; i < toAddress.size(); i++) {
                        email.addTo(toAddress.get(i));
                }
            }
            // 抄送人
            List<String> ccAddress = mailInfo.getCcAddress();
            if (null != ccAddress && ccAddress.size() > 0) {
                for (int i = 0; i < ccAddress.size(); i++) {
                        email.addCc(ccAddress.get(i));
                }
            }
            //邮件模板 密送人
            List<String> bccAddress = mailInfo.getBccAddress();
            if (null != bccAddress && bccAddress.size() > 0) {
                for (int i = 0; i < bccAddress.size(); i++) {
                    email.addBcc(ccAddress.get(i));
                }
            }
            email.send();
            logger.debug("Email is sent successfully.");
        } catch (EmailException e) {
            e.printStackTrace();
        } 
    }
}