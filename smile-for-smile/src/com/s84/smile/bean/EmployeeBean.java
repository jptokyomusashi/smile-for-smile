package com.s84.smile.bean;

import java.util.Date;

public class EmployeeBean {

	private String employeeId;
	private String password;
	private String name;
	private String email;
	private int share;
	private int authority;
	private Integer resigned;
	private Integer sort;
	private Date upDay;
	private String upEmployeeId;

	public EmployeeBean() {
		
	}

	public EmployeeBean(String employeeId, String password, String name, String email,
				int share, int authority, int resigned, Date upDay, String upEmployeeId) {
		this.setEmployeeId(employeeId);
		this.setPassword(password);
		this.setName(name);
		this.setEmail(email);
		this.setShare(share);
		this.setAuthority(authority);
		this.setResigned(resigned);
		this.setUpDay(upDay);
		this.setUpEmployeeId(upEmployeeId);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public Date getUpDay() {
		return upDay;
	}

	public void setUpDay(Date upDay) {
		this.upDay = upDay;
	}

	public Integer getResigned() {
		return resigned;
	}

	public void setResigned(Integer resigned) {
		this.resigned = resigned;
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
}
