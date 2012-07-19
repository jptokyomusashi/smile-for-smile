package com.s84.smile.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.EmployeeBean;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		EmployeeBean employeeBean = (EmployeeBean)command;

		if(!StringUtils.hasLength(employeeBean.getEmployeeId())) {
			errors.rejectValue("employeeId", "error.required");
		}
		if(!StringUtils.hasLength(employeeBean.getPassword())) {
			errors.rejectValue("password", "error.required");
		}
	}
}
