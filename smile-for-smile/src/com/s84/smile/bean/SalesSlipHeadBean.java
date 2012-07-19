package com.s84.smile.bean;

import java.util.Date;

import com.s84.smile.util.DateUtil;

public class SalesSlipHeadBean {

	private Integer slipId;
	private Date day;
	private String employeeId;
	private String memberId;
	private Date startTime;
	private Date endTime;
	private Integer courseClassId;
	private Integer courseId;
	private Integer courseCharge;
	private Integer courseExtensionId;
	private Integer courseExtensionCharge;
	private Integer appointId;
	private Integer appointCharge;	
	private Date upDay;
	private String upEmployeeId;

	public SalesSlipHeadBean() {
		this.day = DateUtil.getDay(-300);
		this.endTime = DateUtil.getTime(-10);
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public Integer getCourseClassId() {
		return courseClassId;
	}

	public void setCourseClassId(Integer courseClassId) {
		this.courseClassId = courseClassId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseCharge() {
		return courseCharge;
	}

	public void setCourseCharge(Integer courseCharge) {
		this.courseCharge = courseCharge;
	}

	public Integer getCourseExtensionId() {
		return courseExtensionId;
	}

	public void setCourseExtensionId(Integer courseExtensionId) {
		this.courseExtensionId = courseExtensionId;
	}

	public Integer getCourseExtensionCharge() {
		return courseExtensionCharge;
	}

	public void setCourseExtensionCharge(Integer courseExtensionCharge) {
		this.courseExtensionCharge = courseExtensionCharge;
	}

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	public Integer getAppointCharge() {
		return appointCharge;
	}

	public void setAppointCharge(Integer appointCharge) {
		this.appointCharge = appointCharge;
	}
}
