package com.template.domain;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * 药库出入库信息明细表storeInOutDetail的实体类
* @author  fengql 
* @date 2016年4月1日 下午2:51:01 
* @version 1.0
 */
public class StoreInOutDetail {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int id;//id,自增长
	
	private int billNo;//票据号
	
	@Min(value=0,message="最小值为0")
	private int orderNo;//顺序号
	
	@Min(value=1,message="最小值为1")
	private int drugId;//药品标识
	
	@Size(max=16,message="长度最大为16个字符")
	private String invoiceNo;//发票号,与billType的值相关,当billType为入库时，字段值为发票号。billType取其它值时为空。
	
	@Size(max=32,message="长度最大为32个字符")
	private String batchNo;//批号
	
	@Digits(fraction = 1, integer = 10)
	private double amount;//数量
	
	@Digits(fraction = 3, integer = 10)
	private double price1;//价格1,与billType的值相关，billType为调价，字段值为现零售价。billType为其它值时，字段值为进价
	
	@Digits(fraction = 3, integer = 10)
	private double price2;//价格2，与billType的值相关，billType为调价，字段值为新零售价。billType为其它值时，字段值为零售价
	
	private Date validDate;//有效期
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
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
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	public double getPrice1() {
		return price1;
	}
	public void setPrice1(double price1) {
		this.price1 = price1;
	}
	public double getPrice2() {
		return price2;
	}
	public void setPrice2(double price2) {
		this.price2 = price2;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
}
