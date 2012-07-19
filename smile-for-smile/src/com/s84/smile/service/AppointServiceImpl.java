package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.AppointBean;
import com.s84.smile.dao.AppointDao;

@Service
public class AppointServiceImpl implements AppointService {

	@Autowired
	private AppointDao appointDao;

	@Override
	public List<AppointBean> selectAll() {
		return appointDao.selectAll();
	}

	@Override
	public AppointBean selectByPrimaryKey(int appointId) {
		return appointDao.selectByPrimaryKey(appointId);
	}
}
