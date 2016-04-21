package com.template.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 供应商实体
 * @Description: 供应商实体
 * @author army.liu
 */
public class DicProvider {

	private int id;//供应商标识（唯一）
	
	@NotNull(message="不能为空")
	@Size(max=32,message="长度最大为32个汉字")
	private String providerName;//供应商名称
	
	@NotNull(message="不能为空")
	@Size(max=64,message="长度最大为64个汉字")
	private String addr;//地址
	
	@NotNull(message="不能为空")
	@Pattern(regexp="1[3|4|5|8][0-9]{9}",message="格式不对")
	private String phone;//电话
	
	@NotNull(message="不能为空")
	private String linkman;//联系人
	
	@NotNull(message="不能为空")
	private String finance;//财务姓名
	
	@NotNull(message="不能为空")
	@Size(max=64,message="长度最大为64个汉字")
	private String bank;//开户行及电话
	
	private String wb;//名称五笔码
	private String py;//名称拼音码
	private int enabled;//是否启用（默认1：启用，0：禁用）
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getFinance() {
		return finance;
	}
	public void setFinance(String finance) {
		this.finance = finance;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getWb() {
		return wb;
	}
	public void setWb(String wb) {
		this.wb = wb;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
}
