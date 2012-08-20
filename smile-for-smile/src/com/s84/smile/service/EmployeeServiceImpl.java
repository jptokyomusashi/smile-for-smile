package com.s84.smile.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.CloseSearchResultBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.EmployeeForMaintenanceBean;
import com.s84.smile.dao.EmployeeDao;
import com.s84.smile.util.DateUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public EmployeeBean selectByEmployeeIdAndPassword(String employeeId, String password) {
		return employeeDao.selectByEmployeeIdAndPassword(employeeId, password);
	}

	@Override
	public EmployeeBean selectByEmployeeId(String employeeId) {
		return employeeDao.selectByEmployeeId(employeeId);
	}

	@Override
	public List<EmployeeBean> selectAll() {
		return employeeDao.selectAll();
	}

	@Override
	public List<EmployeeForMaintenanceBean> selectAllForMaintenance() {
		return employeeDao.selectAllForMaintenance();		
	}

	@Override
	public List<CloseSearchResultBean> selectAttendanceClose(Date day) {
		return employeeDao.selectAttendanceClose(day);		
	}

	@Override
	public void insert(EmployeeBean employeeBean, String loginEmployeeId) {
		employeeBean.setUpDay(DateUtil.getDay(0));
		employeeBean.setUpEmployeeId(loginEmployeeId);
		employeeDao.insert(employeeBean);
	}

	@Override
	public void update(EmployeeBean employeeBean, String loginEmployeeId) {
		employeeBean.setUpDay(DateUtil.getDay(0));
		employeeBean.setUpEmployeeId(loginEmployeeId);
		employeeDao.update(employeeBean);
	}

	@Override
	public void delete(EmployeeBean employeeBean) {
		employeeDao.delete(employeeBean);
	}
}