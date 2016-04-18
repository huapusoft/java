package com.template.domain;

import java.util.List;

/**
 * 配伍主表实体
* @author  fengql 
* @date 2016年4月14日 上午9:39:26
 */
public class DicCompatibility {

	private int id;//唯一，自增长
	private int comId;//配伍关联id
	private String contentA;//A类内容
	private String contentB;//B类内容
	private String result;//配伍结果
	
	private List<DicCompatibilityDetail> detailList;//配伍明细
	
	public List<DicCompatibilityDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<DicCompatibilityDetail> detailList) {
		this.detailList = detailList;
	}
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
	public String getContentA() {
		return contentA;
	}
	public void setContentA(String contentA) {
		this.contentA = contentA;
	}
	public String getContentB() {
		return contentB;
	}
	public void setContentB(String contentB) {
		this.contentB = contentB;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
