package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.CourseBean;

public interface CourseService {
	
	public List<CourseBean> selectByCourseClassId(int courseClassId);
	public CourseBean selectByPrimaryKey(int courseClassId, int courseId);
}
