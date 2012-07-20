package com.s84.smile.bean;

import java.util.Date;

import com.s84.smile.util.DateUtil;

public class CloseSearchConditionBean {

	private Date day;

	public CloseSearchConditionBean() {
		this.day = DateUtil.getDay(-300);
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
}
