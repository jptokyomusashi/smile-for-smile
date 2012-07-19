package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.AppointBean;

public interface AppointService {

	public List<AppointBean> selectAll();
	public AppointBean selectByPrimaryKey(int appointId);
}
