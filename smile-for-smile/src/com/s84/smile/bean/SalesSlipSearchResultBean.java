package com.s84.smile.bean;

import java.util.Date;

public class SalesSlipSearchResultBean {

	private int slipId;
	private Integer closeSlipId;
	private Date day;
	private String employeeId;
	private String name;
	private Date startTime;
	private Date endTime;
	private String memberId;
	private Integer appointId;
	private Integer totalCharge;

	public Integer getCloseSlipId() {
		return closeSlipId;
	}

	public void setCloseSlipId(Integer closeSlipId) {
		this.closeSlipId = closeSlipId;
	}

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	public Integer getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Integer totalCharge) {
		this.totalCharge = totalCharge;
	}

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
