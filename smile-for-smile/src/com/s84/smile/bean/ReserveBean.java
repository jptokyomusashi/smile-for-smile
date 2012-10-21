package com.s84.smile.bean;

public class ReserveBean {

	private String name;
	private String mail;
	private String phone;
	private String amount;
	private String day;
	private String time;
	private String notes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ReserveBean copy() {
		ReserveBean reserveBean = new ReserveBean();
		reserveBean.setName(this.getName());
		reserveBean.setMail(this.getMail());
		reserveBean.setPhone(this.getPhone());
		reserveBean.setAmount(this.getAmount());
		reserveBean.setDay(this.getDay());
		reserveBean.setTime(this.getTime());
		reserveBean.setNotes(this.getNotes());

		return reserveBean;
	}
}
