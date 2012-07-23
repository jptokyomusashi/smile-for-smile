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
import com.s84.smile.bean.SalesSummarySearchResultBean;
import com.s84.smile.dao.CloseDao;
import com.s84.smile.dao.CloseDiscountDao;
import com.s84.smile.dao.CloseHeadDao;
import com.s84.smile.dao.CloseOptionmenuDao;
import com.s84.smile.util.DateUtil;

@Service
public class CloseServiceImpl implements CloseService {

	private static final int ACCOUNT_KYUYO = 1;
	private static final int TAXLIMIT = 12000;
	private static final int TAXRATE = 5;
	private static final int MINPAYMENT = 6000;
	
	@Autowired
	private CloseDao closeDao;
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
	public void insert(CloseBean closeBean) {
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

	@Override
	public void close(List<SalesSlipBean> salesSlipList, Date day, String employeeId, EmployeeBean employeeBean) {
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
			CloseHeadBean closeHeadBean = createCloseHeadBean(salesSlipBean.getSalesSlipHeadBean(), employeeBean);
			closeBean.setCloseHeadBean(closeHeadBean);

			closeList.add(closeBean);
			paymentCharge += (closeBean.getSalesTotalForEmployee() - closeBean.getDiscountTotalForEmployee());
		}

		if (salesSlipList.size() > 0) {
			//税額計算
			int tax = 0;
			if (paymentCharge >= TAXLIMIT) {
				tax = (int)(paymentCharge * (TAXRATE) / 100.0);
			}
			//最低保証額
			int payment = (paymentCharge < MINPAYMENT) ? MINPAYMENT : paymentCharge;

			//支払伝票
			PaymentSlipBean paymentSlipBean = createPaymentSlipBean(salesSlipList, payment, tax);
			//支払伝票登録
			int paymentSlipId = paymentSlipService.insert(paymentSlipBean, employeeBean);

			//締め伝票登録
			boolean first = true;
			for (CloseBean closeBean: closeList) {
				//支払伝票番号
				closeBean.getCloseHeadBean().setPaymentSlipId(paymentSlipId);
				if (first) {
					if (tax > 0) {
						//税額控除
						closeBean.getCloseHeadBean().setCourseChargeEmployee(closeBean.getCloseHeadBean().getCourseChargeEmployee() - tax);
						closeBean.getCloseHeadBean().setCourseCharge(closeBean.getCloseHeadBean().getCourseCharge() + tax);
						closeBean.getCloseHeadBean().setTax(tax);
					}

					if (paymentCharge < MINPAYMENT) {
						//最低保証額
						int balance = MINPAYMENT - paymentCharge;
						closeBean.getCloseHeadBean().setCourseChargeEmployee(closeBean.getCloseHeadBean().getCourseChargeEmployee() + balance);
						closeBean.getCloseHeadBean().setCourseCharge(closeBean.getCloseHeadBean().getCourseCharge() - balance);
					}
					first = false;
				}
				this.insert(closeBean);
			}
		} else {
			//売上伝票がない場合、最低支払額の支払のみ
			PaymentSlipBean paymentSlipBean = createPaymentSlipBean(day, employeeId);
			int paymentSlipId = paymentSlipService.insert(paymentSlipBean, employeeBean);
			//締め伝票
			CloseHeadBean closeHeadBean = createCloseHeadBean(day, employeeId, employeeBean);
			closeHeadBean.setPaymentSlipId(paymentSlipId);
			CloseBean closeBean = new CloseBean();
			closeBean.setCloseHeadBean(closeHeadBean);
			this.insert(closeBean);
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

	@Override
	public List<SalesSummarySearchResultBean> selectSalesSummary(Date dayFrom, Date dayTo) {
		return closeDao.selectSalesSummary(dayFrom, dayTo);
	}

	private PaymentSlipBean createPaymentSlipBean(List<SalesSlipBean> salesSlipList, int paymentCharge, int tax) {
		SalesSlipHeadBean salesSlipHeadBean = salesSlipList.get(0).getSalesSlipHeadBean();
		
		PaymentSlipBean paymentSlipBean = new PaymentSlipBean();
		
		//支払伝票ヘッダ
		PaymentSlipHeadBean paymentSlipHeadBean = new PaymentSlipHeadBean();
		paymentSlipHeadBean.setDay(salesSlipHeadBean.getDay());
		EmployeeBean staff = employeeService.selectByEmployeeId(salesSlipHeadBean.getEmployeeId());
		paymentSlipHeadBean.setPayee(staff.getName());
	
		//支払伝票明細
		PaymentSlipDetailBean paymentSlipDetailBean = new PaymentSlipDetailBean();
		paymentSlipDetailBean.setAccount(ACCOUNT_KYUYO);
		paymentSlipDetailBean.setName("給料");
		paymentSlipDetailBean.setComment("税額控除" + tax + "円");
		paymentSlipDetailBean.setUnitPrice(paymentCharge - tax);
		paymentSlipDetailBean.setAmount(1);

		paymentSlipBean.setPaymentSlipHeadBean(paymentSlipHeadBean);
		paymentSlipBean.addDetail(paymentSlipDetailBean);
		return paymentSlipBean;
	}

	private PaymentSlipBean createPaymentSlipBean(Date day, String employeeId) {
		PaymentSlipBean paymentSlipBean = new PaymentSlipBean();
		
		//支払伝票ヘッダ
		PaymentSlipHeadBean paymentSlipHeadBean = new PaymentSlipHeadBean();
		paymentSlipHeadBean.setDay(day);
		EmployeeBean staff = employeeService.selectByEmployeeId(employeeId);
		paymentSlipHeadBean.setPayee(staff.getName());
	
		//支払伝票明細
		PaymentSlipDetailBean paymentSlipDetailBean = new PaymentSlipDetailBean();
		paymentSlipDetailBean.setAccount(ACCOUNT_KYUYO);
		paymentSlipDetailBean.setName("給料");
		paymentSlipDetailBean.setComment("税額控除0円");
		paymentSlipDetailBean.setUnitPrice(MINPAYMENT);
		paymentSlipDetailBean.setAmount(1);

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

	private CloseHeadBean createCloseHeadBean(SalesSlipHeadBean salesSlipHeadBean, EmployeeBean employeeBean) {
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

	private CloseHeadBean createCloseHeadBean(Date day, String employeeId, EmployeeBean employeeBean) {
		CloseHeadBean closeHeadBean = new CloseHeadBean();

		closeHeadBean.setDay(day);
		closeHeadBean.setEmployeeId(employeeId);
		closeHeadBean.setUpDay(DateUtil.getDay(0));
		closeHeadBean.setUpEmployeeId(employeeBean.getEmployeeId());
		//コース料金(従業員)
		closeHeadBean.setCourseChargeEmployee(MINPAYMENT);
		//コース料金(店舗)
		closeHeadBean.setCourseCharge(-MINPAYMENT);

		return closeHeadBean;
	}

	public CloseHeadBean selectBySalesSlipId(Integer salesSlipId) {
		return closeHeadDao.selectBySalesSlipId(salesSlipId);
	}

	public CloseHeadBean selectByPaymentSlipId(Integer paymentSlipId) {
		return closeHeadDao.selectByPaymentSlipId(paymentSlipId);
	}
}