package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CourseBean;

@Repository
public class CourseDaoImpl implements CourseDao {

	private static final String SELECT_BY_COURSECLASSID = "select * from course where course_class_id = ? order by sort";
	private static final String SELECT_BY_PRIMARYKEY = "select * from course where course_class_id = ? and course_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<CourseBean> selectByCourseClassId(int courseClassId) {
		RowMapper<CourseBean> mapper = new BeanPropertyRowMapper<CourseBean>(CourseBean.class);
		return this.template.query(SELECT_BY_COURSECLASSID, mapper, courseClassId);
	}

	@Override
	public CourseBean selectByPrimaryKey(int courseClassId, int courseId) {
		RowMapper<CourseBean> mapper = new BeanPropertyRowMapper<CourseBean>(CourseBean.class);
		return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, courseClassId, courseId);
	}
}
