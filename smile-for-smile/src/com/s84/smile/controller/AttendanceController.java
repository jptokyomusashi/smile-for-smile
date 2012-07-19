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

import com.s84.smile.bean.AttendanceBean;
import com.s84.smile.bean.AttendanceSearchConditionBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.service.AttendanceService;
import com.s84.smile.service.CloseService;
import com.s84.smile.service.EmployeeService;
import com.s84.smile.service.SalesSlipService;
import com.s84.smile.util.DateUtil;

@Controller
public class AttendanceController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AttendanceService attendanceService;
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
	@RequestMapping(value="/attendance/search.html", method = RequestMethod.POST)
	public ModelAndView toSearch() {
		ModelAndView modelAndView = new ModelAndView();

		//検索画面モデル
		modelAndView.addObject(new AttendanceSearchConditionBean());

		modelAndView.setViewName("attendance/search");
		return modelAndView;
	}

	//検索画面へ遷移(検索条件維持)
	@RequestMapping(value="/attendance/search_keep.html", method = RequestMethod.POST)
	public ModelAndView toSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//検索画面モデル
		AttendanceSearchConditionBean attendanceSearchConditionBean = (AttendanceSearchConditionBean)session.getAttribute("attendanceSearchConditionBean");
		modelAndView.addObject(attendanceSearchConditionBean);

		modelAndView.setViewName("attendance/search");
		return modelAndView;
	}

	//検索
	@RequestMapping(value = "/attendance/doSearch.html", method = RequestMethod.POST)
	public ModelAndView doSearch(AttendanceSearchConditionBean attendanceSearchConditionBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.attendanceSearchValidator.validate(attendanceSearchConditionBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("attendance/search");
			return modelAndView;
		}		

		//検索条件をセッションに登録
		session.setAttribute("attendanceSearchConditionBean", attendanceSearchConditionBean);
		//検索
		modelAndView.addObject("attendanceSearchResultList", employeeService.selectAttendanceClose(attendanceSearchConditionBean.getDay()));
		modelAndView.setViewName("attendance/list");
		return modelAndView;
	}
	
	//締め登録
	@RequestMapping(value = "/attendance/entryClose.html", method = RequestMethod.POST)
	public ModelAndView entryClose(Date day, String employeeId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//売上伝票取得
		List<SalesSlipBean> salesSlipList = salesSlipService.selectByDayAndEmployeeId(day, employeeId);
		//締め処理
		closeService.close(salesSlipList, (EmployeeBean)session.getAttribute("loginEmployee"));
		
		//検索
		AttendanceSearchConditionBean attendanceSearchConditionBean = (AttendanceSearchConditionBean)session.getAttribute("attendanceSearchConditionBean");
		modelAndView.addObject("attendanceSearchResultList", employeeService.selectAttendanceClose(attendanceSearchConditionBean.getDay()));
		modelAndView.setViewName("attendance/list");
		return modelAndView;
	}

	//締め取消
	@RequestMapping(value = "/attendance/cancelClose.html", method = RequestMethod.POST)
	public ModelAndView cancelClose(Date day, String employeeId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//締め処理取消
		closeService.closeCancel(day, employeeId);

		//検索
		AttendanceSearchConditionBean attendanceSearchConditionBean = (AttendanceSearchConditionBean)session.getAttribute("attendanceSearchConditionBean");
		modelAndView.addObject("attendanceSearchResultList", employeeService.selectAttendanceClose(attendanceSearchConditionBean.getDay()));
		modelAndView.setViewName("attendance/list");
		return modelAndView;
	}
}
