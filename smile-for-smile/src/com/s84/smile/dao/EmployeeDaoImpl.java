package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CloseSearchResultBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.EmployeeForMaintenanceBean;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final String SELECT_BY_EMPLOYEEID_AND_PASSWORD = "select * from employee where employee_id = ? and password = ? and resigned = 0";
	//private static final String SELECT_BY_EMPLOYEEID = "select * from employee where employee_id = ? and resigned = 0";
	private static final String SELECT_BY_EMPLOYEEID = "select * from employee where employee_id = ?";
	private static final String SELECT_ALL = "select * from employee where authority < 10 order by sort";
	private static final String SELECT_ALL_FOR_MAINTENANCE = "select e.*, a.label resigned_name, b.label authority_name from employee e" +
																		"  left outer join code a" +
																		"    on a.category = 'resigned' and a.code = e.resigned" +
																		"  left outer join code b" +
																		"    on b.category = 'authority' and b.code = e.authority" +
																		" order by sort";
	private static final String SELECT_CLOSE = "select e.employee_id, e.name," +
													"         case when c.day is null then false else true end closed from employee e" +
													"    left outer join (select day, employee_id from close_head group by day, employee_id) c" +
													"      on e.employee_id = c.employee_id and c.day = ?" +
													"   where authority < 10 and resigned is null order by sort";
	private static final String INSERT = "insert into employee values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update employee set password = ?, name = ?, email = ?, share = ?, authority = ?, sort = ?, resigned = ?, up_day = ?, up_employee_id = ? where employee_id = ?";
	private static final String DELETE = "delete from employee where employee_id = ?";
	
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
	public List<EmployeeForMaintenanceBean> selectAllForMaintenance() {
		RowMapper<EmployeeForMaintenanceBean> mapper = new BeanPropertyRowMapper<EmployeeForMaintenanceBean>(EmployeeForMaintenanceBean.class);
		return this.template.query(SELECT_ALL_FOR_MAINTENANCE, mapper);
	}

	@Override
	public List<CloseSearchResultBean> selectAttendanceClose(Date day) {
		RowMapper<CloseSearchResultBean> mapper = new BeanPropertyRowMapper<CloseSearchResultBean>(CloseSearchResultBean.class);
		return this.template.query(SELECT_CLOSE, mapper, day);
	}

	@Override
	public void insert(EmployeeBean employeeBean) {
		this.template.update(INSERT, employeeBean.getEmployeeId(), employeeBean.getPassword(), employeeBean.getName(), employeeBean.getEmail(), employeeBean.getShare(),
				employeeBean.getAuthority(), employeeBean.getSort(), employeeBean.getResigned(), employeeBean.getUpDay(), employeeBean.getUpEmployeeId());
	}

	@Override
	public void update(EmployeeBean employeeBean) {
		this.template.update(UPDATE, employeeBean.getPassword(), employeeBean.getName(), employeeBean.getEmail(), employeeBean.getShare(), employeeBean.getAuthority(),
				employeeBean.getSort(), employeeBean.getResigned(), employeeBean.getUpDay(), employeeBean.getUpEmployeeId(), employeeBean.getEmployeeId());
	}

	@Override
	public void delete(EmployeeBean employeeBean) {
		this.template.update(DELETE, employeeBean.getEmployeeId());
	}
}