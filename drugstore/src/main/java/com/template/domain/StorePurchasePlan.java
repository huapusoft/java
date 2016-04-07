package com.template.domain;

import java.util.Date;

/**
 * 采购计划信息表
* @author  army.liu 
* @date 2016年4月1日 下午2:51:01 
* @version 1.0
 */
public class StorePurchasePlan {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int purchaseNo;//采购号
	private String storeName;//药库名称
	private Date purchaseTime;//制定时间
	private String remark;//计划说明
	private double inSum;//进价金额
	private double retailSum;//零售价金额
	private String status;//状态，默认为0草稿，1已提交，2财务驳回，3财务已审批，4领导驳回，5领导已审批
	private String oper;//操作员
	private Date submitTime;//提交时间
	private String financeOper;//财务操作员
	private Date financeTime;//财务审批时间
	private String leader;//领导
	private Date leaderTime;//领导审批时间
	
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getFinanceOper() {
		return financeOper;
	}
	public void setFinanceOper(String financeOper) {
		this.financeOper = financeOper;
	}
	public Date getFinanceTime() {
		return financeTime;
	}
	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public Date getLeaderTime() {
		return leaderTime;
	}
	public void setLeaderTime(Date leaderTime) {
		this.leaderTime = leaderTime;
	}
	
}