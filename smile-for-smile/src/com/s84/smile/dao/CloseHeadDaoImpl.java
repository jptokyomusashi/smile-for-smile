package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CloseHeadBean;

@Repository
public class CloseHeadDaoImpl implements CloseHeadDao {

	private static final String INSERT = "insert into close_head values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "delete from close_head where day = ? and employee_id = ?";
	private static final String SELECT_FOR_DAY_AND_EMPLOYEE = "select * from close_head where day = ? and employee_id = ?";
	private static final String SELECT_BY_SALES_SLIP_ID = "select * from close_head where slip_id = ?";
	private static final String SELECT_BY_PAYMENT_SLIP_ID = "select * from close_head where payment_slip_id = ? limit 1";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(CloseHeadBean closeHeadBean) {
		return this.template.update(INSERT, closeHeadBean.getSlipId(), closeHeadBean.getDay(), closeHeadBean.getEmployeeId(), closeHeadBean.getMemberId(),
				closeHeadBean.getStartTime(), closeHeadBean.getEndTime(), closeHeadBean.getCourseClassId(), closeHeadBean.getCourseId(),
				closeHeadBean.getCourseCharge(), closeHeadBean.getCourseChargeEmployee(), closeHeadBean.getCourseExtensionId(), closeHeadBean.getCourseExtensionCharge(),
				closeHeadBean.getCourseExtensionChargeEmployee(), closeHeadBean.getAppointId(), closeHeadBean.getAppointCharge(), closeHeadBean.getAppointChargeEmployee(),
				closeHeadBean.getTax(), closeHeadBean.getPaymentSlipId(), closeHeadBean.getUpDay(), closeHeadBean.getUpEmployeeId());
	}

	@Override
	public void delete(Date day, String employeeId) {
		this.template.update(DELETE, day, employeeId);
	}

	@Override
	public List<CloseHeadBean> selectByDayAndEmployeeId(Date day, String employeeId) {
		RowMapper<CloseHeadBean> mapper = new BeanPropertyRowMapper<CloseHeadBean>(CloseHeadBean.class);
		return this.template.query(SELECT_FOR_DAY_AND_EMPLOYEE, mapper, day, employeeId);
	}

	@Override
	public CloseHeadBean selectBySalesSlipId(Integer salesSlipId) {
		RowMapper<CloseHeadBean> mapper = new BeanPropertyRowMapper<CloseHeadBean>(CloseHeadBean.class);
		CloseHeadBean closeHeadBean = null;
		try {
			closeHeadBean = this.template.queryForObject(SELECT_BY_SALES_SLIP_ID, mapper, salesSlipId);
		} catch (EmptyResultDataAccessException e) {
			//例外無視
		}
		return closeHeadBean;
	}

	@Override
	public CloseHeadBean selectByPaymentSlipId(Integer paymentSlipId) {
		RowMapper<CloseHeadBean> mapper = new BeanPropertyRowMapper<CloseHeadBean>(CloseHeadBean.class);
		CloseHeadBean closeHeadBean = null;
		try {
			closeHeadBean = this.template.queryForObject(SELECT_BY_PAYMENT_SLIP_ID, mapper, paymentSlipId);
		} catch (EmptyResultDataAccessException e) {
			//例外無視
		}

		return closeHeadBean;
	}
}
