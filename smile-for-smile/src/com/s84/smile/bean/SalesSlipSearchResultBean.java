package com.s84.smile.bean;

import java.util.Date;

public class SalesSlipSearchResultBean {

	private int slipId;
	private Date day;
	private String employeeId;
	private String name;
	private String memberId;
	private Date startTime;
	private Date endTime;

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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
