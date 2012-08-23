package com.s84.smile.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.MailSendBean;

@Component
public class MailSendValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MailSendBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		MailSendBean mailSendBean = (MailSendBean)command;
		
		if ("".equals(mailSendBean.getTitle().trim())) {
			errors.rejectValue("title", "error.required.mail.title");
		}

		if ("".equals(mailSendBean.getBody().trim())) {
			errors.rejectValue("body", "error.required.mail.body");
		}

	}

}
