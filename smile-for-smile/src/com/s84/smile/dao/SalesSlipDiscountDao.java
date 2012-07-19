package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.SalesSlipDiscountBean;

public interface SalesSlipDiscountDao {

	public int delete(int slipId);
	public int delete(int slipId, int detailId);
	public int insert(SalesSlipDiscountBean salesSlipDiscountBean);
	public List<SalesSlipDiscountBean> selectBySlipId(int slipId);
}
