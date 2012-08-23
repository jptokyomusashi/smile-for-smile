package com.s84.smile.service;

import java.util.Date;
import java.util.List;
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

import com.s84.smile.bean.MailCustomerBean;
import com.s84.smile.bean.MailSettingBean;

@Service
public class MailSendServiceImpl implements MailSendService {

	@Autowired
	MailSettingService mailSettingService;
	@Autowired
	MailCustomerService mailCustomerService;

	@Override
	public void send(String title, String body) throws Exception {
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
		InternetAddress[] from = {new InternetAddress(mailSettingBean.getSendAddress(), mailSettingBean.getSendName())};
		msg.addFrom(from);
		
		List<MailCustomerBean> mailCustomerList = mailCustomerService.selectNotDeleted();
		InternetAddress[] bcc = new InternetAddress[mailCustomerList.size()];
		for (int i = 0; i < mailCustomerList.size(); i++) {
			bcc[i] = new InternetAddress(mailCustomerList.get(i).getMailAddress());
		}
		msg.setRecipients(Message.RecipientType.BCC, bcc);
	
		msg.setRecipients(Message.RecipientType.TO, from);
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