package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.CourseClassBean;
import com.s84.smile.dao.CourseClassDao;

@Service
public class CourseClassServiceImpl implements CourseClassService {

	@Autowired
	private CourseClassDao courseClassDao;
	private List<CourseClassBean> courseClassList;
	
	public List<CourseClassBean> selectAll() {
		this.courseClassList = this.courseClassDao.selectAll();
		return courseClassList;
	}
	
	public int getMinCourseClassId() {
		return courseClassList.get(0).getCourseClassId();
	}
}
