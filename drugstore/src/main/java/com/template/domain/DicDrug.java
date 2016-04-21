package com.template.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 药品基础信息实体
 * @Description: 药品基础信息实体
 * @author army.liu
 */
public class DicDrug {

	private int id;//标识（唯一）

	@Size(max=16,message="长度最大为16个字符")
    private String expenseClass;//收费类别（西药、中成药、中草药）

	@Size(max=16,message="长度最大为16个字符")
    private String storeClass;//库存类别

    @NotNull(message="不能为空")
    @Size(max=64,message="长度最大为32个汉字")
    private String itemName;//名称

    @NotNull(message="不能为空")
    @Size(max=32,message="长度最大为32个字符")
    private String spec;//规格

    @NotNull(message="不能为空")
    @Size(max=32,message="长度最大为32个字符")
    private String vendor;//生产商

    @NotNull(message="不能为空")
    @Size(max=16,message="长度最大为16个字符")
    private String unit;//单位

    @Size(max=16,message="长度最大为16个字符")
    private String clinicUnit;//门诊零售单位

    private int clinicUnitRatio;//门诊零售单位比率

    @Size(max=16,message="长度最大为16个字符")
    private String inhosUnit;//住院零售单位

    private int inhosUnitRatio;//住院零售单位比率

    @Size(max=16,message="长度最大为16个字符")
    private String adviceUnit;//医嘱单位

    @Size(max=16,message="长度最大为16个字符")
    private String adviceUnitValue;//医嘱单位数值

    @Size(max=16,message="长度最大为16个字符")
    private String adviceUnitValueUnit;//医嘱单位数值单位

    private int adviceUnitClinicRatio;//医嘱单位门诊比率

    private int adviceUnitInhosRatio;//	医嘱单位住院比率

    @Size(max=64,message="长度最大为64个字符")
    private String wb;//五笔码

    @Size(max=64,message="长度最大为64个字符")
    private String py;//拼音码

    @Size(max=16,message="长度最大为16个字符")
    private String drugFunction;//药品功能代码

    @Size(max=16,message="长度最大为16个字符")
    private String drugClass;//药品类别

    @Size(max=16,message="长度最大为16个字符")
    private String drugFrom;//药品剂型

    private int compositeItem;//是否为复合项目

    private int subItemSelect;//是否有自选子项目

    private int partSelect;//是否自选部位（专指检查项目）

    private int changePrice;//是否可以更改价格

    private int showInAdvice;//是否在医嘱中显示

    private int enabled;//是否启用

    @Size(max=32,message="长度最大为32个字符")
    private String hzylCode;//合作医疗对应码

    private int hzylVerify;//合作医疗审批标志

    private int hzylReimburse;//合作医疗是否报销

    @Size(max=32,message="长度最大为32个字符")
    private String ybCode;//医疗保险对应码

    private double ybClinicSelfRatio;//医疗保险门诊自理比例

    private double ybInhosSelfRatio;//医疗保险住院自理比例
    
    @Digits(fraction = 3, integer = 10)
    private double inPrice;//进价
    
    @Digits(fraction = 3, integer = 10)
    private double price;//零售价
    
    @Digits(fraction = 3, integer = 10)
    private double clinicInPrice;//门诊进价
    
    @Digits(fraction = 3, integer = 10)
    private double clinicPrice;//门诊零售价
    
    @Digits(fraction = 3, integer = 10)
    private double inhosInPrice;//住院进价
    
    @Digits(fraction = 3, integer = 10)
    private double inhosPrice;//住院零售价
    

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

	public double getClinicInPrice() {
		return clinicInPrice;
	}

	public void setClinicInPrice(double clinicInPrice) {
		this.clinicInPrice = clinicInPrice;
	}

	public double getClinicPrice() {
		return clinicPrice;
	}

	public void setClinicPrice(double clinicPrice) {
		this.clinicPrice = clinicPrice;
	}

	public double getInhosInPrice() {
		return inhosInPrice;
	}

	public void setInhosInPrice(double inhosInPrice) {
		this.inhosInPrice = inhosInPrice;
	}

	public double getInhosPrice() {
		return inhosPrice;
	}

	public void setInhosPrice(double inhosPrice) {
		this.inhosPrice = inhosPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpenseClass() {
		return expenseClass;
	}

	public void setExpenseClass(String expenseClass) {
		this.expenseClass = expenseClass;
	}

	public String getStoreClass() {
		return storeClass;
	}

	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
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

	public String getClinicUnit() {
		return clinicUnit;
	}

	public void setClinicUnit(String clinicUnit) {
		this.clinicUnit = clinicUnit;
	}

	public int getClinicUnitRatio() {
		return clinicUnitRatio;
	}

	public void setClinicUnitRatio(int clinicUnitRatio) {
		this.clinicUnitRatio = clinicUnitRatio;
	}

	public String getInhosUnit() {
		return inhosUnit;
	}

	public void setInhosUnit(String inhosUnit) {
		this.inhosUnit = inhosUnit;
	}

	public int getInhosUnitRatio() {
		return inhosUnitRatio;
	}

	public void setInhosUnitRatio(int inhosUnitRatio) {
		this.inhosUnitRatio = inhosUnitRatio;
	}

	public String getAdviceUnit() {
		return adviceUnit;
	}

	public void setAdviceUnit(String adviceUnit) {
		this.adviceUnit = adviceUnit;
	}

	public String getAdviceUnitValue() {
		return adviceUnitValue;
	}

	public void setAdviceUnitValue(String adviceUnitValue) {
		this.adviceUnitValue = adviceUnitValue;
	}

	public String getAdviceUnitValueUnit() {
		return adviceUnitValueUnit;
	}

	public void setAdviceUnitValueUnit(String adviceUnitValueUnit) {
		this.adviceUnitValueUnit = adviceUnitValueUnit;
	}

	public int getAdviceUnitClinicRatio() {
		return adviceUnitClinicRatio;
	}

	public void setAdviceUnitClinicRatio(int adviceUnitClinicRatio) {
		this.adviceUnitClinicRatio = adviceUnitClinicRatio;
	}

	public int getAdviceUnitInhosRatio() {
		return adviceUnitInhosRatio;
	}

	public void setAdviceUnitInhosRatio(int adviceUnitInhosRatio) {
		this.adviceUnitInhosRatio = adviceUnitInhosRatio;
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

	public String getDrugFunction() {
		return drugFunction;
	}

	public void setDrugFunction(String drugFunction) {
		this.drugFunction = drugFunction;
	}

	public String getDrugClass() {
		return drugClass;
	}

	public void setDrugClass(String drugClass) {
		this.drugClass = drugClass;
	}

	public String getDrugFrom() {
		return drugFrom;
	}

	public void setDrugFrom(String drugFrom) {
		this.drugFrom = drugFrom;
	}

	public int getCompositeItem() {
		return compositeItem;
	}

	public void setCompositeItem(int compositeItem) {
		this.compositeItem = compositeItem;
	}

	public int getSubItemSelect() {
		return subItemSelect;
	}

	public void setSubItemSelect(int subItemSelect) {
		this.subItemSelect = subItemSelect;
	}

	public int getPartSelect() {
		return partSelect;
	}

	public void setPartSelect(int partSelect) {
		this.partSelect = partSelect;
	}

	public int getChangePrice() {
		return changePrice;
	}

	public void setChangePrice(int changePrice) {
		this.changePrice = changePrice;
	}

	public int getShowInAdvice() {
		return showInAdvice;
	}

	public void setShowInAdvice(int showInAdvice) {
		this.showInAdvice = showInAdvice;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getHzylCode() {
		return hzylCode;
	}

	public void setHzylCode(String hzylCode) {
		this.hzylCode = hzylCode;
	}

	public int getHzylVerify() {
		return hzylVerify;
	}

	public void setHzylVerify(int hzylVerify) {
		this.hzylVerify = hzylVerify;
	}

	public int getHzylReimburse() {
		return hzylReimburse;
	}

	public void setHzylReimburse(int hzylReimburse) {
		this.hzylReimburse = hzylReimburse;
	}

	public String getYbCode() {
		return ybCode;
	}

	public void setYbCode(String ybCode) {
		this.ybCode = ybCode;
	}

	public double getYbClinicSelfRatio() {
		return ybClinicSelfRatio;
	}

	public void setYbClinicSelfRatio(double ybClinicSelfRatio) {
		this.ybClinicSelfRatio = ybClinicSelfRatio;
	}

	public double getYbInhosSelfRatio() {
		return ybInhosSelfRatio;
	}

	public void setYbInhosSelfRatio(double ybInhosSelfRatio) {
		this.ybInhosSelfRatio = ybInhosSelfRatio;
	}

}
