package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.CourseBean;

public interface CourseDao {

	public List<CourseBean> selectByCourseClassId(int courseClassId);
	public CourseBean selectByPrimaryKey(int courseClassId, int courseId);
}
