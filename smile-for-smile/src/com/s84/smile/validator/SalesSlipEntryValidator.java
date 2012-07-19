package com.s84.smile.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSlipHeadBean;

public class SalesSlipEntryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesSlipBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		SalesSlipBean salesSlipBean = (SalesSlipBean)command;
		
		SalesSlipHeadBean salesSlipHeadBean = salesSlipBean.getSalesSlipHeadBean();

		//日付
		if (salesSlipHeadBean.getDay() == null) {
			errors.rejectValue("salesSlipHeadBean.day", "error.required.sales.day");
		}
		//従業員
		if (!StringUtils.hasLength(salesSlipHeadBean.getEmployeeId())) {
			errors.rejectValue("salesSlipHeadBean.employeeId", "error.required.sales.employeeId");
		}		
		//時刻
		if (salesSlipHeadBean.getStartTime() == null) {
			errors.rejectValue("salesSlipHeadBean.startTime", "error.required.sales.startTime");
		}
		if (salesSlipHeadBean.getEndTime() == null) {
			errors.rejectValue("salesSlipHeadBean.endTime", "error.required.sales.endTime");
		}
		//コース分類
		if (salesSlipHeadBean.getCourseClassId() == null) {
			errors.rejectValue("salesSlipHeadBean.courseClassId", "error.required.sales.courseClass");
		}
		//コース
		if (salesSlipHeadBean.getCourseId() == null) {
			errors.rejectValue("salesSlipHeadBean.courseId", "error.required.sales.course");
		}

	}
}
