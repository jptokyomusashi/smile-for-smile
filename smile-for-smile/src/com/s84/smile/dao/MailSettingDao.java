package com.s84.smile.dao;

import com.s84.smile.bean.MailSettingBean;

public interface MailSettingDao {

	public MailSettingBean select();
	public void insert(MailSettingBean mailSettingBean);
	public void delete();
}