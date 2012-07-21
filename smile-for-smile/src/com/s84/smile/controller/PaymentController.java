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

import com.s84.smile.bean.CloseHeadBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.PaymentSlipBean;
import com.s84.smile.bean.PaymentSlipSearchConditionBean;
import com.s84.smile.bean.PaymentSlipSearchResultBean;
import com.s84.smile.service.AccountService;
import com.s84.smile.service.CloseService;
import com.s84.smile.service.PaymentSlipService;
import com.s84.smile.util.DateUtil;

@Controller
public class PaymentController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private PaymentSlipService paymentSlipService;
	@Autowired
	private Validator paymentSlipEntryValidator;
	@Autowired
	private Validator paymentSlipSearchValidator;
	@Autowired
	private CloseService closeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat dateFormat = DateUtil.getDateFormat();

		dateFormat.setLenient(false);	
		//支払伝票登録画面
		binder.registerCustomEditor(Date.class, "paymentSlipHeadBean.day", new CustomDateEditor(dateFormat, true));
		//支払伝票検索画面
		binder.registerCustomEditor(Date.class, "dayFrom", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "dayTo", new CustomDateEditor(dateFormat, true));
	}

	//支払伝票登録画面へ遷移(新規)
	@RequestMapping(value = "/payment/newEntry.html", method = RequestMethod.POST)
	public ModelAndView toEntry() {
		ModelAndView modelAndView = new ModelAndView();

		//支払伝票登録画面モデル
		modelAndView.addObject("paymentSlipBean", new PaymentSlipBean());
		//勘定科目SELECT
		modelAndView.addObject("account", accountService.selectAll());
		//支払伝票登録画面
		modelAndView.setViewName("payment/entry");

		return modelAndView;
	}

	//支払伝票検索画面へ遷移(検索条件初期化)
	@RequestMapping(value="/payment/search.html", method = RequestMethod.POST)
	public ModelAndView toSearch() {
		ModelAndView modelAndView = new ModelAndView();
		//支払伝票検索画面モデル
		modelAndView.addObject(new PaymentSlipSearchConditionBean());
		modelAndView.setViewName("payment/search");
		return modelAndView;
	}

	//支払伝票検索画面へ遷移(検索条件維持)
	@RequestMapping(value="/payment/search_keep.html", method = RequestMethod.POST)
	public ModelAndView toSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		//支払伝票検索画面モデル
		PaymentSlipSearchConditionBean paymentSlipSearchConditionBean = (PaymentSlipSearchConditionBean)session.getAttribute("paymentSlipSearchConditionBean");
		modelAndView.addObject(paymentSlipSearchConditionBean);
		modelAndView.setViewName("payment/search");
		return modelAndView;
	}

	//支払伝票登録画面へ遷移(修正)
	@RequestMapping(value = "/payment/updateEntry.html", method = RequestMethod.GET)
	public ModelAndView toEntry(Integer slipId) {
		ModelAndView modelAndView = new ModelAndView();

		//支払伝票登録画面モデル
		PaymentSlipBean paymentSlipBean = paymentSlipService.selectBySlipId(slipId);
		modelAndView.addObject("paymentSlipBean", paymentSlipBean);
		//勘定科目SELECT
		modelAndView.addObject("account", accountService.selectAll());
		//締め伝票
		CloseHeadBean closeHeadBean = closeService.selectByPaymentSlipId(slipId);
		modelAndView.addObject("closeHeadBean", closeHeadBean);
		//支払伝票登録画面
		modelAndView.setViewName("payment/entry");

		return modelAndView;
	}
	//金額計算
	@RequestMapping(value = "/payment/calc.html", method = RequestMethod.POST)
	public ModelAndView doCalc(PaymentSlipBean paymentSlipBean, BindingResult bindingResult) {
		//計算ロジックはBeanにあり(PaymentSlipDetailBean#getCharge)

		ModelAndView modelAndView = new ModelAndView();

		//勘定科目SELECT
		modelAndView.addObject("account", accountService.selectAll());

		//締め伝票
		if (paymentSlipBean.getPaymentSlipHeadBean().getSlipId() != null) {
			CloseHeadBean closeHeadBean = closeService.selectByPaymentSlipId(paymentSlipBean.getPaymentSlipHeadBean().getSlipId());
			modelAndView.addObject("closeHeadBean", closeHeadBean);
		}

		//支払伝票登録画面
		modelAndView.setViewName("payment/entry");

		return modelAndView;
	}

	//登録
	@RequestMapping(value = "/payment/doEntry.html", method = RequestMethod.POST)
	public ModelAndView entry(PaymentSlipBean paymentSlipBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.paymentSlipEntryValidator.validate(paymentSlipBean, bindingResult);
		if (bindingResult.hasErrors()) {
			//勘定科目SELECT
			modelAndView.addObject("account", accountService.selectAll());
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("payment/entry");
			return modelAndView;
		}

		//支払伝票登録
		if (paymentSlipBean.getPaymentSlipHeadBean().getSlipId() == null) {
			//新規登録
			paymentSlipService.insert(paymentSlipBean, (EmployeeBean)session.getAttribute("loginEmployee"));
			//売上伝票登録完了画面
			modelAndView.setViewName("payment/entryFinish");
		} else {
			//修正
			paymentSlipService.update(paymentSlipBean, (EmployeeBean)session.getAttribute("loginEmployee"));
			//検索条件
			PaymentSlipSearchConditionBean paymentSlipSearchConditionBean = (PaymentSlipSearchConditionBean)session.getAttribute("paymentSlipSearchConditionBean");
			//検索
			List<PaymentSlipSearchResultBean> paymentSlipSearchResultList = paymentSlipService.selectPaymentSlipHead(paymentSlipSearchConditionBean);
			modelAndView.addObject("paymentSlipSearchResultList", paymentSlipSearchResultList);
			modelAndView.addObject("size", paymentSlipSearchResultList.size());
			//売上伝票検索画面モデル
			modelAndView.addObject(paymentSlipSearchConditionBean);
			//売上伝票一覧画面
			modelAndView.setViewName("payment/list");
		}
		
		return modelAndView;
	}

	//検索
	@RequestMapping(value = "/payment/doSearch.html", method = RequestMethod.POST)
	public ModelAndView doSearch(PaymentSlipSearchConditionBean paymentSlipSearchConditionBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		//バリデーション
		this.paymentSlipSearchValidator.validate(paymentSlipSearchConditionBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("payment/search");
			return modelAndView;
		}		

		//検索条件をセッションに登録
		session.setAttribute("paymentSlipSearchConditionBean", paymentSlipSearchConditionBean);
		//検索
		List<PaymentSlipSearchResultBean> paymentSlipSearchResultList = paymentSlipService.selectPaymentSlipHead(paymentSlipSearchConditionBean);
		modelAndView.addObject("paymentSlipSearchResultList", paymentSlipSearchResultList);
		modelAndView.addObject("size", paymentSlipSearchResultList.size());
		modelAndView.setViewName("payment/list");
		return modelAndView;
	}

	//検索(支払伝票修正画面から支払伝票一覧画面に戻る)
	@RequestMapping(value = "/payment/list.html", method = RequestMethod.POST)
	public ModelAndView doSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//検索条件をセッションから取得
		PaymentSlipSearchConditionBean paymentSlipSearchConditionBean = (PaymentSlipSearchConditionBean)session.getAttribute("paymentSlipSearchConditionBean");
		//検索
		List<PaymentSlipSearchResultBean> paymentSlipSearchResultList = paymentSlipService.selectPaymentSlipHead(paymentSlipSearchConditionBean);
		modelAndView.addObject("paymentSlipSearchResultList", paymentSlipSearchResultList);
		modelAndView.addObject("size", paymentSlipSearchResultList.size());
		modelAndView.setViewName("payment/list");
		return modelAndView;
	}

}
