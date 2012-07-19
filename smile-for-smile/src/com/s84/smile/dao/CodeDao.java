package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.CodeBean;

public interface CodeDao {

	public List<CodeBean> selectByCategory(String category);
}
