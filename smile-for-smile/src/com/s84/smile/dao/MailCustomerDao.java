package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.MailCustomerBean;

public interface MailCustomerDao {

	public MailCustomerBean selectByPrimarykey(int id);
	public List<MailCustomerBean> selectByMailAddress(String mailAddress);
	public List<MailCustomerBean> selectAll();
	public List<MailCustomerBean> selectNotDeleted();
	public void insert(MailCustomerBean mailCustomerBean);
	public void update(MailCustomerBean mailCustomerBean);
}