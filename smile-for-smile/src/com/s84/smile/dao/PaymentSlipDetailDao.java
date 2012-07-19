package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.PaymentSlipDetailBean;

public interface PaymentSlipDetailDao {

	public int delete(int slipId);
	public int delete(int slipId, int detailId);
	public int insert(PaymentSlipDetailBean paymentSlipDetailBean);
	public List<PaymentSlipDetailBean> selectBySlipId(int slipId);
}
