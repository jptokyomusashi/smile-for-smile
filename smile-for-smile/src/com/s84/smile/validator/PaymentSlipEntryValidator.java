package com.s84.smile.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.PaymentSlipBean;
import com.s84.smile.bean.PaymentSlipDetailBean;
import com.s84.smile.bean.PaymentSlipHeadBean;

public class PaymentSlipEntryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PaymentSlipBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		PaymentSlipBean paymentSlipBean = (PaymentSlipBean)command;
		
		PaymentSlipHeadBean paymentSlipHeadBean = paymentSlipBean.getPaymentSlipHeadBean();
		List<PaymentSlipDetailBean> paymentSlipDetailList = paymentSlipBean.getPaymentSlipDetailList();

		//日付
		if (paymentSlipHeadBean.getDay() == null) {
			errors.rejectValue("paymentSlipHeadBean.day", "error.required.payment.day");
		}
		//支払先
		if ("".equals(paymentSlipHeadBean.getPayee().trim())) {
			errors.rejectValue("paymentSlipHeadBean.payee", "error.required.payment.payee");
		}

		//明細
		int index = -1;
		int detailCount = 0;
		for (PaymentSlipDetailBean paymentSlipDetailBean : paymentSlipDetailList) {
			index++;
			if (!(paymentSlipDetailBean.getAccount() == null && "".equals(paymentSlipDetailBean.getName().trim()) && paymentSlipDetailBean.getUnitPrice() == null &&
					paymentSlipDetailBean.getAmount() == null && "".equals(paymentSlipDetailBean.getComment().trim()))) {

				if ("".equals(paymentSlipDetailBean.getName().trim())) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].name", "error.required.payment");
				}
				if (paymentSlipDetailBean.getUnitPrice() == null) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].unitPrice", "error.required.payment.unitPrice");
				}
				if (paymentSlipDetailBean.getUnitPrice() != null && paymentSlipDetailBean.getUnitPrice() > 100000000) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].unitPrice", "error.required.payment.max.unitPrice");
				}
				if (paymentSlipDetailBean.getUnitPrice() != null && paymentSlipDetailBean.getUnitPrice() < -100000000) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].unitPrice", "error.required.payment.min.unitPrice");
				}
				if (paymentSlipDetailBean.getAmount() == null) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].amount", "error.required.payment.amount");
				}
				if (paymentSlipDetailBean.getAmount() != null && paymentSlipDetailBean.getAmount() > 32000) {	
					errors.rejectValue("paymentSlipDetailList[" + index + "].amount", "error.required.payment.max.amount");
				}
				if (paymentSlipDetailBean.getAmount() != null && paymentSlipDetailBean.getAmount() < -32000) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].amount", "error.required.payment.min.amount");
				}
				if (paymentSlipDetailBean.getAccount() == null) {
					errors.rejectValue("paymentSlipDetailList[" + index + "].account", "error.required.payment.account");
				}
				detailCount++;
			}
		}
		if (detailCount == 0) {
			errors.reject("error.required.payment.detail");
		}

	}

}
