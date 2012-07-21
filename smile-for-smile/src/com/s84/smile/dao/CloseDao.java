package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.SalesSummarySearchResultBean;

public interface CloseDao {

	public List<SalesSummarySearchResultBean> selectSalesSummary(Date dayFrom, Date dayTo);
}
