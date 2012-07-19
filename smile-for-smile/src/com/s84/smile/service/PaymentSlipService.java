package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.PaymentSlipBean;
import com.s84.smile.bean.PaymentSlipSearchConditionBean;
import com.s84.smile.bean.PaymentSlipSearchResultBean;

public interface PaymentSlipService {

	public int insert(PaymentSlipBean paymentSlipBean, EmployeeBean employeeBean);
	public void update(PaymentSlipBean paymentSlipBean, EmployeeBean employeeBean);
	public List<PaymentSlipSearchResultBean> selectPaymentSlipHead(PaymentSlipSearchConditionBean paymentSlipSearchConditionBean);
	public PaymentSlipBean selectBySlipId(int slipId);
	public void delete(int slipId);
}
