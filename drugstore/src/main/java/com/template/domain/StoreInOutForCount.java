package com.template.domain;

/**
 * 药库出入库统计实体
 * @Description: 药库出入库统计实体
 * @author army.liu
 */
public class StoreInOutForCount {
	
	private String billType;//状态：默认为0草稿，1已提交，2复核通过，3复核驳回
	private int sum0;//草稿个数
	private int sum1;//已提交个数
	private int sum2;//复核通过个数
	private int sum3;//复核驳回个数
	
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public int getSum0() {
		return sum0;
	}
	public void setSum0(int sum0) {
		this.sum0 = sum0;
	}
	public int getSum1() {
		return sum1;
	}
	public void setSum1(int sum1) {
		this.sum1 = sum1;
	}
	public int getSum2() {
		return sum2;
	}
	public void setSum2(int sum2) {
		this.sum2 = sum2;
	}
	public int getSum3() {
		return sum3;
	}
	public void setSum3(int sum3) {
		this.sum3 = sum3;
	}
	
}
