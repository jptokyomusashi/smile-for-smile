package com.s84.smile.bean;

import java.util.Date;

public class OptionmenuBean {

	private Integer optionmenuId;
	private String name;
	private Integer charge;
	private Integer share;
	private Integer sort;
	private Integer deleted;
	private Date upDay;
	private String upEmployeeId;

	public Integer getOptionmenuId() {
		return optionmenuId;
	}

	public void setOptionmenuId(Integer optionmenuId) {
		this.optionmenuId = optionmenuId;
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

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
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
}
