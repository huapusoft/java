package com.template.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    
    @NotNull(message="不能为空")
    private int orderNo;//顺序号
    
    @NotNull(message="不能为空")
    private int drugId;//药品id
    
    @NotNull(message="不能为空")
    @Size(max=32,message="长度最大为32个字符")
    private String batchNo;//批号
    
    @Digits(fraction = 2, integer = 8,message="格式错误，应为：最多包含2位小数的10位数值")
    private double amount;//数量
    
    @Digits(fraction = 2, integer = 8,message="格式错误，应为：最多包含2位小数的10位数值")
    private double realAmount;//实际数量
    
    @Digits(fraction = 3, integer = 7,message="格式错误，应为：最多包含3位小数的10位数值")
    private double inPrice;//进价
    
    @Digits(fraction = 3, integer = 7,message="格式错误，应为：最多包含3位小数的10位数值")
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