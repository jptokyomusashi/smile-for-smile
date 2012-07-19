package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.AccountBean;
import com.s84.smile.dao.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Override
	public List<AccountBean> selectAll() {
		return accountDao.selectAll();
	}
}
