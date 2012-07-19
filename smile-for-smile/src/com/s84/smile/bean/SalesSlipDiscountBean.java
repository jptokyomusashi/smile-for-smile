package com.s84.smile.bean;

import java.util.Date;

public class SalesSlipDiscountBean {

	private Integer slipId;
	private Integer detailId;
	private Integer discountId;
	private Integer charge;
	private Date upDay;
	private String upEmployeeId;

	public Integer getSlipId() {
		return slipId;
	}

	public void setSlipId(Integer slipId) {
		this.slipId = slipId;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
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
