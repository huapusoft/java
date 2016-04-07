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
    private int checkno;//盘点号
    private int orderno;//顺序号
    private int drugid;//药品id
    private String batchno;//批号
    private double amount;//数量
    private double realamount;//实际数量
    private double inprice;//进价
    private double price;//零售价
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCheckno() {
		return checkno;
	}
	public void setCheckno(int checkno) {
		this.checkno = checkno;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getDrugid() {
		return drugid;
	}
	public void setDrugid(int drugid) {
		this.drugid = drugid;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRealamount() {
		return realamount;
	}
	public void setRealamount(double realamount) {
		this.realamount = realamount;
	}
	public double getInprice() {
		return inprice;
	}
	public void setInprice(double inprice) {
		this.inprice = inprice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}