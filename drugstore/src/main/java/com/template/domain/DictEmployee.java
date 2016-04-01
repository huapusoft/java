package com.template.domain;

/**
 * 员工实体
 */
public class DictEmployee {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String jobNumber;
	private String name;
	private String age;
	private String sex;
	private String office;
	private String role;
	private String roleInfo;
	private String password;
	private String highLevel;
	
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHighLevel() {
		return highLevel;
	}
	public void setHighLevel(String highLevel) {
		this.highLevel = highLevel;
	}
	
}
