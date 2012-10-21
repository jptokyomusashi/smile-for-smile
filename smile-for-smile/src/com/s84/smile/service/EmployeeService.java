package com.s84.smile.service;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.CloseSearchResultBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.EmployeeForMaintenanceBean;

public interface EmployeeService {

	public EmployeeBean selectByEmployeeIdAndPassword(String employeeId, String password);
	public EmployeeBean selectByEmployeeId(String employeeId);
	public List<EmployeeBean> selectAll();
	public List<EmployeeBean> selectManager();
	public List<EmployeeForMaintenanceBean> selectAllForMaintenance();
	public List<CloseSearchResultBean> selectAttendanceClose(Date day);
	public void insert(EmployeeBean employeeBean, String loginEmployeeId);
	public void update(EmployeeBean employeeBean, String loginEmployeeId);
	public void delete(EmployeeBean employeeBean);
}