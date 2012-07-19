package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CourseClassBean;

@Repository
public class CourseClassDaoImpl implements CourseClassDao {

	private static final String SELECT_ALL = "select * from course_class order by sort";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<CourseClassBean> selectAll() {
		RowMapper<CourseClassBean> mapper = new BeanPropertyRowMapper<CourseClassBean>(CourseClassBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}

}
