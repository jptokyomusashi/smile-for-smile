package com.s84.smile.bean;

import java.util.Date;

import com.s84.smile.util.DateUtil;

public class SalesSummarySearchConditionBean {

	private Date dayFrom;
	private Date dayTo;

	public SalesSummarySearchConditionBean() {
		dayFrom = DateUtil.getDay(0);
		dayTo = DateUtil.getLastDayOfMonth();
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
}
