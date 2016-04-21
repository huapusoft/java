package com.template.domain;

import javax.validation.constraints.NotNull;

/**
 * 部门实体
 * @Description: 部门实体
 * @author army.liu
 */
public class DicDepartment {

	private int departmentId;//部门号，自增长
	private String departmentCode;//部门code
	
	@NotNull(message="不能为空")
	private String departmentName;//部门名称
	
	private String parentCode;//上级部门的部门code
	private String parentName;//上级部门的部门名称
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
