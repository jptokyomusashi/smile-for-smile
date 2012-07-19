package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.s84.smile.bean.SalesSlipHeadBean;
import com.s84.smile.bean.SalesSlipSearchConditionBean;
import com.s84.smile.bean.SalesSlipSearchResultBean;
import com.s84.smile.util.DateUtil;

@Repository
public class SalesSlipHeadDaoImpl implements SalesSlipHeadDao {

	private static final String INSERT = "insert into sales_slip_head(day, employee_id, member_id, start_time, end_time, course_class_id," +
										"course_id, course_charge, course_extension_id, course_extension_charge, appoint_id, appoint_charge," +
										"up_day, up_employee_id)" + 
										"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update sales_slip_head set day = ?, employee_id = ?, member_id = ?," +
								"start_time = ?, end_time = ?, course_class_id = ?, course_id = ?, course_charge = ?," +
								"course_extension_id = ?, course_extension_charge = ?, appoint_id = ?, appoint_charge = ?," +
								"up_day = ?, up_employee_id = ? where slip_id = ?";
	private static final String SELECT_BY_SLIP_ID = "select * from sales_slip_head where slip_id = ?";
	private static final String SELECT_LAST_INSERT_ID = "select last_insert_id()";
	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(SalesSlipHeadBean salesSlipHeadBean) {
		this.template.update(INSERT, salesSlipHeadBean.getDay(), salesSlipHeadBean.getEmployeeId(), salesSlipHeadBean.getMemberId(),
				salesSlipHeadBean.getStartTime(), salesSlipHeadBean.getEndTime(), salesSlipHeadBean.getCourseClassId(), salesSlipHeadBean.getCourseId(),
				salesSlipHeadBean.getCourseCharge(), salesSlipHeadBean.getCourseExtensionId(), salesSlipHeadBean.getCourseExtensionCharge(),
				salesSlipHeadBean.getAppointId(), salesSlipHeadBean.getAppointCharge(),	salesSlipHeadBean.getUpDay(), salesSlipHeadBean.getUpEmployeeId());
		return this.template.queryForInt(SELECT_LAST_INSERT_ID);
	}

	@Override
	public int update(SalesSlipHeadBean salesSlipHeadBean) {
		return this.template.update(UPDATE, salesSlipHeadBean.getDay(), salesSlipHeadBean.getEmployeeId(), salesSlipHeadBean.getMemberId(),
				salesSlipHeadBean.getStartTime(), salesSlipHeadBean.getEndTime(), salesSlipHeadBean.getCourseClassId(), salesSlipHeadBean.getCourseId(),
				salesSlipHeadBean.getCourseCharge(), salesSlipHeadBean.getCourseExtensionId(), salesSlipHeadBean.getCourseExtensionCharge(),
				salesSlipHeadBean.getAppointId(), salesSlipHeadBean.getAppointCharge(), salesSlipHeadBean.getUpDay(),
				salesSlipHeadBean.getUpEmployeeId(), salesSlipHeadBean.getSlipId());
	}

	@Override
	public SalesSlipHeadBean selectBySlipId(int slipId) {
		RowMapper<SalesSlipHeadBean> mapper = new BeanPropertyRowMapper<SalesSlipHeadBean>(SalesSlipHeadBean.class);
		return this.template.queryForObject(SELECT_BY_SLIP_ID, mapper, slipId);
	}

	@Override
	public List<SalesSlipSearchResultBean> select(SalesSlipSearchConditionBean salesSlipSearchConditionBean) {
		String query = "select h.slip_id, h.day, h.employee_id, e.name, h.member_id, h.start_time, h.end_time " +
					"     from sales_slip_head h" +
					"    inner join employee e on h.employee_id = e.employee_id" +
					"    where 1 = 1";
		//日付FROM
		if (salesSlipSearchConditionBean.getDayFrom() != null) {
			query += " and h.day >= '" + DateUtil.getDateFormat().format(salesSlipSearchConditionBean.getDayFrom()) + "'";
		}
		//日付TO
		if (salesSlipSearchConditionBean.getDayTo() != null) {
			query += " and h.day <= '" + DateUtil.getDateFormat().format(salesSlipSearchConditionBean.getDayTo()) + "'";
		}
		//従業員
		if (StringUtils.hasLength(salesSlipSearchConditionBean.getEmployeeId())) {
			query += " and h.employee_id = '" + salesSlipSearchConditionBean.getEmployeeId() + "'";
		}
		query += " order by h.slip_id";

		RowMapper<SalesSlipSearchResultBean> mapper = new BeanPropertyRowMapper<SalesSlipSearchResultBean>(SalesSlipSearchResultBean.class);
		return this.template.query(query, mapper);
	}
}
