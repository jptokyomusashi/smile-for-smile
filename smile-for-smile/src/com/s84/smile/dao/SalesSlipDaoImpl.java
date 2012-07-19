package com.s84.smile.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSlipDiscountBean;
import com.s84.smile.bean.SalesSlipHeadBean;
import com.s84.smile.bean.SalesSlipOptionmenuBean;

@Repository
public class SalesSlipDaoImpl implements SalesSlipDao {

	private static final String SELECT_SLIP_ID = "select * from sales_slip_head where day = ? and employee_id = ?";
	private JdbcTemplate template;

	@Autowired
	private SalesSlipHeadDao salesSlipHeadDao;
	@Autowired
	private SalesSlipOptionmenuDao salesSlipOptionmenuDao;
	@Autowired
	private SalesSlipDiscountDao salesSlipDiscountDao;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SalesSlipBean> selectByDayAndEmployeeId(Date day, String employeeId) {
		RowMapper<SalesSlipHeadBean> mapper = new BeanPropertyRowMapper<SalesSlipHeadBean>(SalesSlipHeadBean.class);

		List<SalesSlipBean> salesSlipList = new ArrayList<SalesSlipBean>();
		List<SalesSlipHeadBean> headList = this.template.query(SELECT_SLIP_ID, mapper, day, employeeId);
		for (SalesSlipHeadBean headBean : headList) {
			SalesSlipHeadBean salesSlipHeadBean = salesSlipHeadDao.selectBySlipId(headBean.getSlipId());
			List<SalesSlipOptionmenuBean> salesSlipOptionmenuList = salesSlipOptionmenuDao.selectBySlipId(headBean.getSlipId());
			List<SalesSlipDiscountBean> salesSlipDiscountList = salesSlipDiscountDao.selectBySlipId(headBean.getSlipId());

			//SalesSlipBean生成
			SalesSlipBean salesSlipBean = new SalesSlipBean();
			salesSlipBean.setSalesSlipHeadBean(salesSlipHeadBean);
			salesSlipBean.setSalesSlipOptionmenuList(salesSlipOptionmenuList);
			salesSlipBean.setSalesSlipDiscountList(salesSlipDiscountList);
			
			salesSlipList.add(salesSlipBean);
		}
		
		return salesSlipList;
	}
}
