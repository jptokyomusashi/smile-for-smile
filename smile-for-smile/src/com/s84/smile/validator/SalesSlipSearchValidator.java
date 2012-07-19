package com.s84.smile.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.SalesSlipSearchConditionBean;

public class SalesSlipSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesSlipSearchConditionBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		//未実装
	}
}
