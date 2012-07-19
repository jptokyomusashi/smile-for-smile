package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.CloseOptionmenuBean;

@Repository
public class CloseOptionmenuDaoImpl implements CloseOptionmenuDao {

	private static final String INSERT = "insert into close_optionmenu values(?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_BY_CLOSEID = "delete from close_optionmenu where slip_id = ?";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(CloseOptionmenuBean closeOptionmenuBean) {
		return this.template.update(INSERT, closeOptionmenuBean.getSlipId(), closeOptionmenuBean.getDetailId(), closeOptionmenuBean.getOptionmenuId(),
				closeOptionmenuBean.getCharge(), closeOptionmenuBean.getChargeEmployee(), closeOptionmenuBean.getUpDay(), closeOptionmenuBean.getUpEmployeeId());
	}

	@Override
	public int delete(List<Integer> closeIdList) {
		int deletedRows = 0;
		for (Integer closeId : closeIdList) {
			deletedRows += this.template.update(DELETE_BY_CLOSEID, closeId);
		}
		return deletedRows;
	}
}
