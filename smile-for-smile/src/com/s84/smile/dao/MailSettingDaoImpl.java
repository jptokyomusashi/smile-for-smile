package com.s84.smile.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.MailSettingBean;

@Repository
public class MailSettingDaoImpl implements MailSettingDao {

	private static final String INSERT = "insert into mail_setting values(?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "delete from mail_setting";
	private static final String SELECT = "select * from mail_setting";

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(MailSettingBean mailSettingBean) {
		this.template.update(INSERT, mailSettingBean.getSmtp(), mailSettingBean.getPort(), mailSettingBean.getSendAddress(), mailSettingBean.getSendName(),
				mailSettingBean.getUserId(), mailSettingBean.getPassword());
	}

	@Override
	public void delete() {
		this.template.update(DELETE);
	}

	@Override
	public MailSettingBean select() {
		RowMapper<MailSettingBean> mapper = new BeanPropertyRowMapper<MailSettingBean>(MailSettingBean.class);
		return this.template.queryForObject(SELECT, mapper);	
	}
}