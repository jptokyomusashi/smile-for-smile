package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CloseDiscountBean;

@Repository
public class CloseDiscountDaoImpl implements CloseDiscountDao {

	private static final String INSERT = "insert into close_discount values(?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_BY_CLOSEID = "delete from close_discount where slip_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(CloseDiscountBean closeDiscountBean) {
		return this.template.update(INSERT, closeDiscountBean.getSlipId(), closeDiscountBean.getDetailId(), closeDiscountBean.getDiscountId(),
				closeDiscountBean.getCharge(), closeDiscountBean.getChargeEmployee(), closeDiscountBean.getUpDay(), closeDiscountBean.getUpEmployeeId());
	}

	@Override
	public int delete(List<Integer> closeIdList) {
		int deletedRows = 0;
		for (Integer closeId : closeIdList) {
			deletedRows += this.template.update(DELETE_BY_CLOSEID, closeId);
		}
		return deletedRows;
	}
}
