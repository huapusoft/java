package com.template.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;

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
	
	@Digits(fraction = 3, integer = 9,message="格式错误，应为：最多包含3位小数的12位数值")
	private double inSum;//进价金额
	
	@Digits(fraction = 3, integer = 9,message="格式错误，应为：最多包含3位小数的12位数值")
	private double retailSum;//零售价金额
	
	@Digits(fraction = 3, integer = 9,message="格式错误，应为：最多包含3位小数的12位数值")
	private double checkRetailSum;//盘点零售价金额
	
	private String checkOper;//操作员
	private String sealOper;//封存操作员
	private Date sealTime;//封存时间
	
	private int status;//状态
	private String statusName;//状态说明
	
	//临时
	private List<StoreCheckDetail> detailList;//盘点明细
	
	private List<DrugAndCheckDetail> detailAndDrugList;
		
	public List<StoreCheckDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<StoreCheckDetail> detailList) {
		this.detailList = detailList;
	}
	public List<DrugAndCheckDetail> getDetailAndDrugList() {
		return detailAndDrugList;
	}
	public void setDetailAndDrugList(List<DrugAndCheckDetail> detailAndDrugList) {
		this.detailAndDrugList = detailAndDrugList;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
