package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import com.s84.smile.bean.AttendanceSearchResultBean;
import com.s84.smile.bean.EmployeeBean;

public interface EmployeeDao {
	public EmployeeBean selectByEmployeeIdAndPassword(String employeeId, String password);
	public EmployeeBean selectByEmployeeId(String employeeId);
	public List<EmployeeBean> selectAll();
	public List<AttendanceSearchResultBean> selectAttendanceClose(Date day);
	public void insert(EmployeeBean employeeBean);
	public void update(EmployeeBean employeeBean);
}
