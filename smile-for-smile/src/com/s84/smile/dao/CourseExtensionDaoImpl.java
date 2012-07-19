package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CourseExtensionBean;

@Repository
public class CourseExtensionDaoImpl implements CourseExtensionDao {

	private static final String SELECT_BY_COURSECLASSID = "select * from course_extension where course_class_id = ? order by sort";
	private static final String SELECT_BY_PRIMARYKEY = "select * from course_extension where course_class_id = ? and course_extension_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<CourseExtensionBean> selectByCourseClassId(int courseClassId) {
		RowMapper<CourseExtensionBean> mapper = new BeanPropertyRowMapper<CourseExtensionBean>(CourseExtensionBean.class);
		return this.template.query(SELECT_BY_COURSECLASSID, mapper, courseClassId);
	}

	@Override
	public CourseExtensionBean selectByPrimaryKey(int courseClassId, int courseExtensionId) {
		RowMapper<CourseExtensionBean> mapper = new BeanPropertyRowMapper<CourseExtensionBean>(CourseExtensionBean.class);
		return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, courseClassId, courseExtensionId);
	}

}
