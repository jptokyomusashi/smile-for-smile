package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.MailCustomerBean;
import com.s84.smile.dao.MailCustomerDao;
import com.s84.smile.util.DateUtil;

@Service
public class MailCustomerServiceImpl implements MailCustomerService {

	@Autowired
	MailCustomerDao mailCustomerDao;

	@Override
	public void insert(MailCustomerBean mailCustomerBean) {
		mailCustomerBean.setUpDay(DateUtil.getDay(0));
		mailCustomerBean.setUpEmployeeId("merumaga");

		mailCustomerDao.insert(mailCustomerBean);
	}

	@Override
	public void update(MailCustomerBean mailCustomerBean) {
		mailCustomerBean.setUpDay(DateUtil.getDay(0));
		mailCustomerBean.setUpEmployeeId("merumaga");

		mailCustomerDao.update(mailCustomerBean);
	}
	
	@Override
	public List<MailCustomerBean> selectByMailAddress(String mailAddress) {
		return mailCustomerDao.selectByMailAddress(mailAddress);
	}

	@Override
	public List<MailCustomerBean> selectAll() {
		return mailCustomerDao.selectAll();
	}

	@Override
	public MailCustomerBean selectByPrimarykey(int id) {
		return mailCustomerDao.selectByPrimarykey(id);
	}
}