package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.DiscountBean;
import com.s84.smile.dao.DiscountDao;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountDao discountDao;

	@Override
	public List<DiscountBean> selectAll() {
		return discountDao.selectAll();
	}

	@Override
	public DiscountBean selectByPrimaryKey(int discountId) {
		return discountDao.selectByPrimaryKey(discountId);
	}
}
