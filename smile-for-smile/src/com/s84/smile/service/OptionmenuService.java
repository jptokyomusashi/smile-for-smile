package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.OptionmenuBean;

public interface OptionmenuService {

	public List<OptionmenuBean> selectAll();
	public OptionmenuBean selectByPrimaryKey(int optionmenuId);
}
