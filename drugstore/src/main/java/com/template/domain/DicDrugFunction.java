package com.template.domain;

/**
 * 功能代码实体
 * @Description: 功能代码实体
 * @author army.liu
 */
public class DicDrugFunction {

	private int id;//唯一，自增长
	private String functionCode;//功能代码
	private String functionName;//功能名称
	private String wb;//五笔码
	private String py;//拼音码
	private String parentCode;//父功能代码
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getWb() {
		return wb;
	}
	public void setWb(String wb) {
		this.wb = wb;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
}
