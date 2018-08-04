/**
 * 
 */
package com.neuedu.crm.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wanghaoyu
 *
 */
public class MailUtil {



    private JavaMailSenderImpl mailSender; 
    
    /**
    *   JavaMailSenderImpl支持MimeMessages和SimpleMailMessages。
    * MimeMessages为复杂邮件模板，支持文本、附件、html、图片等。
    * SimpleMailMessages实现了MimeMessageHelper，为普通邮件模板，支持文本
    */
    private SimpleMailMessage simpleMailMessage;
    

    /**
     * 描述：Spring 依赖注入
     * @author wanghaoyu
     * @date 
     * @version 1.0
     * @param mailSender
     * @since 1.8
     *
     */
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    
    /**
     * 描述：Spring 依赖注入
     * @author wanghaoyu
     * @date 
     * @version 1.0
     * @param simpleMailMessage void
     * @since 1.8
     *
     */
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
    
    /**
     * 单发
     *
     * @param recipient 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void send(String recipient,String subject,String content){
    	System.out.println(simpleMailMessage);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
    
    /**
     * 群发
     *
     * @param recipients 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void send(List<String> recipients,String subject,String content){
        simpleMailMessage.setTo(recipients.toArray(new String[recipients.size()]));
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
    
    /**
     * 发送带附件的邮件
     * @author malizhi
     * @param recipient
     * @param subject
     * @param content
     * @param file void
     * @version 1.0
     * @exception Nothing
     */
    public void sendWithFile(String recipient,String subject,String content,MultipartFile file){
    	//使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容  
    	MimeMessage msg = mailSender.createMimeMessage();
    	try {
    		//创建MimeMessageHelper对象，处理MimeMessage的辅助类  
        	MimeMessageHelper helper = new MimeMessageHelper(msg, true);  
        	//使用辅助类MimeMessage设定参数  
        	helper.setFrom(mailSender.getUsername());  
        	helper.setTo(recipient);  
        	helper.setSubject(subject);  
        	helper.setText(content);
        	//加入附件  
			helper.addAttachment(file.getOriginalFilename(), file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}  
    	// 发送邮件  
    	mailSender.send(msg);
    }
    
}

