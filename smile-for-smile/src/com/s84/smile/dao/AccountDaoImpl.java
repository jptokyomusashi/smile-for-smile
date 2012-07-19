package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.AccountBean;

@Repository
public class AccountDaoImpl implements AccountDao {

	private static final String SELECT_ALL = "select * from account order by sort";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<AccountBean> selectAll() {
		RowMapper<AccountBean> mapper = new BeanPropertyRowMapper<AccountBean>(AccountBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}
}
