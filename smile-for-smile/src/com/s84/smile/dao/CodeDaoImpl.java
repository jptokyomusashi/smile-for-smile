package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CodeBean;

@Repository
public class CodeDaoImpl implements CodeDao {

	private static final String SELECT_BY_CATEGORY = "select * from code where category = ? order by code";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<CodeBean> selectByCategory(String category) {
		RowMapper<CodeBean> mapper = new BeanPropertyRowMapper<CodeBean>(CodeBean.class);
		return this.template.query(SELECT_BY_CATEGORY, mapper, category);
	}

}
