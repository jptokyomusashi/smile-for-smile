package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.CodeBean;
import com.s84.smile.dao.CodeDao;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDao codeDao;

	public List<CodeBean> selectByCategory(String category) {
		return this.codeDao.selectByCategory(category);
	}
}
