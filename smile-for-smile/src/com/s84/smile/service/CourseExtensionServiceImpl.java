package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.CourseExtensionBean;
import com.s84.smile.dao.CourseExtensionDao;

@Service
public class CourseExtensionServiceImpl implements CourseExtensionService {

	@Autowired
	private CourseExtensionDao courseExtensionDao;

	@Override
	public List<CourseExtensionBean> selectByCourseClassId(int courseClassId) {
		return courseExtensionDao.selectByCourseClassId(courseClassId);
	}

	@Override
	public CourseExtensionBean selectByPrimaryKey(int courseClassId, int courseExtensionId) {
		return courseExtensionDao.selectByPrimaryKey(courseClassId, courseExtensionId);
	}

}
