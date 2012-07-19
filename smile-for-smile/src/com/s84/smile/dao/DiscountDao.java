package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.DiscountBean;

public interface DiscountDao {

	public List<DiscountBean> selectAll();
	public DiscountBean selectByPrimaryKey(int discountId);
}
