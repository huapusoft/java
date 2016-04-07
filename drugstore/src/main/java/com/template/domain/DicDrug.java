package com.template.domain;

import java.math.BigDecimal;

/**
 * 药品基础信息实体
 * @Description: 药品基础信息实体
 * @author army.liu
 */
public class DicDrug {

//	adviceUnitValueUnit	char:16	医嘱单位数值单位
//	adviceUnitClinicRatio	int	医嘱单位门诊比率
//	adviceUnitInhosRatio	int	医嘱单位住院比率
//	wb	char:64	五笔码
//	py	char:64	拼音码
//	drugFunction	char:16	药品功能代码
//	drugClass	char:16	药品类别
//	drugForm	char:16	药品剂型
//	compositeItem	int	是否为复合项目
//	subItemSelect	int	是否有自选子项目
//	partSelect	int	是否自选部位（专指检查项目）
//	changePrice	int	是否可以更改价格
//	showInAdvice	int	是否在医嘱中显示
//	enabled	int	是否启用
//	hzylCode	char:32	合作医疗对应码
//	hzylVerify	int	合作医疗审批标志
//	hzylReimburse	int	合作医疗是否报销
//	ybCode	char:32	医疗保险对应码
//	ybClinicSelfRatio	numeric:(8,2)	医疗保险门诊自理比例
//	ybInhosSelfRatio	numeric:(8,2)	医疗保险住院自理比例

	private int id;//标识（唯一）

    private String expenseclass;//收费类别（西药、中成药、中草药）

    private String storeclass;//库存类别

    private String itemname;//名称

    private String spec;//规格

    private String vendor;//生产商

    private String unit;//单位

    private String clinicunit;//门诊零售单位

    private int clinicunitratio;//门诊零售单位比率

    private String inhosunit;//住院零售单位

    private int inhosunitratio;//住院零售单位比率

    private String adviceunit;//医嘱单位

    private String adviceunitvalue;//医嘱单位数值

    private String adviceunitvalueunit;//医嘱单位数值单位

    private int adviceunitclinicratio;//医嘱单位门诊比率

    private int adviceunitinhosratio;//	医嘱单位住院比率

    private String wb;//五笔码

    private String py;//拼音码

    private String drugfunction;//

    private String drugclass;//

    private String drugfrom;//

    private Integer compositeitem;//

    private Integer subitemselect;//

    private Integer partselect;//

    private Integer changeprice;//

    private Integer showinadvice;//

    private Integer enabled;//

    private String hzylcode;//

    private Integer hzylverify;//

    private Integer hzylreimburse;//

    private String ybcode;//

    private double ybclinicselfratio;//

    private double ybinhosselfratio;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpenseclass() {
        return expenseclass;
    }

    public void setExpenseclass(String expenseclass) {
        this.expenseclass = expenseclass;
    }

    public String getStoreclass() {
        return storeclass;
    }

    public void setStoreclass(String storeclass) {
        this.storeclass = storeclass;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
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

    public String getClinicunit() {
        return clinicunit;
    }

    public void setClinicunit(String clinicunit) {
        this.clinicunit = clinicunit;
    }

    public Integer getClinicunitratio() {
        return clinicunitratio;
    }

    public void setClinicunitratio(Integer clinicunitratio) {
        this.clinicunitratio = clinicunitratio;
    }

    public String getInhosunit() {
        return inhosunit;
    }

    public void setInhosunit(String inhosunit) {
        this.inhosunit = inhosunit;
    }

    public Integer getInhosunitratio() {
        return inhosunitratio;
    }

    public void setInhosunitratio(Integer inhosunitratio) {
        this.inhosunitratio = inhosunitratio;
    }

    public String getAdviceunit() {
        return adviceunit;
    }

    public void setAdviceunit(String adviceunit) {
        this.adviceunit = adviceunit;
    }

    public String getAdviceunitvalue() {
        return adviceunitvalue;
    }

    public void setAdviceunitvalue(String adviceunitvalue) {
        this.adviceunitvalue = adviceunitvalue;
    }

    public String getAdviceunitvalueunit() {
        return adviceunitvalueunit;
    }

    public void setAdviceunitvalueunit(String adviceunitvalueunit) {
        this.adviceunitvalueunit = adviceunitvalueunit;
    }

    public Integer getAdviceunitclinicratio() {
        return adviceunitclinicratio;
    }

    public void setAdviceunitclinicratio(Integer adviceunitclinicratio) {
        this.adviceunitclinicratio = adviceunitclinicratio;
    }

    public Integer getAdviceunitinhosratio() {
        return adviceunitinhosratio;
    }

    public void setAdviceunitinhosratio(Integer adviceunitinhosratio) {
        this.adviceunitinhosratio = adviceunitinhosratio;
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

    public String getDrugfunction() {
        return drugfunction;
    }

    public void setDrugfunction(String drugfunction) {
        this.drugfunction = drugfunction;
    }

    public String getDrugclass() {
        return drugclass;
    }

    public void setDrugclass(String drugclass) {
        this.drugclass = drugclass;
    }

    public String getDrugfrom() {
        return drugfrom;
    }

    public void setDrugfrom(String drugfrom) {
        this.drugfrom = drugfrom;
    }

    public Integer getCompositeitem() {
        return compositeitem;
    }

    public void setCompositeitem(Integer compositeitem) {
        this.compositeitem = compositeitem;
    }

    public Integer getSubitemselect() {
        return subitemselect;
    }

    public void setSubitemselect(Integer subitemselect) {
        this.subitemselect = subitemselect;
    }

    public Integer getPartselect() {
        return partselect;
    }

    public void setPartselect(Integer partselect) {
        this.partselect = partselect;
    }

    public Integer getChangeprice() {
        return changeprice;
    }

    public void setChangeprice(Integer changeprice) {
        this.changeprice = changeprice;
    }

    public Integer getShowinadvice() {
        return showinadvice;
    }

    public void setShowinadvice(Integer showinadvice) {
        this.showinadvice = showinadvice;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getHzylcode() {
        return hzylcode;
    }

    public void setHzylcode(String hzylcode) {
        this.hzylcode = hzylcode;
    }

    public Integer getHzylverify() {
        return hzylverify;
    }

    public void setHzylverify(Integer hzylverify) {
        this.hzylverify = hzylverify;
    }

    public Integer getHzylreimburse() {
        return hzylreimburse;
    }

    public void setHzylreimburse(Integer hzylreimburse) {
        this.hzylreimburse = hzylreimburse;
    }

    public String getYbcode() {
        return ybcode;
    }

    public void setYbcode(String ybcode) {
        this.ybcode = ybcode;
    }

    public double getYbclinicselfratio() {
        return ybclinicselfratio;
    }

    public void setYbclinicselfratio(double ybclinicselfratio) {
        this.ybclinicselfratio = ybclinicselfratio;
    }

    public double getYbinhosselfratio() {
        return ybinhosselfratio;
    }

    public void setYbinhosselfratio(double ybinhosselfratio) {
        this.ybinhosselfratio = ybinhosselfratio;
    }
	
}
