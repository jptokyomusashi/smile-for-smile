package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.AttendanceSearchResultBean;
import com.s84.smile.bean.EmployeeBean;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final String SELECT_BY_EMPLOYEEID_AND_PASSWORD = "select * from employee where employee_id = ? and password = ? and resigned is null";
	private static final String SELECT_BY_EMPLOYEEID = "select * from employee where employee_id = ? and resigned is null";
	private static final String SELECT_ALL = "select * from employee where authority < 10 and resigned is null order by sort";
	private static final String SELECT_ATTENDANCE_CLOSE = "select e.employee_id, e.name," +
														"         case when c.day is null then false else true end closed from employee e" +
														"    left outer join (select day, employee_id from close_head group by day, employee_id) c" +
														"      on e.employee_id = c.employee_id and c.day = ?" +
														"   where authority < 10 and resigned is null order by sort";
	//private static final String INSERT = "";
	//private static final String UPDATE = "";
	
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public EmployeeBean selectByEmployeeIdAndPassword(String employeeId, String password) {
		RowMapper<EmployeeBean> mapper = new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class);
		return this.template.queryForObject(SELECT_BY_EMPLOYEEID_AND_PASSWORD, mapper, employeeId, password);
	}

	@Override
	public EmployeeBean selectByEmployeeId(String employeeId) {
		RowMapper<EmployeeBean> mapper = new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class);
		return this.template.queryForObject(SELECT_BY_EMPLOYEEID, mapper, employeeId);
	}
	
	@Override
	public List<EmployeeBean> selectAll() {
		RowMapper<EmployeeBean> mapper = new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}

	@Override
	public List<AttendanceSearchResultBean> selectAttendanceClose(Date day) {
		RowMapper<AttendanceSearchResultBean> mapper = new BeanPropertyRowMapper<AttendanceSearchResultBean>(AttendanceSearchResultBean.class);
		return this.template.query(SELECT_ATTENDANCE_CLOSE, mapper, day);
	}

	@Override
	public void insert(EmployeeBean employeeBean) {
		//
	}

	@Override
	public void update(EmployeeBean employeeBean) {
		//
	}

}
