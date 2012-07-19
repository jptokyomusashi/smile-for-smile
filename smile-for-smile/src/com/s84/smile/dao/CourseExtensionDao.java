package com.s84.smile.dao;

import java.util.List;

import com.s84.smile.bean.CourseExtensionBean;

public interface CourseExtensionDao {

	public List<CourseExtensionBean> selectByCourseClassId(int courseClassId);
	public CourseExtensionBean selectByPrimaryKey(int courseClassId, int courseExtensionId);
}
