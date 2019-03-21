package com.pactera.gcw.common.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;

public class MailUsageExample {

    /**
     * @return void
     */
    public static void main(String[] args) {
        MailInfo mailInfo = new MailInfo();
        List<String> toList = new ArrayList<String>();
        toList.add("910150090@qq.com");
        
        List<String> ccList = new ArrayList<String>();
        ccList.add("liuj_0805@163.com");
        ccList.add("910794406@qq.com");
        
        List<String> bccList = new ArrayList<String>();
        bccList.add("liuj_0805@163.com");
        
        //添加附件
        EmailAttachment att = new EmailAttachment();
        att.setPath("./README.md");
        att.setName("README.md");
        List<EmailAttachment> atts = new ArrayList<EmailAttachment>();
        atts.add(att);
        mailInfo.setAttachments(atts);
        
        mailInfo.setToAddress(toList);//收件人
        mailInfo.setCcAddress(ccList);//抄送人
        mailInfo.setBccAddress(bccList);//密送人
        
        mailInfo.setSubject("测试用：Please review the Readme document.");
        mailInfo.setContent("测试用：<h1>Please review the Readme document.</h1>");

        MailHelper mp = new MailHelper("smtp.qq.com", "910150090@qq.com", "910150090@qq.com",
                "rlalvhtqhqilbbjf");
        mp.sendEmail(mailInfo);

    }
}