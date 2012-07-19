package com.s84.smile.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.PaymentSlipSearchConditionBean;

public class PaymentSlipSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PaymentSlipSearchConditionBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		//未実装
	}

}
