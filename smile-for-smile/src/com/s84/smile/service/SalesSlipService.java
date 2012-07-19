package com.s84.smile.service;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSlipSearchConditionBean;
import com.s84.smile.bean.SalesSlipSearchResultBean;

public interface SalesSlipService {

	public void insert(SalesSlipBean salesSlipBean, EmployeeBean employeeBean);
	public void update(SalesSlipBean salesSlipBean, EmployeeBean employeeBean);
	public List<SalesSlipSearchResultBean> selectSalesSlipHead(SalesSlipSearchConditionBean salesSlipSearchConditionBean);
	public SalesSlipBean selectBySlipId(int slipId);
	public List<SalesSlipBean> selectByDayAndEmployeeId(Date day, String employeeId);
}
