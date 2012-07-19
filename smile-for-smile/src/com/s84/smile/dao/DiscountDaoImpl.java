package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.DiscountBean;

@Repository
public class DiscountDaoImpl implements DiscountDao {

	private static final String SELECT_ALL = "select * from discount order by sort";
	private static final String SELECT_BY_PRIMARYKEY = "select * from discount where discount_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<DiscountBean> selectAll() {
		RowMapper<DiscountBean> mapper = new BeanPropertyRowMapper<DiscountBean>(DiscountBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}
	
	@Override
	public DiscountBean selectByPrimaryKey(int discountId) {
		RowMapper<DiscountBean> mapper = new BeanPropertyRowMapper<DiscountBean>(DiscountBean.class);
		return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, discountId);
	}
}
