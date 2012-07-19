package com.s84.smile.service;

import java.util.Date;

import com.s84.smile.bean.AttendanceBean;
import com.s84.smile.bean.EmployeeBean;

public interface AttendanceService {

	public int insert(AttendanceBean attendanceBean, EmployeeBean employeeBean);
	public int delete(Date day, String employeeId, EmployeeBean employeeBean);
}
