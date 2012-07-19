package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.CourseBean;
import com.s84.smile.dao.CourseDao;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	public List<CourseBean> selectByCourseClassId(int courseClassId) {
		return this.courseDao.selectByCourseClassId(courseClassId);
	}
	
	public CourseBean selectByPrimaryKey(int courseClassId, int courseId) {
		return this.courseDao.selectByPrimaryKey(courseClassId, courseId);
	}
}
