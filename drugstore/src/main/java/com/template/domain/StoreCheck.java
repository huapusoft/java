package com.template.domain;

import java.util.Date;
import java.util.List;

/**
 * 药库盘点信息表
* @author  army.liu 
* @date 2016年4月1日 下午2:51:01 
* @version 1.0
 */
public class StoreCheck {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int checkNo;//盘点号
	private String storeName;//药库名称
	private Date checkTime;//时间
	private double inSum;//进价金额
	private double retailSum;//零售价金额
	private double checkRetailSum;//盘点零售价金额
	private String checkOper;//操作员
	private String sealOper;//封存操作员
	private Date sealTime;//封存时间
	
	//临时
	private List<StoreCheckDetail> detailList;//盘点明细
		
	public List<StoreCheckDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<StoreCheckDetail> detailList) {
		this.detailList = detailList;
	}
	public int getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public double getInSum() {
		return inSum;
	}
	public void setInSum(double inSum) {
		this.inSum = inSum;
	}
	public double getRetailSum() {
		return retailSum;
	}
	public void setRetailSum(double retailSum) {
		this.retailSum = retailSum;
	}
	public double getCheckRetailSum() {
		return checkRetailSum;
	}
	public void setCheckRetailSum(double checkRetailSum) {
		this.checkRetailSum = checkRetailSum;
	}
	public String getCheckOper() {
		return checkOper;
	}
	public void setCheckOper(String checkOper) {
		this.checkOper = checkOper;
	}
	public String getSealOper() {
		return sealOper;
	}
	public void setSealOper(String sealOper) {
		this.sealOper = sealOper;
	}
	public Date getSealTime() {
		return sealTime;
	}
	public void setSealTime(Date sealTime) {
		this.sealTime = sealTime;
	}
	
}
