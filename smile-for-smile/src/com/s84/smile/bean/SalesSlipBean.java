package com.s84.smile.bean;

import java.util.ArrayList;
import java.util.List;

import com.s84.smile.service.AppointService;
import com.s84.smile.service.CourseExtensionService;
import com.s84.smile.service.CourseService;
import com.s84.smile.service.DiscountService;
import com.s84.smile.service.OptionmenuService;
import com.s84.smile.util.DateUtil;

public class SalesSlipBean {

	private SalesSlipHeadBean salesSlipHeadBean;
	private List<SalesSlipDiscountBean> salesSlipDiscountList = new ArrayList<SalesSlipDiscountBean>();
	private List<SalesSlipOptionmenuBean> salesSlipOptionmenuList = new ArrayList<SalesSlipOptionmenuBean>();

	public SalesSlipHeadBean getSalesSlipHeadBean() {
		return salesSlipHeadBean;
	}

	public void setSalesSlipHeadBean(SalesSlipHeadBean salesSlipHeadBean) {
		this.salesSlipHeadBean = salesSlipHeadBean;
	}

	public void addDiscount(SalesSlipDiscountBean salesSlipDiscountBean) {
		salesSlipDiscountList.add(salesSlipDiscountBean);
	}

	public void addOptionmenu(SalesSlipOptionmenuBean salesSlipOptionmenuBean) {
		salesSlipOptionmenuList.add(salesSlipOptionmenuBean);
	}

	public List<SalesSlipDiscountBean> getSalesSlipDiscountList() {
		return salesSlipDiscountList;
	}

	public List<SalesSlipOptionmenuBean> getSalesSlipOptionmenuList() {
		return salesSlipOptionmenuList;
	}

	public void setSalesSlipDiscountList(List<SalesSlipDiscountBean> salesSlipDiscountList) {
		this.salesSlipDiscountList = salesSlipDiscountList;
	}

	public void setSalesSlipOptionmenuList(List<SalesSlipOptionmenuBean> salesSlipOptionmenuList) {
		this.salesSlipOptionmenuList = salesSlipOptionmenuList;
	}

	//売上合計金額
	public int getSalesTotal() {
		int salesTotal = 0;

		Integer charge = salesSlipHeadBean.getCourseCharge();
		if (charge != null) {
			salesTotal += charge;
		}
		charge = salesSlipHeadBean.getCourseExtensionCharge();
		if (charge != null) {
			salesTotal += charge;
		}
		charge = salesSlipHeadBean.getAppointCharge();
		if (charge != null) {
			salesTotal += charge;
		}

		for (int i = 0; i < salesSlipOptionmenuList.size(); i++) {
			Integer optionCharge = salesSlipOptionmenuList.get(i).getCharge();
			if (optionCharge != null) {
				salesTotal += optionCharge;
			}
		}
		return salesTotal;
	}

	//割引合計金額
	public int getDiscountTotal() {
		int discountTotal = 0;

		for (int i = 0; i < salesSlipDiscountList.size(); i++) {
			Integer discount = salesSlipDiscountList.get(i).getCharge();
			if (discount != null) {
				discountTotal += discount;
			}
		}

		return discountTotal;
	}

	//コース料金設定
	public void setCourseCharge(CourseService courseService) {
		Integer charge = null;
		if (salesSlipHeadBean.getCourseId() != null) {
			CourseBean courseBean = courseService.selectByPrimaryKey(salesSlipHeadBean.getCourseClassId(), salesSlipHeadBean.getCourseId());
			charge = courseBean.getCharge();					
		}
		salesSlipHeadBean.setCourseCharge(charge);
	}

	//コース延長料金設定
	public void setCourseExtensionCharge(CourseExtensionService courseExtensionService) {
		Integer charge = null;
		if (salesSlipHeadBean.getCourseExtensionId() != null) {
			CourseExtensionBean courseExtensionBean = courseExtensionService.selectByPrimaryKey(salesSlipHeadBean.getCourseClassId(), salesSlipHeadBean.getCourseExtensionId());
			charge = courseExtensionBean.getCharge();					
		}
		salesSlipHeadBean.setCourseExtensionCharge(charge);
	}

	//指名料金設定
	public void setAppointCharge(AppointService appointService) {
		Integer charge = null;
		if (salesSlipHeadBean.getAppointId() != null) {
			AppointBean appointBean = appointService.selectByPrimaryKey(salesSlipHeadBean.getAppointId());
			charge = appointBean.getCharge();					
		}
		salesSlipHeadBean.setAppointCharge(charge);
	}

	//オプション料金設定
	public void setOptionmenuCharge(OptionmenuService optionmenuService, int index) {
		Integer charge = null;
		SalesSlipOptionmenuBean salesSlipOptionmenuBean = salesSlipOptionmenuList.get(index);
		if (salesSlipOptionmenuBean.getOptionmenuId() != null) {
			OptionmenuBean optionmenuBean = optionmenuService.selectByPrimaryKey(salesSlipOptionmenuBean.getOptionmenuId());
			charge = optionmenuBean.getCharge();
		}
		salesSlipOptionmenuBean.setCharge(charge);
	}

	//割引料金設定
	public void setDiscountCharge(DiscountService discountService, int index) {
		Integer charge = null;
		SalesSlipDiscountBean salesSlipDiscountBean = salesSlipDiscountList.get(index);
		if (salesSlipDiscountBean.getDiscountId() != null) {
			DiscountBean discountBean = discountService.selectByPrimaryKey(salesSlipDiscountBean.getDiscountId());
			charge = discountBean.getCharge();
		}
		salesSlipDiscountBean.setCharge(charge);
	}

	//開始時刻設定
	public void setStartTime(CourseService courseService, CourseExtensionService courseExtensionService) {
		int time = 0;

		//コース時間
		if (salesSlipHeadBean.getCourseId() != null) {
			CourseBean courseBean = courseService.selectByPrimaryKey(salesSlipHeadBean.getCourseClassId(), salesSlipHeadBean.getCourseId());
			time += courseBean.getTime();
		}
		//コース延長時間
		if (salesSlipHeadBean.getCourseExtensionId() != null) {
			CourseExtensionBean courseExtensionBean = courseExtensionService.selectByPrimaryKey(salesSlipHeadBean.getCourseClassId(), salesSlipHeadBean.getCourseExtensionId());
			time += courseExtensionBean.getTime();
		}
		salesSlipHeadBean.setStartTime(DateUtil.getTime(salesSlipHeadBean.getEndTime(), -time));
	}
}
