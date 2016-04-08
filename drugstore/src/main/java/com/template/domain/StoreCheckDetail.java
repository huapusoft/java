package com.template.domain;

/**
 * 药库盘点明细表实体
* @author  fengql 
* @date 2016年4月7日 下午4:09:36
 */
public class StoreCheckDetail {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
    private int id;//唯一，自增长
    private int checkNo;//盘点号
    private int orderNo;//顺序号
    private int drugId;//药品id
    private String batchNo;//批号
    private double amount;//数量
    private double realAmount;//实际数量
    private double inPrice;//进价
    private double price;//零售价
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
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
    
	


}