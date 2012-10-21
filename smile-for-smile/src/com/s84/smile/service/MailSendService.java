package com.s84.smile.service;

import javax.mail.internet.InternetAddress;

public interface MailSendService {

	public void send(String title, String body, InternetAddress[] from, InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc) throws Exception;
}