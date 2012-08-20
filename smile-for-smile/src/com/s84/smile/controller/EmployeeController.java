package com.s84.smile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.EmployeeForMaintenanceBean;
import com.s84.smile.service.CodeService;
import com.s84.smile.service.EmployeeService;
import com.s84.smile.validator.EmployeeEntryValidator;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	CodeService codeService;
	@Autowired
	EmployeeEntryValidator employeeEntryValidator;

	//一覧
	@RequestMapping(value = "/employee/list.html", method = RequestMethod.POST)
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employee/list");

		List<EmployeeForMaintenanceBean> employeeList = employeeService.selectAllForMaintenance();
		modelAndView.addObject("employeeList", employeeList);
		return modelAndView;
	}

	//新規登録画面
	@RequestMapping(value = "/employee/newEntry.html", method = RequestMethod.POST)
	public ModelAndView newEntry() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employee/entry");
		modelAndView.addObject("employeeBean", new EmployeeBean());
		modelAndView.addObject("authorityList", codeService.selectByCategory("authority"));
		modelAndView.addObject("resignedList", codeService.selectByCategory("resigned"));
		return modelAndView;
	}

	//修正登録画面
	@RequestMapping(value = "/employee/toEntry.html", method = RequestMethod.POST)
	public ModelAndView toEntry(String employeeId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employee/entry");
		modelAndView.addObject("employeeBean", employeeService.selectByEmployeeId(employeeId));
		modelAndView.addObject("authorityList", codeService.selectByCategory("authority"));
		modelAndView.addObject("resignedList", codeService.selectByCategory("resigned"));
		return modelAndView;
	}

	//登録
	@RequestMapping(value = "/employee/doEntry.html", method = RequestMethod.POST)
	public ModelAndView doEntry(EmployeeBean employeeBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.employeeEntryValidator.validate(employeeBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("authorityList", codeService.selectByCategory("authority"));
			modelAndView.addObject("resignedList", codeService.selectByCategory("resigned"));
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("employee/entry");
			return modelAndView;
		}

		String loginEmployeeId = ((EmployeeBean)session.getAttribute("loginEmployee")).getEmployeeId();

		//登録
		if ("".equals(employeeBean.getUpEmployeeId())) {
			//新規登録
			employeeService.insert(employeeBean, loginEmployeeId);
			modelAndView.setViewName("employee/entryFinish");
		} else {
			//修正
			employeeService.update(employeeBean, loginEmployeeId);
			//検索
			List<EmployeeForMaintenanceBean> employeeList = employeeService.selectAllForMaintenance();
			modelAndView.addObject("employeeList", employeeList);		
			modelAndView.setViewName("employee/list");
		}

		return modelAndView;
	}

	//削除
	@RequestMapping(value = "/employee/doDelete.html", method = RequestMethod.POST)
	public ModelAndView doDelete(EmployeeBean employeeBean) {
		ModelAndView modelAndView = new ModelAndView();
		//削除
		employeeService.delete(employeeBean);
		//検索
		List<EmployeeForMaintenanceBean> employeeList = employeeService.selectAllForMaintenance();
		modelAndView.addObject("employeeList", employeeList);		
		modelAndView.setViewName("employee/list");
		return modelAndView;
	}

}