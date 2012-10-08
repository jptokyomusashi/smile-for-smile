package com.s84.smile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.MailCustomerBean;
import com.s84.smile.service.MailCustomerService;

@Controller
public class MailmagazineController {

	@Autowired
	MailCustomerService mailCustomerService;
	
	@RequestMapping(value="/mailmagazine/entry.html", method = RequestMethod.GET)
	public ModelAndView onInit(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mailCustomerBean", new MailCustomerBean());
		modelAndView.setViewName("/mailmagazine/entry");
		return modelAndView;
	}

	@RequestMapping(value="/mailmagazine/doEntry.html", method = RequestMethod.POST)
	public ModelAndView doEntry(MailCustomerBean mailCustomerBean) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/mailmagazine/entry");
		
		String check = "^[_A-Za-z0-9-\\+\\*\\.]+(\\.[_A-Za-z0-9-\\+\\*\\.]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
		String mailAddress = mailCustomerBean.getMailAddress().trim();

		if (!"".equals(mailAddress)) {
			//バリデーション
			if (!mailAddress.matches(check)) {
				modelAndView.addObject("MESSAGE", "メールアドレスの形式ではありません。");
				return modelAndView;
			}

			List<MailCustomerBean> mailCustomerBeanList = mailCustomerService.selectByMailAddress(mailAddress);
			if (mailCustomerBeanList.size() > 0) {
				//重複
				modelAndView.addObject("MESSAGE", "既に登録されています。");
				return modelAndView;
			}

			//登録
			mailCustomerBean.setMailAddress(mailAddress);
			mailCustomerService.insert(mailCustomerBean);
			modelAndView.addObject("MESSAGE", "登録が完了しました。");
		}

		return modelAndView;
	}

	@RequestMapping(value="/mailmagazine/doDelete.html", method = RequestMethod.POST)
	public ModelAndView doDelete(MailCustomerBean mailCustomerBean) {
		ModelAndView modelAndView = new ModelAndView();
		
		String mailAddress = mailCustomerBean.getMailAddressForDelete().trim();

		if (!"".equals(mailAddress)) {
			List<MailCustomerBean> list = mailCustomerService.selectByMailAddress(mailAddress);
			
			for (MailCustomerBean bean : list) {
				bean.setDeleted(1);
				mailCustomerService.update(bean);
			}

			modelAndView.addObject("MESSAGE", "解除しました。");
		}

		modelAndView.setViewName("/mailmagazine/entry");
		return modelAndView;
	}

}