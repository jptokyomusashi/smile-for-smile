package com.s84.smile.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.SalesSummarySearchResultBean;

@Repository
public class CloseDaoImpl implements CloseDao {

	private static final String SELECT_SALES_SUMMARY = "select h.day, e.name employee_name, cc.name course_class_name, c.name course_name, h.start_time, h.end_time," +
																"        course_charge_employee + ifnull(course_extension_charge_employee, 0) + " +
																"        ifnull(appoint_charge_employee, 0) + ifnull(o.charge_employee, 0) charge_employee," +
																"        course_charge + ifnull(course_extension_charge, 0) + ifnull(appoint_charge, 0) + ifnull(o.charge, 0) charge_shop," +
																"        ifnull(d.charge_employee, 0) discount_employee," +
																"        ifnull(d.charge, 0) discount_shop," +
																"        ifnull(tax, 0) tax, appoint_id, member_id" +
																"   from close_head h" +
																"   left outer join (select slip_id, sum(charge) charge, sum(charge_employee) charge_employee" +
																"                      from close_optionmenu" +
																"                     group by slip_id) o" +
																"     on h.slip_id = o.slip_id" +
																"   left outer join (select slip_id, sum(charge) charge, sum(charge_employee) charge_employee" +
																"                      from close_discount" +
																"                     group by slip_id) d" +
																"     on h.slip_id = d.slip_id" +
																"   left outer join course_class cc" +
																"     on h.course_class_id = cc.course_class_id" +
																"   left outer join course c" +
																"     on h.course_class_id = c.course_class_id" +
																"    and h.course_id = c.course_id" +
																"  inner join employee e" +
																"     on h.employee_id = e.employee_id" +
																"  where h.day >= ? and h.day <= ?" +
																"  order by h.day, e.sort, h.start_time";

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public List<SalesSummarySearchResultBean> selectSalesSummary(Date dayFrom, Date dayTo) {
		RowMapper<SalesSummarySearchResultBean> mapper = new BeanPropertyRowMapper<SalesSummarySearchResultBean>(SalesSummarySearchResultBean.class);
		return this.template.query(SELECT_SALES_SUMMARY, mapper, dayFrom, dayTo);
	}
}
