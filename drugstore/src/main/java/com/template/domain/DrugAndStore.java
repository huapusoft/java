package com.template.domain;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

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
	
	private int drugId;//药品id
	private String batchNo;//批号
	private double inPrice;//进价
	private Date validDate;//有效期
	private String place1;//摆放位置
	
	//以下为新建盘点时需要
	private int orderNo;//序号
	private double amount;//在库数量
	private double storePrice;//库存金额
	private double realAmount;//实际数量
	private double realPrice;//实际金额
	
	//以下为药品设置时需要
//	storeIds--支持多个
//	  * 			drugFunction
//	  * 			storeClass
//	  * 			place1
//	  * 			customOrderCode
//	  * 			amountLowLimit
//	  * 			validDateWarnDays
	private String storeIds;
	
	@Size(max=16,message="长度最大为8个汉字")
	private String expenseClass;
	
	@Size(max=16,message="长度最大为8个汉字")
	private String storeClass;
	
	private String drugFrom;
	
	@Size(max=16,message="长度最大为8个汉字")
	private String drugFunction;
	
	@Digits(fraction = 0, integer = 10)
	private int amountLowLimit;//库存下限
	
	@Digits(fraction = 0, integer = 10)
	private int validDateWarnDays;//有效期报警天数
	
	@Digits(fraction = 0, integer = 10)
	private int customOrderCode;//自定义顺序码
	
	//以下为采购计划用到
	private double inPriceNew;//最近一次进价
	private double priceNew;//最近一次零售价
	
	public double getInPriceNew() {
		return inPriceNew;
	}
	public void setInPriceNew(double inPriceNew) {
		this.inPriceNew = inPriceNew;
	}
	public double getPriceNew() {
		return priceNew;
	}
	public void setPriceNew(double priceNew) {
		this.priceNew = priceNew;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getStoreClass() {
		return storeClass;
	}
	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
	}
	public String getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(String storeIds) {
		this.storeIds = storeIds;
	}
	public String getExpenseClass() {
		return expenseClass;
	}
	public void setExpenseClass(String expenseClass) {
		this.expenseClass = expenseClass;
	}
	public String getDrugFrom() {
		return drugFrom;
	}
	public void setDrugFrom(String drugFrom) {
		this.drugFrom = drugFrom;
	}
	public String getDrugFunction() {
		return drugFunction;
	}
	public void setDrugFunction(String drugFunction) {
		this.drugFunction = drugFunction;
	}
	public int getAmountLowLimit() {
		return amountLowLimit;
	}
	public void setAmountLowLimit(int amountLowLimit) {
		this.amountLowLimit = amountLowLimit;
	}
	public int getValidDateWarnDays() {
		return validDateWarnDays;
	}
	public void setValidDateWarnDays(int validDateWarnDays) {
		this.validDateWarnDays = validDateWarnDays;
	}
	public int getCustomOrderCode() {
		return customOrderCode;
	}
	public void setCustomOrderCode(int customOrderCode) {
		this.customOrderCode = customOrderCode;
	}
	public String getPlace1() {
		return place1;
	}
	public void setPlace1(String place1) {
		this.place1 = place1;
	}
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
