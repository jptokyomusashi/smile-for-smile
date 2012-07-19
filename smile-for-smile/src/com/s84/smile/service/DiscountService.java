package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.DiscountBean;

public interface DiscountService {

	public List<DiscountBean> selectAll();
	public DiscountBean selectByPrimaryKey(int discountId);
}
