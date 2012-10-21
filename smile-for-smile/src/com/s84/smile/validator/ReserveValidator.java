package com.s84.smile.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.ReserveBean;

public class ReserveValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ReserveBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ReserveBean reserveBean = (ReserveBean)command;

		//名前
		if ("".equals(reserveBean.getName())) {
			errors.rejectValue("name", "error.required.reserve.name");
		}
		//メアド
		if ("".equals(reserveBean.getMail())) {
			errors.rejectValue("mail", "error.required.reserve.mail");
		}
		//メアド(形式チェック)
		if (!"".equals(reserveBean.getMail())) {
			String check = "^[_A-Za-z0-9-\\+\\*\\.]+(\\.[_A-Za-z0-9-\\+\\*\\.]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
			if (!reserveBean.getMail().matches(check)) {
				errors.rejectValue("mail", "error.reserve.mail");
			}
		}
		//電話
		if ("".equals(reserveBean.getPhone())) {
			errors.rejectValue("phone", "error.required.reserve.phone");
		}
		//人数
		if ("".equals(reserveBean.getAmount())) {
			errors.rejectValue("amount", "error.required.reserve.amount");
		}
		//予約日
		if ("".equals(reserveBean.getDay())) {
			errors.rejectValue("day", "error.required.reserve.day");
		}
		//予約時間
		if ("".equals(reserveBean.getTime())) {
			errors.rejectValue("time", "error.required.reserve.time");
		}

	}

}
