package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.SalesSlipDiscountBean;

@Repository
public class SalesSlipDiscountDaoImpl implements SalesSlipDiscountDao {

	private static final String INSERT = "insert into sales_slip_discount values(?, ?, ?, ?, ?, ?)";
	private static final String DELETE_BY_SLIPID = "delete from sales_slip_discount where slip_id = ?";
	private static final String DELETE_BY_SLIPID_AND_DETAILID = "delete from sales_slip_discount where slip_id = ? and detail_id = ?";
	private static final String SELECT_BY_SLIP_ID = "select * from sales_slip_discount where slip_id = ? order by detail_id";
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
	public int insert(SalesSlipDiscountBean salesSlipDiscountBean) {
		return this.template.update(INSERT, salesSlipDiscountBean.getSlipId(), salesSlipDiscountBean.getDetailId(), salesSlipDiscountBean.getDiscountId(), 
				salesSlipDiscountBean.getCharge(), salesSlipDiscountBean.getUpDay(), salesSlipDiscountBean.getUpEmployeeId());
	}

	@Override
	public List<SalesSlipDiscountBean> selectBySlipId(int slipId) {
		RowMapper<SalesSlipDiscountBean> mapper = new BeanPropertyRowMapper<SalesSlipDiscountBean>(SalesSlipDiscountBean.class);
		return this.template.query(SELECT_BY_SLIP_ID, mapper, slipId);
	}
}
