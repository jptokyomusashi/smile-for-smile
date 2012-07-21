package com.s84.smile.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.SalesSummarySearchConditionBean;

public class SalesSummarySearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesSummarySearchConditionBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		SalesSummarySearchConditionBean salesSummarySearchConditionBean = (SalesSummarySearchConditionBean)command;

		if (salesSummarySearchConditionBean.getDayFrom() == null) {
			errors.rejectValue("dayFrom", "error.required.salesSummary.dayFrom");
		}
		if (salesSummarySearchConditionBean.getDayTo() == null) {
			errors.rejectValue("dayTo", "error.required.salesSummary.dayTo");
		}
	}
}
