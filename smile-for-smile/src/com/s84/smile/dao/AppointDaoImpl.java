package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.AppointBean;

@Repository
public class AppointDaoImpl implements AppointDao {

	private static final String SELECT_ALL = "select * from appoint order by sort";
	private static final String SELECT_BY_PRIMARYKEY = "select * from appoint where appoint_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<AppointBean> selectAll() {
		RowMapper<AppointBean> mapper = new BeanPropertyRowMapper<AppointBean>(AppointBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}
	
	@Override
	public AppointBean selectByPrimaryKey(int appointId) {
		RowMapper<AppointBean> mapper = new BeanPropertyRowMapper<AppointBean>(AppointBean.class);
		return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, appointId);
	}
}
