package com.s84.smile.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.AttendanceSearchConditionBean;

public class AttendanceSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AttendanceSearchConditionBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		AttendanceSearchConditionBean attendanceSearchConditionBean = (AttendanceSearchConditionBean)command;

		if (attendanceSearchConditionBean.getDay() == null) {
			errors.rejectValue("day", "error.required.attendance.day");
		}
	}

}
