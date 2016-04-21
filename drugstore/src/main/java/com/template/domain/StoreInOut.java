package com.template.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 药库出入库实体
 * @Description: 药库出入库实体
 * @author army.liu
 */
public class StoreInOut {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int billNo;//票据号
	
	private String storeName;//药库名称
	
	@NotNull(message="不能为空")
	@Size(max=2,message="长度最大为2个汉字")
	private String billType;//单据类型，可用值为：（入库、出库、退货、报损、调价）
	
	@NotNull(message="不能为空")
	@Size(max=32,message="长度最大为32个汉字")
	private String typeData;//与单据类型相关的数据。入库：供应商名称 出库：领药部门名称 退货：供应商名称 报损：报损原因 调价：调价批文
	
	private Date billTime;//插入时间
	
	@Digits(fraction = 3, integer = 10)
	private double sum1;//金额1，字段的值与billType相关 入库：进价金额 出库：进价金额 退货：进价金额 报损：进价金额 调价：现零售价金额
	
	@Digits(fraction = 3, integer = 10)
	private double sum2;//金额2，字段的值与billType相关 入库：零售价金额 出库：零售价金额 退货：零售价金额 报损：零售价金额 调价：新零售价金额
	
	private String billOper;//操作员
	
	private String status;//状态：默认为0草稿，1已提交，2复核通过，3复核驳回
	
	private Date submitTime;//提交时间
	
	private String verifyOper;//复核操作员，默认为空
	
	private Date verifyTime;//复核时间
	
	//临时
	@Valid
	private List<StoreInOutDetail> detailList;
	
	private List<DrugAndStoreInOutDetail> detailAndDrugList;
	
	public List<StoreInOutDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<StoreInOutDetail> detailList) {
		this.detailList = detailList;
	}
	public List<DrugAndStoreInOutDetail> getDetailAndDrugList() {
		return detailAndDrugList;
	}
	public void setDetailAndDrugList(List<DrugAndStoreInOutDetail> detailAndDrugList) {
		this.detailAndDrugList = detailAndDrugList;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getTypeData() {
		return typeData;
	}
	public void setTypeData(String typeData) {
		this.typeData = typeData;
	}
	public Date getBillTime() {
		return billTime;
	}
	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}
	public double getSum1() {
		return sum1;
	}
	public void setSum1(double sum1) {
		this.sum1 = sum1;
	}
	public double getSum2() {
		return sum2;
	}
	public void setSum2(double sum2) {
		this.sum2 = sum2;
	}
	public String getBillOper() {
		return billOper;
	}
	public void setBillOper(String billOper) {
		this.billOper = billOper;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getVerifyOper() {
		return verifyOper;
	}
	public void setVerifyOper(String verifyOper) {
		this.verifyOper = verifyOper;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	
}
