package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.CloseDiscountBean;

public interface CloseDiscountDao {

	public int insert(CloseDiscountBean closeDiscountBean);
	public int delete(List<Integer> closeIdList);
}
