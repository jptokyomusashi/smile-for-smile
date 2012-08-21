package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.MailCustomerBean;

public interface MailCustomerService {

	public MailCustomerBean selectByPrimarykey(int id);
	public List<MailCustomerBean> selectByMailAddress(String mailAddress);
	public List<MailCustomerBean> selectAll();
	public void insert(MailCustomerBean mailCustomerBean);
	public void update(MailCustomerBean mailCustomerBean);
}