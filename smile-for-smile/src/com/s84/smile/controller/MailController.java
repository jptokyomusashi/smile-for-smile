package com.s84.smile.controller;

import java.util.List;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.MailCustomerBean;
import com.s84.smile.bean.MailSendBean;
import com.s84.smile.bean.MailSettingBean;
import com.s84.smile.service.MailCustomerService;
import com.s84.smile.service.MailSendService;
import com.s84.smile.service.MailSettingService;
import com.s84.smile.validator.MailSendValidator;

@Controller
public class MailController {

	@Autowired
	private MailCustomerService mailCustomerService;
	@Autowired
	private MailSettingService mailSettingService;
	@Autowired
	private MailSendService mailSendService;
	@Autowired
	private MailSendValidator mailSendValidator;

	@RequestMapping(value="/mail/mail.html", method = RequestMethod.POST)
	public ModelAndView mail() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mailSendBean", new MailSendBean());
		modelAndView.setViewName("mail/mail");
		return modelAndView;
	}

	@RequestMapping(value="/mail/send.html", method = RequestMethod.POST)
	public ModelAndView send(MailSendBean mailSendBean, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		//バリデーション
		this.mailSendValidator.validate(mailSendBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("mail/mail");
			return modelAndView;
		}

		//メール送信
		try {
			//from
			MailSettingBean mailSettingBean = mailSettingService.select();
			InternetAddress[] from = {new InternetAddress(mailSettingBean.getSendAddress(), mailSettingBean.getSendName())};
			//to
			InternetAddress[] to = from;
			//bcc
			List<MailCustomerBean> mailCustomerList = mailCustomerService.selectNotDeleted();
			InternetAddress[] bcc = new InternetAddress[mailCustomerList.size()];
			for (int i = 0; i < mailCustomerList.size(); i++) {
				bcc[i] = new InternetAddress(mailCustomerList.get(i).getMailAddress());
			}
			//cc
			InternetAddress[] cc = null;
			//送信
			mailSendService.send(mailSendBean.getTitle(), mailSendBean.getBody(), from, to, cc, bcc);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		modelAndView.setViewName("mail/mail");
		return modelAndView;
	}

	@RequestMapping(value="/mail/setting.html", method = RequestMethod.POST)
	public ModelAndView setting() {
		MailSettingBean mailSettingBean = null;

		try {
			mailSettingBean = mailSettingService.select();
		} catch (EmptyResultDataAccessException e) {
			mailSettingBean = new MailSettingBean();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mailSettingBean", mailSettingBean);
		modelAndView.setViewName("mail/setting");
		return modelAndView;
	}

	@RequestMapping(value="/mail/doSetting.html", method = RequestMethod.POST)
	public ModelAndView doSetting(MailSettingBean mailSettingBean) {
		ModelAndView modelAndView = new ModelAndView();
		
		mailSettingService.delete();
		mailSettingService.insert(mailSettingBean);
		modelAndView.addObject("MESSAGE", "登録完了しました。");
		modelAndView.setViewName("mail/setting");
		return modelAndView;
	}

	@RequestMapping(value="/mail/list.html", method = RequestMethod.POST)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mailCustomerList", mailCustomerService.selectAll());
		modelAndView.addObject("size", mailCustomerService.selectAll().size());
		modelAndView.setViewName("mail/list");
		return modelAndView;
	}

	@RequestMapping(value="/mail/toEntry.html", method = RequestMethod.POST)
	public ModelAndView toEntry(int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mailCustomerBean", mailCustomerService.selectByPrimarykey(id));
		modelAndView.setViewName("mail/entry");
		return modelAndView;
	}

	@RequestMapping(value="/mail/doEntry.html", method = RequestMethod.POST)
	public ModelAndView doEntry(MailCustomerBean mailCustomerBean) {
		ModelAndView modelAndView = new ModelAndView();
		
		mailCustomerService.update(mailCustomerBean);

		modelAndView.addObject("mailCustomerList", mailCustomerService.selectAll());
		modelAndView.addObject("size", mailCustomerService.selectAll().size());
		modelAndView.setViewName("mail/list");
		return modelAndView;
	}
}