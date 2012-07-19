package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.CourseClassBean;

public interface CourseClassService {

	public List<CourseClassBean> selectAll();
	public int getMinCourseClassId();
}
