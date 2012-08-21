package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.MailCustomerBean;

@Repository
public class MailCustomerDaoImpl implements MailCustomerDao {

	private static final String INSERT = "insert into mail_customer(mailaddress, up_day, up_employee_id) values(?, ?, ?)";
	private static final String UPDATE = "update mail_customer set name = ?, deleted = ?, up_day = ?, up_employee_id = ? where id = ?";
	private static final String SELECT_BY_MAILADDRESS = "select * from mail_customer where mailaddress = ? and deleted is null";
	private static final String SELECT_ALL = "select * from mail_customer order by id";
	private static final String SELECT_BY_PRIMARYKEY = "select * from mail_customer where id = ?";

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(MailCustomerBean mailCustomerBean) {
		this.template.update(INSERT, mailCustomerBean.getMailAddress(), mailCustomerBean.getUpDay(), mailCustomerBean.getUpEmployeeId());
	}

	@Override
	public void update(MailCustomerBean mailCustomerBean) {
		this.template.update(UPDATE, mailCustomerBean.getName(), mailCustomerBean.getDeleted(),
				mailCustomerBean.getUpDay(), mailCustomerBean.getUpEmployeeId(), mailCustomerBean.getId());
	}

	@Override
	public List<MailCustomerBean> selectByMailAddress(String mailAddress) {
		RowMapper<MailCustomerBean> mapper = new BeanPropertyRowMapper<MailCustomerBean>(MailCustomerBean.class);
		return this.template.query(SELECT_BY_MAILADDRESS, mapper, mailAddress);
	}

	@Override
	public List<MailCustomerBean> selectAll() {
		RowMapper<MailCustomerBean> mapper = new BeanPropertyRowMapper<MailCustomerBean>(MailCustomerBean.class);
		return this.template.query(SELECT_ALL, mapper);
	}

	@Override
	public MailCustomerBean selectByPrimarykey(int id) {
		RowMapper<MailCustomerBean> mapper = new BeanPropertyRowMapper<MailCustomerBean>(MailCustomerBean.class);
		return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, id);	
	}
}