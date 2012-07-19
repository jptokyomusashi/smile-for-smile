package com.s84.smile.bean;

import java.util.Date;

import com.s84.smile.util.DateUtil;

public class PaymentSlipSearchConditionBean {

	private Date dayFrom;
	private Date dayTo;
	private String payee;

	public PaymentSlipSearchConditionBean() {
		this.dayFrom = DateUtil.getDay(-300);
	}

	public Date getDayFrom() {
		return dayFrom;
	}

	public void setDayFrom(Date dayFrom) {
		this.dayFrom = dayFrom;
	}

	public Date getDayTo() {
		return dayTo;
	}

	public void setDayTo(Date dayTo) {
		this.dayTo = dayTo;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}
}
