package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.SalesSlipOptionmenuBean;

public interface SalesSlipOptionmenuDao {

	public int delete(int slipId);
	public int delete(int slipId, int detailId);
	public int insert(SalesSlipOptionmenuBean salesSlipOptionmenuBean);
	public List<SalesSlipOptionmenuBean> selectBySlipId(int slipId);
}
