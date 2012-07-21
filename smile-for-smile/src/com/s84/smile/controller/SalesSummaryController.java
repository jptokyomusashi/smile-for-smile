package com.s84.smile.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.SalesSummarySearchConditionBean;
import com.s84.smile.bean.SalesSummarySearchResultBean;
import com.s84.smile.service.CloseService;
import com.s84.smile.util.DateUtil;

@Controller
public class SalesSummaryController {

	@Autowired
	private CloseService closeService;
	@Autowired
	private Validator salesSummarySearchValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat dateFormat = DateUtil.getDateFormat();
		dateFormat.setLenient(false);	
		binder.registerCustomEditor(Date.class, "dayFrom", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "dayTo", new CustomDateEditor(dateFormat, true));
	}

	//検索画面へ遷移(検索条件初期化)
	@RequestMapping(value="/salessummary/search.html", method = RequestMethod.POST)
	public ModelAndView toSearch() {
		ModelAndView modelAndView = new ModelAndView();

		//検索画面モデル
		modelAndView.addObject(new SalesSummarySearchConditionBean());

		modelAndView.setViewName("salessummary/search");
		return modelAndView;
	}

	//検索画面へ遷移(検索条件維持)
	@RequestMapping(value="/salessummary/search_keep.html", method = RequestMethod.POST)
	public ModelAndView toSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//検索画面モデル
		SalesSummarySearchConditionBean salesSummarySearchConditionBean = (SalesSummarySearchConditionBean)session.getAttribute("salesSummarySearchConditionBean");
		modelAndView.addObject(salesSummarySearchConditionBean);

		modelAndView.setViewName("salessummary/search");
		return modelAndView;
	}

	//検索
	@RequestMapping(value = "/salessummary/doSearch.html", method = RequestMethod.POST)
	public ModelAndView doSearch(SalesSummarySearchConditionBean salesSummarySearchConditionBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.salesSummarySearchValidator.validate(salesSummarySearchConditionBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("salessummary/search");
			return modelAndView;
		}		

		//検索条件をセッションに登録
		session.setAttribute("salesSummarySearchConditionBean", salesSummarySearchConditionBean);
		//検索
		List<SalesSummarySearchResultBean> salesSummarySearchResultList = closeService.selectSalesSummary(salesSummarySearchConditionBean.getDayFrom(), salesSummarySearchConditionBean.getDayTo());
		modelAndView.addObject("salesSummarySearchResultList", salesSummarySearchResultList);
		modelAndView.addObject("size", salesSummarySearchResultList.size());
		//合計
		Integer totalChargeEmployee = 0;
		Integer totalChargeShop = 0;
		Integer totalDiscountEmployee = 0;
		Integer totalDiscountShop = 0;
		for (SalesSummarySearchResultBean salesSummarySearchResultBean: salesSummarySearchResultList) {
			totalChargeEmployee += (salesSummarySearchResultBean.getChargeEmployee() == null ? 0 : salesSummarySearchResultBean.getChargeEmployee());
			totalChargeShop += (salesSummarySearchResultBean.getChargeShop() == null ? 0 : salesSummarySearchResultBean.getChargeShop());
			totalDiscountEmployee += (salesSummarySearchResultBean.getDiscountEmployee() == null ? 0 : salesSummarySearchResultBean.getDiscountEmployee());
			totalDiscountShop += (salesSummarySearchResultBean.getDiscountShop() == null ? 0 : salesSummarySearchResultBean.getDiscountShop());
		}
		modelAndView.addObject("totalChargeEmployee", totalChargeEmployee);
		modelAndView.addObject("totalChargeShop", totalChargeShop);
		modelAndView.addObject("totalDiscountEmployee", totalDiscountEmployee);
		modelAndView.addObject("totalDiscountShop", totalDiscountShop);

		modelAndView.setViewName("salessummary/list");
		return modelAndView;
	}
}
