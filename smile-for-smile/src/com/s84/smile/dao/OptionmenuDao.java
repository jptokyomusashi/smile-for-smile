package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.OptionmenuBean;

public interface OptionmenuDao {

	public List<OptionmenuBean> selectAll();
	public OptionmenuBean selectByPrimaryKey(int optionmenuId);
}
