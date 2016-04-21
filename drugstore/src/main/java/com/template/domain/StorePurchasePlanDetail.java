package com.template.domain;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 采购计划明细表实体
* @author  fengql 
* @date 2016年4月11日 上午9:17:46
 */
public class StorePurchasePlanDetail {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int id;//唯一，自增长
	private int purchaseNo;//采购号
	
	@NotNull(message="不能为空")
	private int orderNo;//顺序
	
	@NotNull(message="不能为空")
	@Size(max=64,message="长度最大为32个汉字")
	private String provider;//供应商
	
	@NotNull(message="不能为空")
	private Date arriveTime;//预计到货日期
	
	@NotNull(message="不能为空")
	private int drugId;//药品id
	
	@Digits(fraction = 2, integer = 8,message="格式错误，应为：最多包含2位小数的10位数值")
	private double amount;//数量
	
	@Digits(fraction = 3, integer = 7,message="格式错误，应为：最多包含3位小数的10位数值")
	private double inPrice;//进价
	
	@Digits(fraction = 3, integer = 7,message="格式错误，应为：最多包含3位小数的10位数值")
	private double price;//零售价
	
	@Size(max=64,message="长度最大为32个汉字")
	private String remark;//说明
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getInPrice() {
		return inPrice;
	}
	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
