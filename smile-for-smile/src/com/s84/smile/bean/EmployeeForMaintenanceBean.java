package com.s84.smile.bean;

public class EmployeeForMaintenanceBean extends EmployeeBean {

	private String resignedName;
	private String authorityName;

	public String getResignedName() {
		return resignedName;
	}

	public void setResignedName(String resignedName) {
		this.resignedName = resignedName;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
}