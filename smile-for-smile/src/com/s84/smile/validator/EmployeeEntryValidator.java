package com.s84.smile.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.s84.smile.bean.EmployeeBean;
import com.s84.smile.service.EmployeeService;

@Component
public class EmployeeEntryValidator implements Validator {

	@Autowired
	EmployeeService employeeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		EmployeeBean employeeBean = (EmployeeBean)command;

		//ID
		if ("".equals(employeeBean.getEmployeeId().trim())) {
			errors.rejectValue("employeeId", "error.required.employee.employeeId");
		}
		if ("".equals(employeeBean.getUpEmployeeId()) && !"".equals(employeeBean.getEmployeeId().trim())) {
			//新規登録の時のみ重複チェック
			EmployeeBean bean = null;
			try {
				bean = employeeService.selectByEmployeeId(employeeBean.getEmployeeId());
			} catch (EmptyResultDataAccessException e) {
				bean = null;
			}
			if (bean != null) {
				errors.rejectValue("employeeId", "error.employee.employeeId");
			}
		}
		//パスワード
		if ("".equals(employeeBean.getPassword().trim())) {
			errors.rejectValue("password", "error.required.employee.password");
		}
		//名前
		if ("".equals(employeeBean.getName().trim())) {
			errors.rejectValue("name", "error.required.employee.name");
		}
		
		//支払割合
		if (employeeBean.getShare() == null) {
			errors.rejectValue("share", "error.required.employee.share");
		}
		if (employeeBean.getShare() != null && (employeeBean.getShare() < 0 || employeeBean.getShare() > 100)) {
			errors.rejectValue("share", "error.employee.share");
		}

		//表示順
		if (employeeBean.getSort() != null && (employeeBean.getSort() < 0 || employeeBean.getSort() > 10000)) {
			errors.rejectValue("sort", "error.employee.sort");
		}
	}

}