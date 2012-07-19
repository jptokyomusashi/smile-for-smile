package com.s84.smile.bean;

import java.util.ArrayList;
import java.util.List;

public class PaymentSlipBean {

	private PaymentSlipHeadBean paymentSlipHeadBean;
	private List<PaymentSlipDetailBean> paymentSlipDetailList = new ArrayList<PaymentSlipDetailBean>();

	public void addDetail(PaymentSlipDetailBean paymentSlipDetailBean) {
		paymentSlipDetailList.add(paymentSlipDetailBean);
	}

	public PaymentSlipHeadBean getPaymentSlipHeadBean() {
		return paymentSlipHeadBean;
	}

	public void setPaymentSlipHeadBean(PaymentSlipHeadBean paymentSlipHeadBean) {
		this.paymentSlipHeadBean = paymentSlipHeadBean;
	}

	public List<PaymentSlipDetailBean> getPaymentSlipDetailList() {
		return paymentSlipDetailList;
	}

	public void setPaymentSlipDetailList(List<PaymentSlipDetailBean> paymentSlipDetailList) {
		this.paymentSlipDetailList = paymentSlipDetailList;
	}
}
