package com.s84.smile.bean;

import java.util.Date;

public class PaymentSlipSearchResultBean {

	private int slipId;
	private Date day;
	private String payee;
	private Integer totalCharge;

	public int getSlipId() {
		return slipId;
	}

	public void setSlipId(int slipId) {
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

	public Integer getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Integer totalCharge) {
		this.totalCharge = totalCharge;
	}
}
