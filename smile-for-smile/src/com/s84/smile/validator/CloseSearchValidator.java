package com.s84.smile.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.CloseSearchConditionBean;

public class CloseSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CloseSearchConditionBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		CloseSearchConditionBean attendanceSearchConditionBean = (CloseSearchConditionBean)command;

		if (attendanceSearchConditionBean.getDay() == null) {
			errors.rejectValue("day", "error.required.attendance.day");
		}
	}

}
