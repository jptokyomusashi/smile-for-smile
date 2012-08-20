package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.CloseSearchResultBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.EmployeeForMaintenanceBean;

public interface EmployeeDao {
	public EmployeeBean selectByEmployeeIdAndPassword(String employeeId, String password);
	public EmployeeBean selectByEmployeeId(String employeeId);
	public List<EmployeeBean> selectAll();
	public List<EmployeeForMaintenanceBean> selectAllForMaintenance();
	public List<CloseSearchResultBean> selectAttendanceClose(Date day);
	public void insert(EmployeeBean employeeBean);
	public void update(EmployeeBean employeeBean);
	public void delete(EmployeeBean employeeBean);
}
