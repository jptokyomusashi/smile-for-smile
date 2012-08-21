package com.s84.smile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.MailSettingBean;
import com.s84.smile.dao.MailSettingDao;

@Service
public class MailSettingServiceImpl implements MailSettingService {

	@Autowired
	MailSettingDao mailSettingDao;
	
	@Override
	public void insert(MailSettingBean mailSettingBean) {
		mailSettingDao.insert(mailSettingBean);
	}

	@Override
	public void delete() {
		mailSettingDao.delete();
	}

	@Override
	public MailSettingBean select() {
		return mailSettingDao.select();
	}
}