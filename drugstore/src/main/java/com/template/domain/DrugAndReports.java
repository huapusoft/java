package com.template.domain;


/**
 * 药品台账实体
 * @author  army.liu
 * @date 2016年4月12日 下午2:57:51
 */
public class DrugAndReports {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String itemName;//药品名称
	private String spec;//规格
	private String vendor;//厂家
	private double addNum;//贷方
	private double reduceNum;//借方
	private String remark;//摘要
	
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
	public double getAddNum() {
		return addNum;
	}
	public void setAddNum(double addNum) {
		this.addNum = addNum;
	}
	public double getReduceNum() {
		return reduceNum;
	}
	public void setReduceNum(double reduceNum) {
		this.reduceNum = reduceNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
