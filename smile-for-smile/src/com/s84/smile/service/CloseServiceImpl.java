package com.s84.smile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s84.smile.bean.CloseBean;
import com.s84.smile.bean.CloseDiscountBean;
import com.s84.smile.bean.CloseHeadBean;
import com.s84.smile.bean.CloseOptionmenuBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.PaymentSlipBean;
import com.s84.smile.bean.PaymentSlipDetailBean;
import com.s84.smile.bean.PaymentSlipHeadBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSlipDiscountBean;
import com.s84.smile.bean.SalesSlipHeadBean;
import com.s84.smile.bean.SalesSlipOptionmenuBean;
import com.s84.smile.dao.CloseDiscountDao;
import com.s84.smile.dao.CloseHeadDao;
import com.s84.smile.dao.CloseOptionmenuDao;
import com.s84.smile.util.DateUtil;

@Service
public class CloseServiceImpl implements CloseService {

	private static final int KYUYOCHINGIN = 1;
	private static final int TAXLIMIT = 12000;
	private static final int TAXRATE = 5;

	@Autowired
	private CloseHeadDao closeHeadDao;
	@Autowired
	private CloseOptionmenuDao closeOptionmenuDao;
	@Autowired
	private CloseDiscountDao closeDiscountDao;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private OptionmenuService optionmenuService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private PaymentSlipService paymentSlipService;

	@Override
	public void close(List<SalesSlipBean> salesSlipList, EmployeeBean employeeBean) {
		List<CloseBean> closeList = new ArrayList<CloseBean>();

		Integer paymentCharge = 0;
		for (SalesSlipBean salesSlipBean : salesSlipList) {
			CloseBean closeBean = new CloseBean();

			//オプション
			List<CloseOptionmenuBean> closeOptionmenuList = createCloseOptionmenuList(salesSlipBean.getSalesSlipOptionmenuList(), employeeBean);
			closeBean.setCloseOptionmenuList(closeOptionmenuList);

			//割引
			List<CloseDiscountBean> closeDiscountList = createCloseDiscountList(salesSlipBean.getSalesSlipDiscountList(), employeeBean);
			closeBean.setCloseDiscountList(closeDiscountList);
		
			//ヘッダ
			CloseHeadBean closeHeadBean = createCloseBean(salesSlipBean.getSalesSlipHeadBean(), employeeBean);
			closeBean.setCloseHeadBean(closeHeadBean);

			closeList.add(closeBean);
			paymentCharge += (closeBean.getSalesTotalForEmployee() - closeBean.getDiscountTotalForEmployee());
		}

		//支払伝票
		int paymentSlipId = 0;
		if (salesSlipList.size() > 0) {
			PaymentSlipBean paymentSlipBean = createPaymentSlipBean(salesSlipList, paymentCharge, employeeBean);
			//支払伝票登録
			paymentSlipId = paymentSlipService.insert(paymentSlipBean, employeeBean);
		}

		//締め伝票登録
		for (CloseBean closeBean: closeList) {
			//支払伝票番号
			closeBean.getCloseHeadBean().setPaymentSlipId(paymentSlipId);
			//ヘッダ登録
			closeHeadDao.insert(closeBean.getCloseHeadBean());

			//オプション登録
			List<CloseOptionmenuBean> closeOptionmenuList = closeBean.getCloseOptionmenuList();
			for (CloseOptionmenuBean closeOptionmenuBean: closeOptionmenuList) {
				closeOptionmenuDao.insert(closeOptionmenuBean);
			}

			//割引登録
			List<CloseDiscountBean> closeDiscountList = closeBean.getCloseDiscountList();
			for (CloseDiscountBean closeDiscountBean: closeDiscountList) {
				closeDiscountDao.insert(closeDiscountBean);
			}
		}
	}

	@Override
	public void closeCancel(Date day, String employeeId) {
		List<CloseHeadBean> closeHeadList = closeHeadDao.selectByDayAndEmployeeId(day, employeeId);
		int paymentSlipId = 0;
		for (CloseHeadBean closeHeadBean: closeHeadList) {
			paymentSlipId = closeHeadBean.getPaymentSlipId();
			List<Integer> closeSlipId = new ArrayList<Integer>();
			closeSlipId.add(closeHeadBean.getSlipId());
			//オプション削除
			closeOptionmenuDao.delete(closeSlipId);
			//割引削除
			closeDiscountDao.delete(closeSlipId);
		}
		//ヘッダ削除
		closeHeadDao.delete(day, employeeId);
		//支払伝票削除
		paymentSlipService.delete(paymentSlipId);
	}

	private PaymentSlipBean createPaymentSlipBean(List<SalesSlipBean> salesSlipList, Integer paymentCharge, EmployeeBean employeeBean) {
		SalesSlipHeadBean salesSlipHeadBean = salesSlipList.get(0).getSalesSlipHeadBean();
		
		PaymentSlipBean paymentSlipBean = new PaymentSlipBean();
		
		//支払伝票ヘッダ
		PaymentSlipHeadBean paymentSlipHeadBean = new PaymentSlipHeadBean();
		paymentSlipHeadBean.setDay(salesSlipHeadBean.getDay());
		EmployeeBean staff = employeeService.selectByEmployeeId(salesSlipHeadBean.getEmployeeId());
		paymentSlipHeadBean.setPayee(staff.getName());
		paymentSlipHeadBean.setUpDay(DateUtil.getDay(0));
		paymentSlipHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
	
		//支払伝票明細
		PaymentSlipDetailBean paymentSlipDetailBean = new PaymentSlipDetailBean();
		paymentSlipDetailBean.setAccount(KYUYOCHINGIN);
		paymentSlipDetailBean.setName("給料");
		Integer paymentChargeTax = paymentCharge;
		if (paymentCharge >= TAXLIMIT) {
			paymentChargeTax = (int)(paymentCharge * (100 - TAXRATE) / 100);
			paymentSlipDetailBean.setComment("税額控除" + (paymentCharge - paymentChargeTax));
		}
		paymentSlipDetailBean.setUnitPrice(paymentChargeTax);
		paymentSlipDetailBean.setAmount(1);
		paymentSlipDetailBean.setUpDay(DateUtil.getDay(0));
		paymentSlipDetailBean.setUpEmployeeId(employeeBean.getEmployeeId());

		paymentSlipBean.setPaymentSlipHeadBean(paymentSlipHeadBean);
		paymentSlipBean.addDetail(paymentSlipDetailBean);
		return paymentSlipBean;
	}
	
	private List<CloseOptionmenuBean> createCloseOptionmenuList(List<SalesSlipOptionmenuBean> salesSlipOptionmenuList, EmployeeBean employeeBean) {
		List<CloseOptionmenuBean> closeOptionmenuList = new ArrayList<CloseOptionmenuBean>();

		for (SalesSlipOptionmenuBean salesSlipOptionmenuBean: salesSlipOptionmenuList) {
			CloseOptionmenuBean closeOptionmenuBean = new CloseOptionmenuBean();

			closeOptionmenuBean.setSlipId(salesSlipOptionmenuBean.getSlipId());
			closeOptionmenuBean.setDetailId(salesSlipOptionmenuBean.getDetailId());
			closeOptionmenuBean.setOptionmenuId(salesSlipOptionmenuBean.getOptionmenuId());
			closeOptionmenuBean.setUpDay(DateUtil.getDay(0));
			closeOptionmenuBean.setUpEmployeeId(employeeBean.getEmployeeId());

			//オプション料金従業員支払割合
			int share = optionmenuService.selectByPrimaryKey(salesSlipOptionmenuBean.getOptionmenuId()).getShare();
			//オプション料金(従業員)
			closeOptionmenuBean.setChargeEmployee((int)(salesSlipOptionmenuBean.getCharge() * share / 100));
			//オプション料金(店舗)
			closeOptionmenuBean.setCharge(salesSlipOptionmenuBean.getCharge() - closeOptionmenuBean.getChargeEmployee());

			closeOptionmenuList.add(closeOptionmenuBean);
		}

		return closeOptionmenuList;
	}

	private List<CloseDiscountBean> createCloseDiscountList(List<SalesSlipDiscountBean> salesSlipDiscountList, EmployeeBean employeeBean) {
		List<CloseDiscountBean> closeDiscountList = new ArrayList<CloseDiscountBean>();

		for (SalesSlipDiscountBean salesSlipDiscountBean: salesSlipDiscountList) {
			CloseDiscountBean closeDiscountBean = new CloseDiscountBean();

			closeDiscountBean.setSlipId(salesSlipDiscountBean.getSlipId());
			closeDiscountBean.setDetailId(salesSlipDiscountBean.getDetailId());
			closeDiscountBean.setDiscountId(salesSlipDiscountBean.getDiscountId());
			closeDiscountBean.setUpDay(DateUtil.getDay(0));
			closeDiscountBean.setUpEmployeeId(employeeBean.getEmployeeId());

			//割引料金従業員支払割合
			int share = discountService.selectByPrimaryKey(salesSlipDiscountBean.getDiscountId()).getShare();
			//割引料金(従業員)
			closeDiscountBean.setChargeEmployee((int)(salesSlipDiscountBean.getCharge() * share / 100));
			//割引料金(店舗)
			closeDiscountBean.setCharge(salesSlipDiscountBean.getCharge() - closeDiscountBean.getChargeEmployee());

			closeDiscountList.add(closeDiscountBean);
		}

		return closeDiscountList;
	}

	private CloseHeadBean createCloseBean(SalesSlipHeadBean salesSlipHeadBean, EmployeeBean employeeBean) {
		CloseHeadBean closeHeadBean = new CloseHeadBean();

		closeHeadBean.setSlipId(salesSlipHeadBean.getSlipId());
		closeHeadBean.setDay(salesSlipHeadBean.getDay());
		closeHeadBean.setEmployeeId(salesSlipHeadBean.getEmployeeId());
		closeHeadBean.setMemberId(salesSlipHeadBean.getMemberId());
		closeHeadBean.setStartTime(salesSlipHeadBean.getStartTime());
		closeHeadBean.setEndTime(salesSlipHeadBean.getEndTime());
		closeHeadBean.setCourseClassId(salesSlipHeadBean.getCourseClassId());
		closeHeadBean.setCourseId(salesSlipHeadBean.getCourseId());
		closeHeadBean.setCourseExtensionId(salesSlipHeadBean.getCourseExtensionId());
		closeHeadBean.setAppointId(salesSlipHeadBean.getAppointId());
		closeHeadBean.setUpDay(DateUtil.getDay(0));
		closeHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
		closeHeadBean.setTax(0);
		closeHeadBean.setPaymentSlipId(0);

		//コース料金従業員支払割合
		int courseShare =  employeeService.selectByEmployeeId(salesSlipHeadBean.getEmployeeId()).getShare();
		//指名料金従業員支払割合
		Integer appointShare = null; 
		if (salesSlipHeadBean.getAppointId() != null) {
			appointShare = appointService.selectByPrimaryKey(salesSlipHeadBean.getAppointId()).getShare();
		}

		//コース料金(従業員)
		closeHeadBean.setCourseChargeEmployee((int)(salesSlipHeadBean.getCourseCharge() * courseShare / 100));
		//コース料金(店舗)
		closeHeadBean.setCourseCharge(salesSlipHeadBean.getCourseCharge() - closeHeadBean.getCourseChargeEmployee());

		if (salesSlipHeadBean.getCourseExtensionCharge() != null) {
			//コース延長料金(従業員)
			closeHeadBean.setCourseExtensionChargeEmployee((int)(salesSlipHeadBean.getCourseExtensionCharge() * courseShare / 100));
			//コース延長料金(店舗)
			closeHeadBean.setCourseExtensionCharge(salesSlipHeadBean.getCourseExtensionCharge() - closeHeadBean.getCourseExtensionChargeEmployee());
		}

		if (appointShare != null) {
			//指名料金(従業員)
			closeHeadBean.setAppointChargeEmployee((int)(salesSlipHeadBean.getAppointCharge() * appointShare / 100));
			//指名料金(店舗)
			closeHeadBean.setAppointCharge(salesSlipHeadBean.getAppointCharge() - closeHeadBean.getAppointChargeEmployee());
		}

		return closeHeadBean;
	}
}
