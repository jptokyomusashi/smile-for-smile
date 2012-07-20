package com.s84.smile.bean;

import java.util.Date;

public class SalesSummarySearchResultBean {

	private Date day;
	private String courseClassName;
	private String courseName;
	private Date startTime;
	private Date endTime;
	private int chargeEmployee;
	private int chargeShop;
	private int discountEmployee;
	private int discountShop;
	private String appointId;
	private String memberId;

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getCourseClassName() {
		return courseClassName;
	}

	public void setCourseClassName(String courseClassName) {
		this.courseClassName = courseClassName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public int getChargeEmployee() {
		return chargeEmployee;
	}

	public void setChargeEmployee(int chargeEmployee) {
		this.chargeEmployee = chargeEmployee;
	}

	public int getChargeShop() {
		return chargeShop;
	}

	public void setChargeShop(int chargeShop) {
		this.chargeShop = chargeShop;
	}

	public int getDiscountEmployee() {
		return discountEmployee;
	}

	public void setDiscountEmployee(int discountEmployee) {
		this.discountEmployee = discountEmployee;
	}

	public int getDiscountShop() {
		return discountShop;
	}

	public void setDiscountShop(int discountShop) {
		this.discountShop = discountShop;
	}

	public String getAppointId() {
		return appointId;
	}

	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
