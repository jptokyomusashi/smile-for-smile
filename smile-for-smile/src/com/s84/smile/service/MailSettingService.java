package com.s84.smile.service;

import com.s84.smile.bean.MailSettingBean;

public interface MailSettingService {

	public void insert(MailSettingBean mailSettingBean);
	public void delete();
	public MailSettingBean select();
}