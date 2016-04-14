package com.template.domain;

/**
 * 药品类别实体
* @author  fengql 
* @date 2016年4月14日 上午9:21:56
 */
public class DicDrugClass {

	private int id;//唯一，自增长
	private String name;//药类名称
	private String functionCode;//功能代码
	private String functionName;//功能名称
	private String wb;//五笔码
	private String py;//拼音码
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
