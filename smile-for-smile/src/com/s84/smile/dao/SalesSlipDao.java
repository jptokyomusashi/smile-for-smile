package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.SalesSlipBean;

public interface SalesSlipDao {

	public List<SalesSlipBean> selectByDayAndEmployeeId(Date day, String employeeId);
}
