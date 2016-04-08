package com.template.domain;

import java.util.Date;

/**
 * 药库基础信息、库存表的部分实体
* @author  fengql 
* @date 2016年4月7日 下午2:57:51
 */
public class DrugAndStore {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String itemName;//药品名称
	private String spec;//规格
	private String vendor;//厂家
	private double num;//数量总和--调价时用到
	private String unit;//单位
	private double price;//单价-当前零售价
	
	private int id;//药品id
	private String batchNo;//批号
	private double inPrice;//进价
	private Date validDate;//有效期
	
	//以下为新建盘点时需要
	private int orderNo;//序号
	private double amount;//在库数量
	private double storePrice;//库存金额
	private double realAmount;//实际数量
	private double realPrice;//实际金额
	
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public double getStorePrice() {
		return storePrice;
	}
	public void setStorePrice(double storePrice) {
		this.storePrice = storePrice;
	}
	public double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public double getInPrice() {
		return inPrice;
	}
	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}