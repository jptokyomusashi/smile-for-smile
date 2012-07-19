package com.s84.smile.bean;

import java.util.Date;

public class CourseExtensionBean {

	private Integer courseClassId;
	private Integer courseExtensionId;
	private String name;
	private Integer charge;
	private Integer time;
	private Integer sort;
	private Integer deleted;
	private Date upDay;
	private String upEmployeeId;

	public Integer getCourseClassId() {
		return courseClassId;
	}

	public void setCourseClassId(Integer courseClassId) {
		this.courseClassId = courseClassId;
	}

	public Integer getCourseExtensionId() {
		return courseExtensionId;
	}

	public void setCourseExtensionId(Integer courseExtensionId) {
		this.courseExtensionId = courseExtensionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
