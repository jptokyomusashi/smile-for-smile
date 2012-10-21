package com.s84.smile.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.bean.MailSettingBean;
import com.s84.smile.bean.ReserveBean;
import com.s84.smile.service.EmployeeService;
import com.s84.smile.service.MailSendService;
import com.s84.smile.service.MailSettingService;
import com.s84.smile.util.DateUtil;

@Controller
public class ReserveController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MailSendService mailSendService;
	@Autowired
	private MailSettingService mailSettingService;
	@Autowired
	private Validator reserveValidator;

	private final String LINE_SEPARATOR = System.getProperty("line.separator");

	@RequestMapping(value="/reserve/entry.html", method = RequestMethod.GET)
	public ModelAndView doInit() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("reserveBean", new ReserveBean());
		modelAndView.setViewName("/reserve/entry");

		createSelectContents(modelAndView);

		return modelAndView;
	}

	@RequestMapping(value="/reserve/doConfirm.html", method = RequestMethod.POST)
	public ModelAndView doConfirm(ReserveBean reserveBean, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		createSelectContents(modelAndView);

		//バリデーション
		this.reserveValidator.validate(reserveBean, bindingResult);
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			modelAndView.setViewName("/reserve/entry");
			return modelAndView;
		}

		modelAndView.setViewName("/reserve/confirm");
		return modelAndView;
	}

	@RequestMapping(value="/reserve/doReserve.html", method = RequestMethod.POST)
	public ModelAndView doReserve(ReserveBean reserveBean) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			//from
			MailSettingBean mailSettingBean = mailSettingService.select();
			InternetAddress[] from = {new InternetAddress(mailSettingBean.getSendAddress(), mailSettingBean.getSendName())};

			//to
			InternetAddress[] to = {new InternetAddress(reserveBean.getMail())};
			//bcc(従業員マスタの管理者)
			List<EmployeeBean> employeeList = employeeService.selectManager();
			InternetAddress[] bcc = new InternetAddress[employeeList.size()];
			for (int i = 0; i < bcc.length; i++) {
				bcc[i] = new InternetAddress(employeeList.get(i).getEmail());
			}
			//cc
			InternetAddress[] cc = null;
			//title
			String title = "ご予約受付メール(タイ古式エステ スマイル)";
			//body
			StringBuffer body = new StringBuffer();
			body.append(reserveBean.getName() + "様" + LINE_SEPARATOR);

			body.append(LINE_SEPARATOR);
			body.append("タイ古式エステスマイルです。" + LINE_SEPARATOR);
			body.append("この度、インターネットでご予約頂き誠にありがとうございます。" + LINE_SEPARATOR);
			body.append("24時間以内に当店予約担当スタッフより返信させて頂きますのでしばらくお待ち下さい。" + LINE_SEPARATOR);
			body.append("なお、当店予約担当スタッフからの返信をもってご予約成立となりますのでご了承下さい。" + LINE_SEPARATOR);

			body.append(LINE_SEPARATOR);
			body.append("◆ご予約内容" + LINE_SEPARATOR);
			body.append("【お名前】" + LINE_SEPARATOR);
			body.append(reserveBean.getName() + "様" + LINE_SEPARATOR);
			body.append("【メールアドレス】" + LINE_SEPARATOR);
			body.append(reserveBean.getMail() + LINE_SEPARATOR);
			body.append("【お電話番号】" + LINE_SEPARATOR);
			body.append(reserveBean.getPhone() + LINE_SEPARATOR);
			body.append("【ご予約人数】" + LINE_SEPARATOR);
			body.append(reserveBean.getAmount() + LINE_SEPARATOR);
			body.append("【ご予約日】" + LINE_SEPARATOR);
			body.append(reserveBean.getDay() + LINE_SEPARATOR);
			body.append("【ご予約時間】" + LINE_SEPARATOR);
			body.append(reserveBean.getTime() + LINE_SEPARATOR);
			body.append("【ご要望】" + LINE_SEPARATOR);
			body.append(reserveBean.getNotes() + LINE_SEPARATOR);

			body.append(LINE_SEPARATOR);
			body.append("---" + LINE_SEPARATOR);
			body.append("タイ古式エステ スマイル");

			//送信
			mailSendService.send(title, body.toString(), from, to, cc, bcc);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		modelAndView.setViewName("/reserve/finish");
		return modelAndView;
	}


	private void createSelectContents(ModelAndView modelAndView) {
		List<Code> amountList = new ArrayList<Code>();
		amountList.add(new Code("1名様", "1名様"));
		amountList.add(new Code("2名様", "2名様"));
		amountList.add(new Code("3名様", "3名様"));
		amountList.add(new Code("4名様", "4名様"));
		amountList.add(new Code("5名様以上", "5名様以上(ご要望欄にご記入下さい)"));
		modelAndView.addObject("amount", amountList);

		List<Code> timeList = new ArrayList<Code>();
		timeList.add(new Code("13:00", "13:00"));
		timeList.add(new Code("13:30", "13:30"));
		timeList.add(new Code("14:00", "14:00"));
		timeList.add(new Code("14:30", "14:30"));
		timeList.add(new Code("15:00", "15:00"));
		timeList.add(new Code("15:30", "15:30"));
		timeList.add(new Code("16:00", "16:00"));
		timeList.add(new Code("16:30", "16:30"));
		timeList.add(new Code("17:00", "17:00"));
		timeList.add(new Code("17:30", "17:30"));
		timeList.add(new Code("18:00", "18:00"));
		timeList.add(new Code("18:30", "18:30"));
		timeList.add(new Code("19:00", "19:00"));
		timeList.add(new Code("19:30", "19:30"));
		timeList.add(new Code("20:00", "20:00"));
		timeList.add(new Code("20:30", "20:30"));
		timeList.add(new Code("21:00", "21:00"));
		timeList.add(new Code("21:30", "21:30"));
		timeList.add(new Code("22:00", "22:00"));
		timeList.add(new Code("22:30", "22:30"));
		timeList.add(new Code("23:00", "23:00"));
		timeList.add(new Code("23:30", "23:30"));
		timeList.add(new Code("24:00", "24:00"));
		timeList.add(new Code("24:30", "24:30"));
		timeList.add(new Code("25:00", "25:00"));
		modelAndView.addObject("time", timeList);

		int daySize = 5;
		List<Code> dayList = new ArrayList<Code>();
		Date date[] = new Date[daySize];
		for (int i = 0; i < daySize; i++) {
			date[i] = DateUtil.getDay((i + 1) * 60 * 24);
			
			String day = DateUtil.getDateFormat().format(date[i]);
			dayList.add(new Code(day, day));
		}
		modelAndView.addObject("day", dayList);

	}

	class Code {
		Code(String value, String label) {
			this.value = value;
			this.label = label;
		}
		String value;
		String label;
		
		public String getValue() {
			return value;
		}
		public String getLabel() {
			return label;
		}
	}
}
