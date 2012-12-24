package com.s84.smile.bean;

import java.util.Date;

public class PaymentSummaryConditionBean {

	private Date dayFrom;
	private Date dayTo;
	private int option;

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

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
}
