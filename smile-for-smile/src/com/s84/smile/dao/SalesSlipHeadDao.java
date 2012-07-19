package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.SalesSlipHeadBean;
import com.s84.smile.bean.SalesSlipSearchConditionBean;
import com.s84.smile.bean.SalesSlipSearchResultBean;

public interface SalesSlipHeadDao {

	public int insert(SalesSlipHeadBean salesSlipHeadBean);
	public int update(SalesSlipHeadBean salesSlipHeadBean);
	public SalesSlipHeadBean selectBySlipId(int slipId);
	public List<SalesSlipSearchResultBean> select(SalesSlipSearchConditionBean salesSlipSearchConditionBean);
}
