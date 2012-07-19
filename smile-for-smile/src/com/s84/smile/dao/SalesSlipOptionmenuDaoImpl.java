package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.SalesSlipOptionmenuBean;

@Repository
public class SalesSlipOptionmenuDaoImpl implements SalesSlipOptionmenuDao {

	private static final String INSERT = "insert into sales_slip_optionmenu values(?, ?, ?, ?, ?, ?)";
	private static final String DELETE_BY_SLIPID = "delete from sales_slip_optionmenu where slip_id = ?";
	private static final String DELETE_BY_SLIPID_AND_DETAILID = "delete from sales_slip_optionmenu where slip_id = ? and detail_id = ?";
	private static final String SELECT_BY_SLIP_ID = "select * from sales_slip_optionmenu where slip_id = ? order by detail_id";
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
	public int insert(SalesSlipOptionmenuBean salesSlipOptionmenuBean) {
		return this.template.update(INSERT, salesSlipOptionmenuBean.getSlipId(), salesSlipOptionmenuBean.getDetailId(), salesSlipOptionmenuBean.getOptionmenuId(), 
				salesSlipOptionmenuBean.getCharge(), salesSlipOptionmenuBean.getUpDay(), salesSlipOptionmenuBean.getUpEmployeeId());
	}

	@Override
	public List<SalesSlipOptionmenuBean> selectBySlipId(int slipId) {
		RowMapper<SalesSlipOptionmenuBean> mapper = new BeanPropertyRowMapper<SalesSlipOptionmenuBean>(SalesSlipOptionmenuBean.class);
		return this.template.query(SELECT_BY_SLIP_ID, mapper, slipId);
	}

}
