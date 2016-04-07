package com.template.domain;

/**
 * 药品基础信息实体
 * @Description: 药品基础信息实体
 * @author army.liu
 */
public class DicDrug {

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

    private String drugfunction;//药品功能代码

    private String drugclass;//药品类别

    private String drugfrom;//药品剂型

    private int compositeitem;//是否为复合项目

    private int subitemselect;//是否有自选子项目

    private int partselect;//是否自选部位（专指检查项目）

    private int changeprice;//是否可以更改价格

    private int showinadvice;//是否在医嘱中显示

    private int enabled;//是否启用

    private String hzylcode;//合作医疗对应码

    private int hzylverify;//合作医疗审批标志

    private int hzylreimburse;//合作医疗是否报销

    private String ybcode;//医疗保险对应码

    private double ybclinicselfratio;//医疗保险门诊自理比例

    private double ybinhosselfratio;//医疗保险住院自理比例

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getClinicunitratio() {
        return clinicunitratio;
    }

    public void setClinicunitratio(int clinicunitratio) {
        this.clinicunitratio = clinicunitratio;
    }

    public String getInhosunit() {
        return inhosunit;
    }

    public void setInhosunit(String inhosunit) {
        this.inhosunit = inhosunit;
    }

    public int getInhosunitratio() {
        return inhosunitratio;
    }

    public void setInhosunitratio(int inhosunitratio) {
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

    public int getAdviceunitclinicratio() {
        return adviceunitclinicratio;
    }

    public void setAdviceunitclinicratio(int adviceunitclinicratio) {
        this.adviceunitclinicratio = adviceunitclinicratio;
    }

    public int getAdviceunitinhosratio() {
        return adviceunitinhosratio;
    }

    public void setAdviceunitinhosratio(int adviceunitinhosratio) {
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

    public int getCompositeitem() {
        return compositeitem;
    }

    public void setCompositeitem(int compositeitem) {
        this.compositeitem = compositeitem;
    }

    public int getSubitemselect() {
        return subitemselect;
    }

    public void setSubitemselect(int subitemselect) {
        this.subitemselect = subitemselect;
    }

    public int getPartselect() {
        return partselect;
    }

    public void setPartselect(int partselect) {
        this.partselect = partselect;
    }

    public int getChangeprice() {
        return changeprice;
    }

    public void setChangeprice(int changeprice) {
        this.changeprice = changeprice;
    }

    public int getShowinadvice() {
        return showinadvice;
    }

    public void setShowinadvice(int showinadvice) {
        this.showinadvice = showinadvice;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getHzylcode() {
        return hzylcode;
    }

    public void setHzylcode(String hzylcode) {
        this.hzylcode = hzylcode;
    }

    public int getHzylverify() {
        return hzylverify;
    }

    public void setHzylverify(int hzylverify) {
        this.hzylverify = hzylverify;
    }

    public int getHzylreimburse() {
        return hzylreimburse;
    }

    public void setHzylreimburse(int hzylreimburse) {
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
