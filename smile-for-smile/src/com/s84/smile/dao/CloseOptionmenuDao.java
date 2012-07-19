package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.CloseOptionmenuBean;

public interface CloseOptionmenuDao {

	public int insert(CloseOptionmenuBean closeOptionmenuBean);
	public int delete(List<Integer> closeIdList);
}
