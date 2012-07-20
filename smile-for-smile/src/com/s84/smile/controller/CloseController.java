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

import com.s84.smile.bean.CloseSearchConditionBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.service.CloseService;
import com.s84.smile.service.EmployeeService;
import com.s84.smile.service.SalesSlipService;
import com.s84.smile.util.DateUtil;

@Controller
public class CloseController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CloseService closeService;
	@Autowired
	private SalesSlipService salesSlipService;
	@Autowired
	private Validator attendanceSearchValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat dateFormat = DateUtil.getDateFormat();
		dateFormat.setLenient(false);	
		binder.registerCustomEditor(Date.class, "day", new CustomDateEditor(dateFormat, true));
	}

	//検索画面へ遷移(検索条件初期化)
	@RequestMapping(value="/close/search.html", method = RequestMethod.POST)
	public ModelAndView toSearch() {
		ModelAndView modelAndView = new ModelAndView();

		//検索画面モデル
		modelAndView.addObject(new CloseSearchConditionBean());

		modelAndView.setViewName("close/search");
		return modelAndView;
	}

	//検索画面へ遷移(検索条件維持)
	@RequestMapping(value="/close/search_keep.html", method = RequestMethod.POST)
	public ModelAndView toSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//検索画面モデル
		CloseSearchConditionBean closeSearchConditionBean = (CloseSearchConditionBean)session.getAttribute("closeSearchConditionBean");
		modelAndView.addObject(closeSearchConditionBean);

		modelAndView.setViewName("close/search");
		return modelAndView;
	}

	//検索
	@RequestMapping(value = "/close/doSearch.html", method = RequestMethod.POST)
	public ModelAndView doSearch(CloseSearchConditionBean closeSearchConditionBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.attendanceSearchValidator.validate(closeSearchConditionBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("close/search");
			return modelAndView;
		}		

		//検索条件をセッションに登録
		session.setAttribute("closeSearchConditionBean", closeSearchConditionBean);
		//検索
		modelAndView.addObject("closeSearchResultList", employeeService.selectAttendanceClose(closeSearchConditionBean.getDay()));
		modelAndView.setViewName("close/list");
		return modelAndView;
	}
	
	//締め登録
	@RequestMapping(value = "/close/entryClose.html", method = RequestMethod.POST)
	public ModelAndView entryClose(Date day, String employeeId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//売上伝票取得
		List<SalesSlipBean> salesSlipList = salesSlipService.selectByDayAndEmployeeId(day, employeeId);
		//締め処理
		closeService.close(salesSlipList, day, employeeId, (EmployeeBean)session.getAttribute("loginEmployee"));
		
		//検索
		CloseSearchConditionBean closeSearchConditionBean = (CloseSearchConditionBean)session.getAttribute("closeSearchConditionBean");
		modelAndView.addObject("closeSearchResultList", employeeService.selectAttendanceClose(closeSearchConditionBean.getDay()));
		modelAndView.setViewName("close/list");
		return modelAndView;
	}

	//締め取消
	@RequestMapping(value = "/close/cancelClose.html", method = RequestMethod.POST)
	public ModelAndView cancelClose(Date day, String employeeId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//締め処理取消
		closeService.closeCancel(day, employeeId);

		//検索
		CloseSearchConditionBean closeSearchConditionBean = (CloseSearchConditionBean)session.getAttribute("closeSearchConditionBean");
		modelAndView.addObject("closeSearchResultList", employeeService.selectAttendanceClose(closeSearchConditionBean.getDay()));
		modelAndView.setViewName("close/list");
		return modelAndView;
	}
}
