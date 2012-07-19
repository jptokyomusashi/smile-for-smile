package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.PaymentSlipHeadBean;
import com.s84.smile.bean.PaymentSlipSearchConditionBean;
import com.s84.smile.bean.PaymentSlipSearchResultBean;

public interface PaymentSlipHeadDao {

	public int insert(PaymentSlipHeadBean paymentSlipHeadBean);
	public int update(PaymentSlipHeadBean paymentSlipHeadBean);
	public PaymentSlipHeadBean selectBySlipId(int slipId);
	public List<PaymentSlipSearchResultBean> select(PaymentSlipSearchConditionBean paymentSlipSearchConditionBean);
	public int delete(int slipId);
}
