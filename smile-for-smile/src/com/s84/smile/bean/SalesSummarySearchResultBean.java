package com.s84.smile.bean;

import java.util.Date;

public class SalesSummarySearchResultBean {

	private Date day;
	private String employeeName;
	private String courseClassName;
	private String courseName;
	private Date startTime;
	private Date endTime;
	private Integer chargeEmployee;
	private Integer chargeShop;
	private Integer discountEmployee;
	private Integer discountShop;
	private Integer tax;
	private Integer appointId;
	private String memberId;

	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public Integer getChargeEmployee() {
		return chargeEmployee;
	}

	public void setChargeEmployee(Integer chargeEmployee) {
		this.chargeEmployee = chargeEmployee;
	}

	public Integer getChargeShop() {
		return chargeShop;
	}

	public void setChargeShop(Integer chargeShop) {
		this.chargeShop = chargeShop;
	}

	public Integer getDiscountEmployee() {
		return discountEmployee;
	}

	public void setDiscountEmployee(Integer discountEmployee) {
		this.discountEmployee = discountEmployee;
	}

	public Integer getDiscountShop() {
		return discountShop;
	}

	public void setDiscountShop(Integer discountShop) {
		this.discountShop = discountShop;
	}

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
