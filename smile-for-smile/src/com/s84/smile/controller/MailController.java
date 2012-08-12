package com.s84.smile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.PaymentSlipSearchConditionBean;

@Controller
public class MailController {

	@RequestMapping(value="/mail/setting.html", method = RequestMethod.POST)
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(new PaymentSlipSearchConditionBean());
		modelAndView.setViewName("mail/setting");
		return modelAndView;
	}

}
