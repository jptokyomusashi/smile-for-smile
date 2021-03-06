package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.OptionmenuBean;

@Repository
public class OptionmenuDaoImpl implements OptionmenuDao {

	private static final String SELECT_ALL = "select * from optionmenu order by sort";
	private static final String SELECT_BY_PRIMARYKEY = "select * from optionmenu where optionmenu_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<OptionmenuBean> selectAll() {
		RowMapper<OptionmenuBean> mapper = new BeanPropertyRowMapper<OptionmenuBean>(OptionmenuBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}

	@Override
	public OptionmenuBean selectByPrimaryKey(int optionmenuId) {
		RowMapper<OptionmenuBean> mapper = new BeanPropertyRowMapper<OptionmenuBean>(OptionmenuBean.class);
		return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, optionmenuId);
	}
}
