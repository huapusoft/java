package com.template.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 配伍明细表实体
* @author  fengql 
* @date 2016年4月14日 上午9:39:26
 */
public class DicCompatibilityDetail {

	private int id;//唯一，自增长
	private int comId;//配伍主表id
	
	@NotNull(message="不能为空")
	private int comTypeA;//A类：0功能代码,1药类id
	
	@Size(max=32,message="长度最大为32个汉字")
	private String functionCodeA;//A类功能代码
	
	private int drugClassIdA;//A类药类id
	
	@NotNull(message="不能为空")
	private int comTypeB;//B类：0功能代码,1药类id
	
	@Size(max=32,message="长度最大为32个汉字")
	private String functionCodeB;//B类功能代码
	
	private int drugClassIdB;//B类药类id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public int getComTypeA() {
		return comTypeA;
	}
	public void setComTypeA(int comTypeA) {
		this.comTypeA = comTypeA;
	}
	public String getFunctionCodeA() {
		return functionCodeA;
	}
	public void setFunctionCodeA(String functionCodeA) {
		this.functionCodeA = functionCodeA;
	}
	public int getDrugClassIdA() {
		return drugClassIdA;
	}
	public void setDrugClassIdA(int drugClassIdA) {
		this.drugClassIdA = drugClassIdA;
	}
	public int getComTypeB() {
		return comTypeB;
	}
	public void setComTypeB(int comTypeB) {
		this.comTypeB = comTypeB;
	}
	public String getFunctionCodeB() {
		return functionCodeB;
	}
	public void setFunctionCodeB(String functionCodeB) {
		this.functionCodeB = functionCodeB;
	}
	public int getDrugClassIdB() {
		return drugClassIdB;
	}
	public void setDrugClassIdB(int drugClassIdB) {
		this.drugClassIdB = drugClassIdB;
	}
	
}
