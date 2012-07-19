package com.s84.smile.dao;

import java.util.Date;

import com.s84.smile.bean.AttendanceBean;

public interface AttendanceDao {

	public int insert(AttendanceBean attendanceBean);
	public int delete(Date day, String employeeId);
}
