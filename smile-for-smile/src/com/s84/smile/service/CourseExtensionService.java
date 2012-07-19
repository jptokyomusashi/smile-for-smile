package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.CourseExtensionBean;

public interface CourseExtensionService {

	public List<CourseExtensionBean> selectByCourseClassId(int courseClassId);
	public CourseExtensionBean selectByPrimaryKey(int courseClassId, int courseExtensionId);
}
