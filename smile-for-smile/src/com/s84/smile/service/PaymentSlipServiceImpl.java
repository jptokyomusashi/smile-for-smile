package com.s84.smile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.PaymentSlipBean;
import com.s84.smile.bean.PaymentSlipDetailBean;
import com.s84.smile.bean.PaymentSlipHeadBean;
import com.s84.smile.bean.PaymentSlipSearchConditionBean;
import com.s84.smile.bean.PaymentSlipSearchResultBean;
import com.s84.smile.dao.PaymentSlipDetailDao;
import com.s84.smile.dao.PaymentSlipHeadDao;
import com.s84.smile.util.DateUtil;

@Service
public class PaymentSlipServiceImpl implements PaymentSlipService {

	@Autowired
	private PaymentSlipHeadDao paymentSlipHeadDao;
	@Autowired
	private PaymentSlipDetailDao paymentSlipDetailDao;

	@Override
	public int insert(PaymentSlipBean paymentSlipBean, EmployeeBean employeeBean) {
		PaymentSlipHeadBean paymentSlipHeadBean = paymentSlipBean.getPaymentSlipHeadBean();
		List<PaymentSlipDetailBean> paymentSlipDetailList = paymentSlipBean.getPaymentSlipDetailList();

		paymentSlipHeadBean.setUpDay(DateUtil.getDay(0));
		paymentSlipHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
		int slipId = paymentSlipHeadDao.insert(paymentSlipHeadBean);
		int detailId = 1;

		for (PaymentSlipDetailBean paymentSlipDetailBean : paymentSlipDetailList) {
			if (paymentSlipDetailBean.getUnitPrice() != null && paymentSlipDetailBean.getAmount() != null) {
				paymentSlipDetailBean.setSlipId(slipId);
				paymentSlipDetailBean.setDetailId(detailId++);
				paymentSlipDetailBean.setUpDay(DateUtil.getDay(0));
				paymentSlipDetailBean.setUpEmployeeId(employeeBean.getEmployeeId());
				paymentSlipDetailDao.insert(paymentSlipDetailBean);
			}
		}

		return slipId;
	}

	@Override
	public void update(PaymentSlipBean paymentSlipBean, EmployeeBean employeeBean) {
		PaymentSlipHeadBean paymentSlipHeadBean = paymentSlipBean.getPaymentSlipHeadBean();
		List<PaymentSlipDetailBean> paymentSlipDetailList = paymentSlipBean.getPaymentSlipDetailList();
		
		paymentSlipHeadBean.setUpDay(DateUtil.getDay(0));
		paymentSlipHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
		paymentSlipHeadDao.update(paymentSlipHeadBean);
		int slipId = paymentSlipHeadBean.getSlipId();
		paymentSlipDetailDao.delete(slipId);
		int detailId = 1;

		for (PaymentSlipDetailBean paymentSlipDetailBean : paymentSlipDetailList) {
			if (paymentSlipDetailBean.getUnitPrice() != null && paymentSlipDetailBean.getAmount() != null) {
				paymentSlipDetailBean.setSlipId(slipId);
				paymentSlipDetailBean.setDetailId(detailId++);
				paymentSlipDetailBean.setUpDay(DateUtil.getDay(0));
				paymentSlipDetailBean.setUpEmployeeId(employeeBean.getEmployeeId());
				paymentSlipDetailDao.insert(paymentSlipDetailBean);
			}
		}
	}

	@Override
	public List<PaymentSlipSearchResultBean> selectPaymentSlipHead(PaymentSlipSearchConditionBean paymentSlipSearchConditionBean) {
		return paymentSlipHeadDao.select(paymentSlipSearchConditionBean);
	}

	@Override
	public PaymentSlipBean selectBySlipId(int slipId) {
		PaymentSlipBean paymentSlipBean = new PaymentSlipBean();
		paymentSlipBean.setPaymentSlipHeadBean(paymentSlipHeadDao.selectBySlipId(slipId));
		paymentSlipBean.setPaymentSlipDetailList(paymentSlipDetailDao.selectBySlipId(slipId));
		return paymentSlipBean;
	}

	@Override
	public void delete(int slipId) {
		paymentSlipHeadDao.delete(slipId);
		paymentSlipDetailDao.delete(slipId);
	}
}
