package com.s84.smile.bean;

import java.util.Date;

import com.s84.smile.util.DateUtil;

public class PaymentSlipHeadBean {

	private Integer slipId;
	private Date day;
	private String payee;
	private Date upDay;
	private String upEmployeeId;

	public PaymentSlipHeadBean() {
		this.day = DateUtil.getDay(-300);
	}

	public Integer getSlipId() {
		return slipId;
	}

	public void setSlipId(Integer slipId) {
		this.slipId = slipId;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public Date getUpDay() {
		return upDay;
	}

	public void setUpDay(Date upDay) {
		this.upDay = upDay;
	}

	public String getUpEmployeeId() {
		return upEmployeeId;
	}

	public void setUpEmployeeId(String upEmployeeId) {
		this.upEmployeeId = upEmployeeId;
	}	
}
