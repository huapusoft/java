package com.template.domain;

import java.util.Date;

/**
 * 药库库存信息表
* @author  army.liu 
* @date 2016年4月1日 下午2:51:01 
* @version 1.0
 */
public class Store {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int id;//主键
	private String storeName;//药库名称
	private int drugId;//药品标识
	private String batchNo;//批号
	private double inPrice;//进价
	private double price;//零售价
	private double amount;//数量
	private int amountLowLimit;//库存下限
	private Date validDate;//有效期
	private int validDateWarnDays;//有效期报警天数
	private int special;//特殊药品，默认为0，否则为1
	private int customOrderCode;//自定义顺序码
	private Date inTime;//入库时间
	private String place1;//摆放位置1
	private String place2;//摆放位置2
	private String place3;//摆放位置3
	
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	public int getAmountLowLimit() {
		return amountLowLimit;
	}
	public void setAmountLowLimit(int amountLowLimit) {
		this.amountLowLimit = amountLowLimit;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public int getValidDateWarnDays() {
		return validDateWarnDays;
	}
	public void setValidDateWarnDays(int validDateWarnDays) {
		this.validDateWarnDays = validDateWarnDays;
	}
	public int getSpecial() {
		return special;
	}
	public void setSpecial(int special) {
		this.special = special;
	}
	public int getCustomOrderCode() {
		return customOrderCode;
	}
	public void setCustomOrderCode(int customOrderCode) {
		this.customOrderCode = customOrderCode;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public String getPlace1() {
		return place1;
	}
	public void setPlace1(String place1) {
		this.place1 = place1;
	}
	public String getPlace2() {
		return place2;
	}
	public void setPlace2(String place2) {
		this.place2 = place2;
	}
	public String getPlace3() {
		return place3;
	}
	public void setPlace3(String place3) {
		this.place3 = place3;
	}
	
	
}
