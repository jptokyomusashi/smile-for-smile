package com.s84.smile.service;

import java.util.List;

import com.s84.smile.bean.CodeBean;

public interface CodeService {

	public List<CodeBean> selectByCategory(String category);
}
