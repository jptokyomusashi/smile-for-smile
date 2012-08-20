package com.s84.smile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailmagazineController {

	@RequestMapping(value="/mailmagazine/entry.html", method = RequestMethod.GET)
	public ModelAndView onInit(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/mailmagazine/entry");
		return modelAndView;
	}

	@RequestMapping(value="/mailmagazine/doEntry.html", method = RequestMethod.POST)
	public ModelAndView onSubmit(BindingResult bindingResult, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/top");
		return modelAndView;
	}
}
