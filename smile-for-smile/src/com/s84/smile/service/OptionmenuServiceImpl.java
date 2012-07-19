package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.OptionmenuBean;
import com.s84.smile.dao.OptionmenuDao;

@Service
public class OptionmenuServiceImpl implements OptionmenuService {

	@Autowired
	private OptionmenuDao optionmenuDao;

	@Override
	public List<OptionmenuBean> selectAll() {
		return optionmenuDao.selectAll();
	}

	@Override
	public OptionmenuBean selectByPrimaryKey(int optionmenuId) {
		return optionmenuDao.selectByPrimaryKey(optionmenuId);
	}
}
