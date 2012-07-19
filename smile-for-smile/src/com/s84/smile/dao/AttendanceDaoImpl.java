package com.s84.smile.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.AttendanceBean;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

	private static final String INSERT = "insert into attendance values(?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "delete from attendance where day = ? and employee_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(AttendanceBean attendanceBean) {
		return this.template.update(INSERT, attendanceBean.getDay(), attendanceBean.getEmployeeId(), attendanceBean.getStartTime(),
				attendanceBean.getEndTime(), attendanceBean.getUpDay(), attendanceBean.getUpEmployeeId());
	}

	@Override
	public int delete(Date day, String employeeId) {
		return this.template.update(DELETE, day, employeeId);
	}

}
