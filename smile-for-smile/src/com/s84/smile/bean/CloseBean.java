package com.s84.smile.bean;

import java.util.ArrayList;
import java.util.List;

public class CloseBean {

	private CloseHeadBean closeHeadBean;
	private List<CloseOptionmenuBean> closeOptionmenuList = new ArrayList<CloseOptionmenuBean>();
	private List<CloseDiscountBean> closeDiscountList = new ArrayList<CloseDiscountBean>();

	public CloseHeadBean getCloseHeadBean() {
		return closeHeadBean;
	}

	public void setCloseHeadBean(CloseHeadBean closeHeadBean) {
		this.closeHeadBean = closeHeadBean;
	}

	public void addOptionmenu(CloseOptionmenuBean closeOptionmenuBean) {
		closeOptionmenuList.add(closeOptionmenuBean);
	}

	public void addDiscount(CloseDiscountBean closeDiscountBean) {
		closeDiscountList.add(closeDiscountBean);
	}

	public List<CloseOptionmenuBean> getCloseOptionmenuList() {
		return closeOptionmenuList;
	}

	public List<CloseDiscountBean> getCloseDiscountList() {
		return closeDiscountList;
	}

	public void setCloseOptionmenuList(List<CloseOptionmenuBean> closeOptionmenuList) {
		this.closeOptionmenuList = closeOptionmenuList;
	}

	public void setCloseDiscountList(List<CloseDiscountBean> closeDiscountList) {
		this.closeDiscountList = closeDiscountList;
	}

	//売上合計金額(従業員)
	public int getSalesTotalForEmployee() {
		int salesTotal = 0;

		Integer charge = closeHeadBean.getCourseChargeEmployee();
		if (charge != null) {
			salesTotal += charge;
		}
		charge = closeHeadBean.getCourseExtensionChargeEmployee();
		if (charge != null) {
			salesTotal += charge;
		}
		charge = closeHeadBean.getAppointChargeEmployee();
		if (charge != null) {
			salesTotal += charge;
		}

		for (int i = 0; i < closeOptionmenuList.size(); i++) {
			Integer optionCharge = closeOptionmenuList.get(i).getChargeEmployee();
			if (optionCharge != null) {
				salesTotal += optionCharge;
			}
		}
		return salesTotal;
	}

	//割引合計金額(従業員)
	public int getDiscountTotalForEmployee() {
		int discountTotal = 0;

		for (int i = 0; i < closeDiscountList.size(); i++) {
			Integer discount = closeDiscountList.get(i).getChargeEmployee();
			if (discount != null) {
				discountTotal += discount;
			}
		}

		return discountTotal;
	}

}
