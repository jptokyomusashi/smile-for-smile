package com.s84.smile.bean;

import java.util.Date;

public class CourseClassBean {

	private Integer courseClassId;
	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
