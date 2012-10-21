package com.s84.smile.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.MailSettingBean;

@Service
public class MailSendServiceImpl implements MailSendService {

	@Autowired
	MailSettingService mailSettingService;
	@Autowired
	MailCustomerService mailCustomerService;

	@Override
	public void send(String title, String body, InternetAddress[] from, InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc) throws Exception {
		MailSettingBean mailSettingBean = mailSettingService.select();
		MyAuth myAuth = new MyAuth();
		myAuth.userId = mailSettingBean.getUserId();
		myAuth.password = mailSettingBean.getPassword();

		Properties prop = new Properties();
		prop.put("mail.smtp.host", mailSettingBean.getSmtp());
		prop.put("mail.smtp.port", mailSettingBean.getPort());
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(prop, myAuth);

		MimeMessage msg = new MimeMessage(session);
		msg.addFrom(from);	
		msg.setRecipients(Message.RecipientType.TO, to);
		if (cc != null) {
			msg.setRecipients(Message.RecipientType.CC, bcc);
		}
		if (bcc != null) {
			msg.setRecipients(Message.RecipientType.BCC, bcc);
		}
		msg.setSubject(title, "ISO-2022-JP");
		msg.setText(body, "ISO-2022-JP");
		msg.setSentDate(new Date());
		Transport.send(msg);
	}

	private class MyAuth extends Authenticator {
		String userId;
		String password;
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userId, password);
		}
	}  
}