package com.s84.smile.service;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.CloseBean;
import com.s84.smile.bean.CloseHeadBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSummarySearchResultBean;

public interface CloseService {

	public void insert(CloseBean closeBean);
	public void close(List<SalesSlipBean> salesSlipList, Date day, String employeeId, EmployeeBean employeeBean);
	public void closeCancel(Date day, String employeeId);
	public List<SalesSummarySearchResultBean> selectSalesSummary(Date dayFrom, Date dayTo);
	public CloseHeadBean selectBySalesSlipId(Integer salesSlipId);
	public CloseHeadBean selectByPaymentSlipId(Integer paymentSlipId);
}
