package com.s84.smile.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSlipDiscountBean;
import com.s84.smile.bean.SalesSlipHeadBean;
import com.s84.smile.bean.SalesSlipOptionmenuBean;
import com.s84.smile.bean.SalesSlipSearchConditionBean;
import com.s84.smile.bean.SalesSlipSearchResultBean;
import com.s84.smile.dao.SalesSlipDao;
import com.s84.smile.dao.SalesSlipDiscountDao;
import com.s84.smile.dao.SalesSlipHeadDao;
import com.s84.smile.dao.SalesSlipOptionmenuDao;
import com.s84.smile.util.DateUtil;

@Service
public class SalesSlipServiceImpl implements SalesSlipService {

	@Autowired
	private SalesSlipHeadDao salesSlipHeadDao;
	@Autowired
	private SalesSlipDiscountDao salesSlipDiscountDao;
	@Autowired
	private SalesSlipOptionmenuDao salesSlipOptionmenuDao;
	@Autowired
	private SalesSlipDao salesSlipDao;

	@Override
	public void insert(SalesSlipBean salesSlipBean, EmployeeBean employeeBean) {
		SalesSlipHeadBean salesSlipHeadBean = salesSlipBean.getSalesSlipHeadBean();
		List<SalesSlipOptionmenuBean> salesSlipOptionmenuList = salesSlipBean.getSalesSlipOptionmenuList();
		List<SalesSlipDiscountBean> salesSlipDiscountList = salesSlipBean.getSalesSlipDiscountList();
		
		salesSlipHeadBean.setUpDay(DateUtil.getDay(0));
		salesSlipHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
		int slipId = salesSlipHeadDao.insert(salesSlipHeadBean);

		int detailId = 1;
		for (SalesSlipOptionmenuBean salesSlipOptionmenuBean : salesSlipOptionmenuList) {
			if (salesSlipOptionmenuBean.getOptionmenuId() != null) {
				salesSlipOptionmenuBean.setSlipId(slipId);
				salesSlipOptionmenuBean.setDetailId(detailId++);
				salesSlipOptionmenuBean.setUpDay(DateUtil.getDay(0));
				salesSlipOptionmenuBean.setUpEmployeeId(employeeBean.getEmployeeId());
				salesSlipOptionmenuDao.insert(salesSlipOptionmenuBean);
			}
		}

		detailId = 1;
		for (SalesSlipDiscountBean salesSlipDiscountBean : salesSlipDiscountList) {
			if (salesSlipDiscountBean.getDiscountId() != null) {
				salesSlipDiscountBean.setSlipId(slipId);
				salesSlipDiscountBean.setDetailId(detailId++);
				salesSlipDiscountBean.setUpDay(DateUtil.getDay(0));
				salesSlipDiscountBean.setUpEmployeeId(employeeBean.getEmployeeId());
				salesSlipDiscountDao.insert(salesSlipDiscountBean);
			}
		}
	}

	@Override
	public void update(SalesSlipBean salesSlipBean, EmployeeBean employeeBean) {
		SalesSlipHeadBean salesSlipHeadBean = salesSlipBean.getSalesSlipHeadBean();
		List<SalesSlipOptionmenuBean> salesSlipOptionmenuList = salesSlipBean.getSalesSlipOptionmenuList();
		List<SalesSlipDiscountBean> salesSlipDiscountList = salesSlipBean.getSalesSlipDiscountList();
		
		salesSlipHeadBean.setUpDay(DateUtil.getDay(0));
		salesSlipHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
		salesSlipHeadDao.update(salesSlipHeadBean);
		int slipId = salesSlipHeadBean.getSlipId();
		salesSlipOptionmenuDao.delete(slipId);
		salesSlipDiscountDao.delete(slipId);

		int detailId = 1;
		for (SalesSlipOptionmenuBean salesSlipOptionmenuBean : salesSlipOptionmenuList) {
			if (salesSlipOptionmenuBean.getOptionmenuId() != null) {
				salesSlipOptionmenuBean.setSlipId(slipId);
				salesSlipOptionmenuBean.setDetailId(detailId++);
				salesSlipOptionmenuBean.setUpDay(DateUtil.getDay(0));
				salesSlipOptionmenuBean.setUpEmployeeId(employeeBean.getEmployeeId());
				salesSlipOptionmenuDao.insert(salesSlipOptionmenuBean);
			}
		}

		detailId = 1;
		for (SalesSlipDiscountBean salesSlipDiscountBean : salesSlipDiscountList) {
			if (salesSlipDiscountBean.getDiscountId() != null) {
				salesSlipDiscountBean.setSlipId(slipId);
				salesSlipDiscountBean.setDetailId(detailId++);
				salesSlipDiscountBean.setUpDay(DateUtil.getDay(0));
				salesSlipDiscountBean.setUpEmployeeId(employeeBean.getEmployeeId());
				salesSlipDiscountDao.insert(salesSlipDiscountBean);
			}
		}
	}

	@Override
	public List<SalesSlipSearchResultBean> selectSalesSlipHead(SalesSlipSearchConditionBean salesSlipSearchConditionBean) {
		return salesSlipHeadDao.select(salesSlipSearchConditionBean);
	}

	@Override
	public SalesSlipBean selectBySlipId(int slipId) {
		SalesSlipBean salesSlipBean = new SalesSlipBean();
		salesSlipBean.setSalesSlipHeadBean(salesSlipHeadDao.selectBySlipId(slipId));
		salesSlipBean.setSalesSlipOptionmenuList(salesSlipOptionmenuDao.selectBySlipId(slipId));
		salesSlipBean.setSalesSlipDiscountList(salesSlipDiscountDao.selectBySlipId(slipId));
		return salesSlipBean;
	}

	@Override
	public List<SalesSlipBean> selectByDayAndEmployeeId(Date day, String employeeId) {
		return salesSlipDao.selectByDayAndEmployeeId(day, employeeId);
	}
}
