package com.s84.smile.service;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.CloseSearchResultBean;
import com.s84.smile.bean.EmployeeBean;

public interface EmployeeService {

	public EmployeeBean selectByEmployeeIdAndPassword(String employeeId, String password);
	public EmployeeBean selectByEmployeeId(String employeeId);
	public List<EmployeeBean> selectAll();
	public List<CloseSearchResultBean> selectAttendanceClose(Date day);
	public void insert(EmployeeBean employeeBean);
	public void update(EmployeeBean employeeBean);
}
