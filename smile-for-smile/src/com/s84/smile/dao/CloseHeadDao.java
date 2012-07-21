package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.CloseHeadBean;

public interface CloseHeadDao {

	public int insert(CloseHeadBean closeHeadBean);
	public void delete(Date day, String employeeId);
	public List<CloseHeadBean> selectByDayAndEmployeeId(Date day, String employeeId);
	public CloseHeadBean selectBySalesSlipId(Integer salesSlipId);
	public CloseHeadBean selectByPaymentSlipId(Integer paymentSlipId);
}
