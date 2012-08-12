package com.s84.smile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.service.EmployeeService;

@Controller
public class LoginController {

	@Autowired
	private Validator loginValidator;
	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public EmployeeBean setUpForm() {
		return new EmployeeBean();
	}

	//@RequestMapping(value="/login.html", method = RequestMethod.GET)
	@RequestMapping(method = RequestMethod.GET)
	public String toLoginView() {
		return "login/login";
	}

	//@RequestMapping(value="/login/doLogin.html", method = RequestMethod.POST)
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(EmployeeBean employeeBean, BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		this.loginValidator.validate(employeeBean, bindingResult);
		
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		
		try {
			EmployeeBean loginEmployee = this.employeeService.selectByEmployeeIdAndPassword(employeeBean.getEmployeeId(), employeeBean.getPassword());
			modelAndView.setViewName("/top");
			session.setAttribute("loginEmployee", loginEmployee);
			return modelAndView;

		} catch (EmptyResultDataAccessException e) {
			bindingResult.reject("error.login");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
			
		}
	}

}
