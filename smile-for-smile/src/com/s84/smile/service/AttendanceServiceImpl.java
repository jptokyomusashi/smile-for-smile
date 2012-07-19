package com.s84.smile.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.AttendanceBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.dao.AttendanceDao;
import com.s84.smile.util.DateUtil;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Override
	public int insert(AttendanceBean attendanceBean, EmployeeBean employeeBean) {
		attendanceBean.setUpDay(DateUtil.getDay(0));
		attendanceBean.setUpEmployeeId(employeeBean.getEmployeeId());
		return attendanceDao.insert(attendanceBean);
	}

	@Override
	public int delete(Date day, String employeeId, EmployeeBean employeeBean) {
		return attendanceDao.delete(day, employeeId);
	}

}
