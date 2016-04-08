package com.template.domain;

/**
 * 药品基础信息实体
 * @Description: 药品基础信息实体
 * @author army.liu
 */
public class DicDrug {

	private int id;//标识（唯一）

    private String expenseClass;//收费类别（西药、中成药、中草药）

    private String storeClass;//库存类别

    private String itemName;//名称

    private String spec;//规格

    private String vendor;//生产商

    private String unit;//单位

    private String clinicUnit;//门诊零售单位

    private int clinicUnitRatio;//门诊零售单位比率

    private String inhosUnit;//住院零售单位

    private int inhosUnitRatio;//住院零售单位比率

    private String adviceUnit;//医嘱单位

    private String adviceUnitValue;//医嘱单位数值

    private String adviceUnitValueUnit;//医嘱单位数值单位

    private int adviceUnitClinicRatio;//医嘱单位门诊比率

    private int adviceUnitInhosRatio;//	医嘱单位住院比率

    private String wb;//五笔码

    private String py;//拼音码

    private String drugFunction;//药品功能代码

    private String drugClass;//药品类别

    private String drugFrom;//药品剂型

    private int compositeItem;//是否为复合项目

    private int subItemSelect;//是否有自选子项目

    private int partSelect;//是否自选部位（专指检查项目）

    private int changePrice;//是否可以更改价格

    private int showInAdvice;//是否在医嘱中显示

    private int enabled;//是否启用

    private String hzylCode;//合作医疗对应码

    private int hzylVerify;//合作医疗审批标志

    private int hzylReimburse;//合作医疗是否报销

    private String ybCode;//医疗保险对应码

    private double ybClinicSelfRatio;//医疗保险门诊自理比例

    private double ybInhosSelfRatio;//医疗保险住院自理比例

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
