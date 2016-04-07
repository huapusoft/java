package com.template.domain;

/**
 * 采购计划统计实体
 * @Description: 采购计划统计实体
 * @author army.liu
 */
public class StorePurchasePlanForCount {
	
	private String status;//状态：默认为0草稿，1已提交，2复核通过，3复核驳回
	private int total;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
}
