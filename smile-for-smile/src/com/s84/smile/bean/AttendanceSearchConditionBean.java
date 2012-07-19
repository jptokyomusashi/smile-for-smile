package com.s84.smile.bean;

import java.util.Date;

import com.s84.smile.util.DateUtil;

public class AttendanceSearchConditionBean {

	private Date day;

	public AttendanceSearchConditionBean() {
		this.day = DateUtil.getDay(-300);
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
}
