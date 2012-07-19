package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.PaymentSlipDetailBean;

@Repository
public class PaymentSlipDetailDaoImpl implements PaymentSlipDetailDao {

	private static final String INSERT = "insert into payment_slip_detail values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_BY_SLIPID = "delete from payment_slip_detail where slip_id = ?";
	private static final String DELETE_BY_SLIPID_AND_DETAILID = "delete from payment_slip_detail where slip_id = ? and detail_id = ?";
	private static final String SELECT_BY_SLIP_ID = "select * from payment_slip_detail where slip_id = ? order by detail_id";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int delete(int slipId) {
		return this.template.update(DELETE_BY_SLIPID, slipId);
	}

	@Override
	public int delete(int slipId, int detailId) {
		return this.template.update(DELETE_BY_SLIPID_AND_DETAILID, slipId, detailId);
	}

	@Override
	public int insert(PaymentSlipDetailBean paymentSlipDetailBean) {
		return this.template.update(INSERT, paymentSlipDetailBean.getSlipId(), paymentSlipDetailBean.getDetailId(), paymentSlipDetailBean.getAccount(),
				paymentSlipDetailBean.getName(), paymentSlipDetailBean.getUnitPrice(), paymentSlipDetailBean.getAmount(),
				paymentSlipDetailBean.getComment(), paymentSlipDetailBean.getUpDay(), paymentSlipDetailBean.getUpEmployeeId());
	}

	@Override
	public List<PaymentSlipDetailBean> selectBySlipId(int slipId) {
		RowMapper<PaymentSlipDetailBean> mapper = new BeanPropertyRowMapper<PaymentSlipDetailBean>(PaymentSlipDetailBean.class);
		return this.template.query(SELECT_BY_SLIP_ID, mapper, slipId);
	}

}
