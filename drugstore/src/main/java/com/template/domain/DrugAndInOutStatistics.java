package com.template.domain;

/**
 * 进出统计实体
* @author  fengql 
* @date 2016年4月12日 下午5:14:59
 */
public class DrugAndInOutStatistics {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int drugId;//药品id
    private String batchNo;//批号
    private String itemName;//药品名称
    private String spec;//规格
    private String vendor;//生产商
    private String unit;//单位

    private double inSum;//入库数
    private double outSum;//出库数
    private double returnSum;//退货数
    private double breakageSum;//报损数
    private double firstSum;//期初数
    private double storeSum;//在库数
    
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getInSum() {
		return inSum;
	}
	public void setInSum(double inSum) {
		this.inSum = inSum;
	}
	public double getOutSum() {
		return outSum;
	}
	public void setOutSum(double outSum) {
		this.outSum = outSum;
	}
	public double getReturnSum() {
		return returnSum;
	}
	public void setReturnSum(double returnSum) {
		this.returnSum = returnSum;
	}
	public double getBreakageSum() {
		return breakageSum;
	}
	public void setBreakageSum(double breakageSum) {
		this.breakageSum = breakageSum;
	}
	public double getFirstSum() {
		return firstSum;
	}
	public void setFirstSum(double firstSum) {
		this.firstSum = firstSum;
	}
	public double getStoreSum() {
		return storeSum;
	}
	public void setStoreSum(double storeSum) {
		this.storeSum = storeSum;
	}

}
