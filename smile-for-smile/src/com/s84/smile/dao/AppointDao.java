package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.AppointBean;

public interface AppointDao {

	public List<AppointBean> selectAll();
	public AppointBean selectByPrimaryKey(int appointId);
}
