package com.s84.smile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.s84.smile.bean.PaymentSlipHeadBean;
import com.s84.smile.bean.PaymentSlipSearchConditionBean;
import com.s84.smile.bean.PaymentSlipSearchResultBean;
import com.s84.smile.util.DateUtil;

@Repository
public class PaymentSlipHeadDaoImpl implements PaymentSlipHeadDao {

	private static final String INSERT = "insert into payment_slip_head(day, payee, up_day, up_employee_id)" + 
										"values(?, ?, ?, ?)";
	private static final String UPDATE = "update payment_slip_head set day = ?, payee = ?, up_day = ?, up_employee_id = ? where slip_id = ?";
	private static final String SELECT_BY_SLIP_ID = "select * from payment_slip_head where slip_id = ?";
	private static final String SELECT_LAST_INSERT_ID = "select last_insert_id()";
	private static final String DELETE = "delete from payment_slip_head where slip_id = ?";
	private static final String SELECT_SUMMARY_BY_PAYEE = 
			"select payee, sum(unit_price * amount)" +
			"  from payment_slip_head h" +
			"  left outer join payment_slip_detail d" +
			"    on h.slip_id = d.slip_id" +
			" group by payee";
	private static final String SELECT_SUMMARY_BY_ACCOUNT = 
			"select a.name, sum(unit_price * amount)" +
			"  from payment_slip_head h" +
			"  left outer join payment_slip_detail d" +
			"    on h.slip_id = d.slip_id" +
			"  inner join account a" +
			"    on d.account = a.id" +
			" group by a.name";

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public int insert(PaymentSlipHeadBean paymentSlipHeadBean) {
		this.template.update(INSERT, paymentSlipHeadBean.getDay(), paymentSlipHeadBean.getPayee(),
				paymentSlipHeadBean.getUpDay(), paymentSlipHeadBean.getUpEmployeeId());
		return this.template.queryForInt(SELECT_LAST_INSERT_ID);
	}

	@Override
	public int update(PaymentSlipHeadBean paymentSlipHeadBean) {
		return this.template.update(UPDATE, paymentSlipHeadBean.getDay(), paymentSlipHeadBean.getPayee(), paymentSlipHeadBean.getUpDay(),
				paymentSlipHeadBean.getUpEmployeeId(), paymentSlipHeadBean.getSlipId());
	}

	@Override
	public PaymentSlipHeadBean selectBySlipId(int slipId) {
		RowMapper<PaymentSlipHeadBean> mapper = new BeanPropertyRowMapper<PaymentSlipHeadBean>(PaymentSlipHeadBean.class);
		return this.template.queryForObject(SELECT_BY_SLIP_ID, mapper, slipId);
	}

	@Override
	public List<PaymentSlipSearchResultBean> select(PaymentSlipSearchConditionBean paymentSlipSearchConditionBean) {
		String query = "select h.slip_id, h.day, h.payee, " +
						"         (select sum(unit_price*amount) from payment_slip_detail d where h.slip_id = d.slip_id) total_charge" +
						"    from payment_slip_head h" +
						"   where 1 = 1";
	//日付FROM
	if (paymentSlipSearchConditionBean.getDayFrom() != null) {
		query += " and h.day >= '" + DateUtil.getDateFormat().format(paymentSlipSearchConditionBean.getDayFrom()) + "'";
	}
	//日付TO
	if (paymentSlipSearchConditionBean.getDayTo() != null) {
		query += " and h.day <= '" + DateUtil.getDateFormat().format(paymentSlipSearchConditionBean.getDayTo()) + "'";
	}
	//支払先
	if (paymentSlipSearchConditionBean.getPayee() != null) {
		query += " and payee like '" + paymentSlipSearchConditionBean.getPayee() + "%'";
	}
	query += " order by h.slip_id";

	RowMapper<PaymentSlipSearchResultBean> mapper = new BeanPropertyRowMapper<PaymentSlipSearchResultBean>(PaymentSlipSearchResultBean.class);
	return this.template.query(query, mapper);

	}

	@Override
	public int delete(int slipId) {
		return this.template.update(DELETE, slipId);
	}
}
