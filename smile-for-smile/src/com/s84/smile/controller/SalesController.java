package com.s84.smile.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.s84.smile.bean.DiscountBean;
import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.OptionmenuBean;
import com.s84.smile.bean.SalesSlipBean;
import com.s84.smile.bean.SalesSlipHeadBean;
import com.s84.smile.bean.SalesSlipSearchConditionBean;
import com.s84.smile.bean.SalesSlipSearchResultBean;
import com.s84.smile.service.AppointService;
import com.s84.smile.service.CloseService;
import com.s84.smile.service.CourseClassService;
import com.s84.smile.service.CourseExtensionService;
import com.s84.smile.service.CourseService;
import com.s84.smile.service.DiscountService;
import com.s84.smile.service.EmployeeService;
import com.s84.smile.service.OptionmenuService;
import com.s84.smile.service.SalesSlipService;
import com.s84.smile.util.DateUtil;

@Controller
public class SalesController {

	private static final int SALES_SLIP_DISCOUNT_COUNT = 4;
	private static final int SALES_SLIP_OPTIONMENU_COUNT = 2;
	@Autowired
	private CourseClassService courseClassService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SalesSlipService salesSlipService;
	@Autowired
	private Validator salesSlipEntryValidator;
	@Autowired
	private Validator salesSlipSearchValidator;
	@Autowired
	private CourseExtensionService courseExtensionService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private OptionmenuService optionmenuService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private CloseService closeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat dateFormat = DateUtil.getDateFormat();
		DateFormat timeFormat = DateUtil.getTimeFormat();

		dateFormat.setLenient(false);	
		timeFormat.setLenient(false);
		//売上伝票登録画面
		binder.registerCustomEditor(Date.class, "salesSlipHeadBean.day", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "salesSlipHeadBean.startTime", new CustomDateEditor(timeFormat, true));
		binder.registerCustomEditor(Date.class, "salesSlipHeadBean.endTime", new CustomDateEditor(timeFormat, true));
		//売上伝票検索画面
		binder.registerCustomEditor(Date.class, "dayFrom", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "dayTo", new CustomDateEditor(dateFormat, true));
	}

	//売上伝票登録画面へ遷移(新規)
	@RequestMapping(value = "/sales/newEntry.html", method = RequestMethod.POST)
	public ModelAndView toEntry() {
		ModelAndView modelAndView = new ModelAndView();

		//売上伝票登録画面モデル
		modelAndView.addObject("salesSlipBean", new SalesSlipBean());
		//従業員SELECT
		modelAndView.addObject("staff", employeeService.selectAll());
		//コース分類SELECT
		modelAndView.addObject("courseClass", courseClassService.selectAll());
		//指名SELECT
		modelAndView.addObject("appoint", appointService.selectAll());
		//オプションSELECT
		List<List<OptionmenuBean>> optionmenuList = new ArrayList<List<OptionmenuBean>>();
		for (int i = 0; i < SALES_SLIP_OPTIONMENU_COUNT; i++) {
			optionmenuList.add(optionmenuService.selectAll());
		}
		modelAndView.addObject("optionmenuList", optionmenuList);
		//割引SELECT
		List<List<DiscountBean>> discountList = new ArrayList<List<DiscountBean>>();
		for (int i = 0; i < SALES_SLIP_DISCOUNT_COUNT; i++) {
			discountList.add(discountService.selectAll());
		}
		modelAndView.addObject("discountList", discountList);
		//売上伝票登録画面
		modelAndView.setViewName("sales/entry");

		return modelAndView;
	}

	//売上伝票検索画面へ遷移(検索条件初期化)
	@RequestMapping(value="/sales/search.html", method = RequestMethod.POST)
	public ModelAndView toSearch() {
		ModelAndView modelAndView = new ModelAndView();
		//従業員SELECT
		modelAndView.addObject("staff", employeeService.selectAll());
		//売上伝票検索画面モデル
		modelAndView.addObject(new SalesSlipSearchConditionBean());
		modelAndView.setViewName("sales/search");
		return modelAndView;
	}

	//売上伝票検索画面へ遷移(検索条件維持)
	@RequestMapping(value="/sales/search_keep.html", method = RequestMethod.POST)
	public ModelAndView toSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		//従業員SELECT
		modelAndView.addObject("staff", employeeService.selectAll());
		//売上伝票検索画面モデル
		SalesSlipSearchConditionBean salesSlipSearchConditionBean = (SalesSlipSearchConditionBean)session.getAttribute("salesSlipSearchConditionBean");
		modelAndView.addObject(salesSlipSearchConditionBean);
		modelAndView.setViewName("sales/search");
		return modelAndView;
	}

	//売上伝票登録画面へ遷移(修正)
	@RequestMapping(value = "/sales/updateEntry.html", method = RequestMethod.GET)
	public ModelAndView toEntry(Integer slipId) {
		ModelAndView modelAndView = new ModelAndView();

		//売上伝票登録画面モデル
		SalesSlipBean salesSlipBean = salesSlipService.selectBySlipId(slipId);
		modelAndView.addObject("salesSlipBean", salesSlipBean);
		refreshSelect(salesSlipBean, modelAndView);
		//締め伝票
		CloseHeadBean closeHeadBean = closeService.selectBySalesSlipId(slipId);
		modelAndView.addObject("closeHeadBean", closeHeadBean);
		//売上伝票登録画面
		modelAndView.setViewName("sales/entry");

		return modelAndView;
	}
	
	//登録
	@RequestMapping(value = "/sales/doEntry.html", method = RequestMethod.POST)
	public ModelAndView entry(SalesSlipBean salesSlipBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.salesSlipEntryValidator.validate(salesSlipBean, bindingResult);
		if (bindingResult.hasErrors()) {
			refreshSelect(salesSlipBean, modelAndView);
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("sales/entry");
			return modelAndView;
		}

		//売上伝票登録
		if (salesSlipBean.getSalesSlipHeadBean().getSlipId() == null) {
			//新規登録
			salesSlipService.insert(salesSlipBean, (EmployeeBean)session.getAttribute("loginEmployee"));
			//売上伝票登録完了画面
			modelAndView.setViewName("sales/entryFinish");
		} else {
			//修正
			salesSlipService.update(salesSlipBean, (EmployeeBean)session.getAttribute("loginEmployee"));
			//検索条件
			SalesSlipSearchConditionBean salesSlipSearchConditionBean = (SalesSlipSearchConditionBean)session.getAttribute("salesSlipSearchConditionBean");
			//検索
			List<SalesSlipSearchResultBean> salesSlipSearchResultList = salesSlipService.selectSalesSlipHead(salesSlipSearchConditionBean);
			modelAndView.addObject("salesSlipSearchResultList", salesSlipSearchResultList);
			modelAndView.addObject("size", salesSlipSearchResultList.size());
			//売上伝票検索画面モデル
			modelAndView.addObject(salesSlipSearchConditionBean);
			//売上伝票一覧画面
			modelAndView.setViewName("sales/list");
		}
		
		return modelAndView;
	}

	//検索
	@RequestMapping(value = "/sales/doSearch.html", method = RequestMethod.POST)
	public ModelAndView doSearch(SalesSlipSearchConditionBean salesSlipSearchConditionBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		//バリデーション
		this.salesSlipSearchValidator.validate(salesSlipSearchConditionBean, bindingResult);
		if (bindingResult.hasErrors()) {
			//従業員SELECT
			modelAndView.addObject("staff", employeeService.selectAll());
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("sales/search");
			return modelAndView;
		}		

		//検索条件をセッションに登録
		session.setAttribute("salesSlipSearchConditionBean", salesSlipSearchConditionBean);
		//検索
		List<SalesSlipSearchResultBean> salesSlipSearchResultList = salesSlipService.selectSalesSlipHead(salesSlipSearchConditionBean);
		modelAndView.addObject("salesSlipSearchResultList", salesSlipSearchResultList);
		modelAndView.addObject("size", salesSlipSearchResultList.size());
		modelAndView.setViewName("sales/list");
		return modelAndView;
	}

	//検索
	@RequestMapping(value = "/sales/list.html", method = RequestMethod.POST)
	public ModelAndView doSearch(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		//検索条件をセッションから取得
		SalesSlipSearchConditionBean salesSlipSearchConditionBean = (SalesSlipSearchConditionBean)session.getAttribute("salesSlipSearchConditionBean");
		//検索
		List<SalesSlipSearchResultBean> salesSlipSearchResultList = salesSlipService.selectSalesSlipHead(salesSlipSearchConditionBean);
		modelAndView.addObject("salesSlipSearchResultList", salesSlipSearchResultList);
		modelAndView.addObject("size", salesSlipSearchResultList.size());
		modelAndView.setViewName("sales/list");
		return modelAndView;
	}

	//コース分類変更時
	@RequestMapping(value = "/sales/changeCourseClass.html", method = RequestMethod.POST)
	public ModelAndView changeCourseClass(SalesSlipBean salesSlipBean, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		SalesSlipHeadBean salesSlipHeadBean = salesSlipBean.getSalesSlipHeadBean();
		//コースID、コース料金初期化
		salesSlipHeadBean.setCourseId(null);
		salesSlipHeadBean.setCourseCharge(null);
		//コース延長ID、コース延長料金初期化
		salesSlipHeadBean.setCourseExtensionId(null);
		salesSlipHeadBean.setCourseExtensionCharge(null);
		//イン初期化
		salesSlipHeadBean.setStartTime(null);
		//SELECT
		refreshSelect(salesSlipBean, modelAndView);

		modelAndView.setViewName("sales/entry");
		return modelAndView;
	}

	//コース変更時
	@RequestMapping(value = "/sales/changeCourse.html", method = RequestMethod.POST)
	public ModelAndView changeCourse(SalesSlipBean salesSlipBean, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		//コース料金再設定
		salesSlipBean.setCourseCharge(courseService);
		//イン再設定
		salesSlipBean.setStartTime(courseService, courseExtensionService);
		if (salesSlipBean.getSalesSlipHeadBean().getCourseId() == null) {
			salesSlipBean.getSalesSlipHeadBean().setCourseExtensionCharge(null);
		}
		//SELECT
		refreshSelect(salesSlipBean, modelAndView);

		modelAndView.setViewName("sales/entry");
		return modelAndView;
	}

	//コース延長変更時
	@RequestMapping(value = "/sales/changeCourseExtension.html", method = RequestMethod.POST)
	public ModelAndView changeCourseExtension(SalesSlipBean salesSlipBean, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		//コース延長料金再設定
		salesSlipBean.setCourseExtensionCharge(courseExtensionService);
		//イン再設定
		salesSlipBean.setStartTime(courseService, courseExtensionService);
		//SELECT
		refreshSelect(salesSlipBean, modelAndView);

		modelAndView.setViewName("sales/entry");
		return modelAndView;
	}

	//指名変更時
	@RequestMapping(value = "/sales/changeAppoint.html", method = RequestMethod.POST)
	public ModelAndView changeAppoint(SalesSlipBean salesSlipBean, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		//指名料金再設定
		salesSlipBean.setAppointCharge(appointService);
		//SELECT
		refreshSelect(salesSlipBean, modelAndView);

		modelAndView.setViewName("sales/entry");
		return modelAndView;
	}

	//オプション変更時
	@RequestMapping(value = "/sales/changeOptionmenu*.html", method = RequestMethod.POST)
	public ModelAndView changeOptionmenu(SalesSlipBean salesSlipBean, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		String requestUri = request.getRequestURI(); // requestUri:/sales/changeOptionmenu0.html
		int firstIndex = 0;
		int lastIndex = requestUri.indexOf(".");
		for (int i = 0; i < salesSlipBean.getSalesSlipOptionmenuList().size(); i++) {
			firstIndex = requestUri.indexOf(String.valueOf(i));
			if (firstIndex > -1) {
				break;
			}
		}
		int index = Integer.parseInt(requestUri.substring(firstIndex, lastIndex));

		//オプション料金再設定
		salesSlipBean.setOptionmenuCharge(optionmenuService, index);
		//SELECT
		refreshSelect(salesSlipBean, modelAndView);

		modelAndView.setViewName("sales/entry");
		return modelAndView;
	}

	//割引変更時
	@RequestMapping(value = "/sales/changeDiscount*.html", method = RequestMethod.POST)
	public ModelAndView changeDiscount(SalesSlipBean salesSlipBean, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		String requestUri = request.getRequestURI(); // requestUri:/sales/changeDiscount0.html
		int firstIndex = 0;
		int lastIndex = requestUri.indexOf(".");
		for (int i = 0; i < salesSlipBean.getSalesSlipDiscountList().size(); i++) {
			firstIndex = requestUri.indexOf(String.valueOf(i));
			if (firstIndex > -1) {
				break;
			}
		}
		int index = Integer.parseInt(requestUri.substring(firstIndex, lastIndex));

		//割引料金再設定
		salesSlipBean.setDiscountCharge(discountService, index);
		//SELECT
		refreshSelect(salesSlipBean, modelAndView);

		modelAndView.setViewName("sales/entry");
		return modelAndView;
	}

	private void refreshSelect(SalesSlipBean salesSlipBean, ModelAndView modelAndView) {
		//従業員SELECT
		modelAndView.addObject("staff", employeeService.selectAll());
		//コース分類SELECT
		modelAndView.addObject("courseClass", courseClassService.selectAll());
		//コースSELECT、延長SELECT
		Integer courseClassId = salesSlipBean.getSalesSlipHeadBean().getCourseClassId();
		if (courseClassId == null) {
			modelAndView.addObject("course", null);
			modelAndView.addObject("courseExtension", null);			
		} else {
			modelAndView.addObject("course", courseService.selectByCourseClassId(courseClassId));
			if (salesSlipBean.getSalesSlipHeadBean().getCourseId() == null) {
				modelAndView.addObject("courseExtension", null);
			} else {
				modelAndView.addObject("courseExtension", courseExtensionService.selectByCourseClassId(courseClassId));					
			}
		}
		//指名SELECT
		modelAndView.addObject("appoint", appointService.selectAll());
		//オプションSELECT
		List<List<OptionmenuBean>> optionmenuList = new ArrayList<List<OptionmenuBean>>();
		for (int i = 0; i < SALES_SLIP_OPTIONMENU_COUNT; i++) {
			optionmenuList.add(optionmenuService.selectAll());
		}
		modelAndView.addObject("optionmenuList", optionmenuList);
		//割引SELECT
		List<List<DiscountBean>> discountList = new ArrayList<List<DiscountBean>>();
		for (int i = 0; i < SALES_SLIP_DISCOUNT_COUNT; i++) {
			discountList.add(discountService.selectAll());
		}
		modelAndView.addObject("discountList", discountList);
		
		//締め伝票
		if (salesSlipBean.getSalesSlipHeadBean().getSlipId() != null) {
			CloseHeadBean closeHeadBean = closeService.selectBySalesSlipId(salesSlipBean.getSalesSlipHeadBean().getSlipId());
			modelAndView.addObject("closeHeadBean", closeHeadBean);
		}
	}
}
