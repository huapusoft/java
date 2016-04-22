package com.template.domain;

/**
 * 药品基础信息表、盘点明细表部分实体
* @author  fengql 
* @date 2016年4月8日 上午9:17:59
 */
public class DrugAndCheckDetail {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int orderNo;//序号
	private int checkNo;//盘点号
	private int id;//药品id
	private String itemName;//药品名称
	private String spec;//规格
	private String batchNo;//批号
	private double price;//单价--当前零售价
	private double amount;//库存数量
	private String unit;//单位
	private double storePrice;//库存金额
	private double realAmount;//实际数量
	private double realPrice;//实际金额
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	
	
}