package com.template.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.DicDrugMapper;
import com.template.dao.StoreMapper;
import com.template.domain.DicDrug;
import com.template.domain.Store;
import com.template.service.DicDrugService;

/**
 * 库存分类serviceimpl
 * @Description: 操作库存分类相关业务方法
 * @author army.liu
 */
@Service("dicDrugService")
public class DicDrugServiceImpl implements DicDrugService {

	@Resource
	private DicDrugMapper dicDrugMapper;
	
	@Resource
	private StoreMapper storeMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(DicDrug bean) throws Exception {
		int id = bean.getId();
		if( 0 == id ){
			dicDrugMapper.insert(bean);
			
		}else{
			int enabled = bean.getEnabled();
			if( 0 == enabled ){
				//检查库存表，若其中存在，则不可以删除
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("drugId", id);
				List<Store> store = storeMapper.getByConditions(params);
				if( null != store && store.size() > 0 ){
					throw new RuntimeException("库存中存在此药品，不可停用");
				}
				
			}
			dicDrugMapper.update(bean);
			
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) throws Exception {
		//检查库存表，若其中存在，则不可以删除
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("drugId", id);
		List<Store> store = storeMapper.getByConditions(params);
		if( null != store && store.size() > 0 ){
			throw new RuntimeException("库存中存在此药品，不可删除");
		}
		
		dicDrugMapper.delete(id);
		
	}

	@Override
	public List<DicDrug> getByConditions(Map<String, Object> params)
			throws Exception {
		return dicDrugMapper.getByConditions(params);
		
	}

	@Override
	public List<DicDrug> getEnabledDrugList(String itemName) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemName", itemName);
		params.put("enabled", 1);
		return dicDrugMapper.getByConditions(params);
	}

	@Override
	public DicDrug getById(int id) throws Exception {
		return dicDrugMapper.getById(id);
	}

	@Override
	public Map<String, Object> exportDrugBaseInfoTemplate() throws Exception {
		Map<String, Object> formatData = new HashMap<String, Object>();
		// sheet
		List<String> sheetList = new ArrayList<String>();
		sheetList.add("药品基础信息数据");
		formatData.put("sheetList", sheetList);//

		// 标题
		Map<String, Object> sheetData = new HashMap<String, Object>();
		sheetData.put("title", "药品基础信息数据模板");//
		sheetData.put("titleMergeSize", 39);//

		// 表头
		List<String> tableHeadList = new ArrayList<String>();
		tableHeadList.add("序号");
		tableHeadList.add("收费类别");
		tableHeadList.add("库存类别");
		tableHeadList.add("名称");
		tableHeadList.add("规格");
		tableHeadList.add("生产商");
		tableHeadList.add("进价");
		tableHeadList.add("零售价");
		tableHeadList.add("单位");
		tableHeadList.add("门诊进价");
		tableHeadList.add("门诊零售价");
		tableHeadList.add("门诊零售单位");
		tableHeadList.add("门诊零售单位比率");
		tableHeadList.add("住院进价");
		tableHeadList.add("住院零售价");
		tableHeadList.add("住院零售单位");
		tableHeadList.add("住院零售单位比率");
		tableHeadList.add("医嘱单位");
		tableHeadList.add("医嘱单位数值");
		tableHeadList.add("医嘱单位数值单位");
		tableHeadList.add("医嘱单位门诊比率");
		tableHeadList.add("医嘱单位住院比率");
		tableHeadList.add("五笔码");
		tableHeadList.add("拼音码");
		tableHeadList.add("药品功能代码");
		tableHeadList.add("药品类别");
		tableHeadList.add("药品剂型");
		tableHeadList.add("是否为复合项目");
		tableHeadList.add("是否有自选子项目");
		tableHeadList.add("是否自选部位（专指检查项目）");
		tableHeadList.add("是否可以更改价格");
		tableHeadList.add("是否在医嘱中显示");
		tableHeadList.add("是否启用");
		tableHeadList.add("合作医疗对应码");
		tableHeadList.add("合作医疗审批标志");
		tableHeadList.add("合作医疗是否报销");
		tableHeadList.add("医疗保险对应码");
		tableHeadList.add("医疗保险门诊自理比例");
		tableHeadList.add("医疗保险住院自理比例");
		sheetData.put("tableHeader", tableHeadList);//
	
		// 表数据
		List<List<Object>> tableData = new ArrayList<List<Object>>();
		List<Object> rowData = new ArrayList<Object>();
		rowData.add("10001");
		rowData.add("1");
		rowData.add("1");
		rowData.add("红花注射液");
		rowData.add("20ml");
		rowData.add(".");
		rowData.add("10");
		rowData.add("15");
		rowData.add("支");
		rowData.add("10");
		rowData.add("15");
		rowData.add("支");
		rowData.add("1");
		rowData.add("10");
		rowData.add("15");
		rowData.add("支");
		rowData.add("1");
		rowData.add("支");
		rowData.add("20");
		rowData.add("ml");
		rowData.add("1");
		rowData.add("1");
		rowData.add("xaiti");
		rowData.add("hhzsy");
		rowData.add("1");
		rowData.add("1");
		rowData.add("1");
		rowData.add("0");
		rowData.add("0");
		rowData.add("0");
		rowData.add("0");
		rowData.add("0");
		rowData.add("1");
		rowData.add("");
		rowData.add("0");
		rowData.add("0");
		rowData.add("");
		rowData.add("0");
		rowData.add("0");
		
		tableData.add(rowData);
		sheetData.put("tableData", tableData);
		formatData.put("sheetData", sheetData);
		
		return formatData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Map<String, Object> excelImport(Map<String, Object> importResult)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String msg="";
		
		Map<String, Object> sheetDatas=	(Map<String, Object>) importResult.get("data");
		List<List<String>> importData=(List<List<String>>) sheetDatas.get("0");
		
		for(int i=0;i<importData.size();i++){
			
			DicDrug dicDrug=new DicDrug();
			//得到每一行的数据
			List<String> data=importData.get(i);
			
			for(int j=1;j<data.size();j++){
				
				String celldata = data.get(j);
				//得到每一列的数据并解析
				switch(j)
		        {
					case 1://收费类别
						if(celldata.length()<=16){
							dicDrug.setExpenseClass(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(收费类别)的数据长度不对(最多16位)、";
						}
						break;
					case 2://库存类别
						if(celldata.length()<=16){
							dicDrug.setStoreClass(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(库存类别)的数据长度不对(最多16位)、";
						}
						break;
					case 3://名称
						if(celldata.length()<=64){
							dicDrug.setItemName(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(名称)的数据长度不对(最多64位)、";
						}
						break;
					case 4://规格
						if(celldata.length()<=32){
							dicDrug.setSpec(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(规格)的数据长度不对(最多32位)、";
						}
						break;
					case 5://生产商
						if(celldata.length()<=32){
							dicDrug.setVendor(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(生产商)的数据长度不对(最多32位)、";
						}
						break;
					case 6://进价
						int length6=isDouble(celldata);
						if(length6<=3 && length6!=-1){
							double inPrice=Double.parseDouble(celldata);
							dicDrug.setInPrice(inPrice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(进价)的数据类型不对(最多保留3位小数)、";
						}
						break;
					case 7://零售价
						int length7=isDouble(celldata);
						if(length7<=3 && length7!=-1){
							double price=Double.parseDouble(celldata);
							dicDrug.setPrice(price);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(零售价)的数据类型不对(最多保留3位小数)、";
						}
						break;
					case 8://单位
						if(celldata.length()<=16){
							dicDrug.setUnit(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(单位)的数据长度不对(最多16位)、";
						}
						break;
					case 9://门诊进价
						int length9=isDouble(celldata);
						if(length9<=3 && length9!=-1){
							double clinicInPrice=Double.parseDouble(celldata);
							dicDrug.setClinicInPrice(clinicInPrice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(门诊进价)的数据类型不对(最多保留3位小数)、";
						}
						break;
					case 10://门诊零售价
						int length10=isDouble(celldata);
						if(length10<=3 && length10!=-1){
							double clinicPrice=Double.parseDouble(celldata);
							dicDrug.setClinicPrice(clinicPrice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(门诊零售价)的数据类型不对(最多保留3位小数)、";
						}
						break;
					case 11://门诊零售单位
						if(celldata.length()<=16){
							dicDrug.setClinicUnit(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(门诊零售单位)的数据长度不对(最多16位)、";
						}
						break;
					case 12://门诊零售单位比率
						if(isInteger(celldata)){
							int clinicUnitRatio=Integer.parseInt(celldata);
							dicDrug.setClinicUnitRatio(clinicUnitRatio);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(门诊零售单位比率)的数据类型不对(应该为整数)、";
						}
						break;
					case 13://住院进价
						int length13=isDouble(celldata);
						if(length13<=3 && length13!=-1){
							double inhosInPrice=Double.parseDouble(celldata);
							dicDrug.setInhosInPrice(inhosInPrice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(住院进价)的数据类型不对(最多保留3位小数)、";
						}
						break;
					case 14://住院零售价
						int length14=isDouble(celldata);
						if(length14<=3 && length14!=-1){
							double inhosPrice=Double.parseDouble(celldata);
							dicDrug.setInhosPrice(inhosPrice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(住院零售价)的数据类型不对(最多保留3位小数)、";
						}
						break;
					case 15://住院零售单位
						if(celldata.length()<=16){
							dicDrug.setInhosUnit(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(住院零售单位)的数据长度不对(最多16位)、";
						}
						break;
					case 16://住院零售单位比率
						if(isInteger(celldata)){
							int inhosUnitRatio=Integer.parseInt(celldata);
							dicDrug.setInhosUnitRatio(inhosUnitRatio);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(住院零售单位比率)的数据类型不对(应该为整数)、";
						}
						break;
					case 17://医嘱单位
						if(celldata.length()<=16){
							dicDrug.setAdviceUnit(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医嘱单位)的数据长度不对(最多16位)、";
						}
						break;
					case 18://医嘱单位数值
						if(celldata.length()<=16){
							dicDrug.setAdviceUnitValue(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医嘱单位数值)的数据长度不对(最多16位)、";
						}
						break;
					case 19://医嘱单位数值单位
						if(celldata.length()<=16){
							dicDrug.setAdviceUnitValueUnit(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医嘱单位数值单位)的数据长度不对(最多16位)、";
						}
						break;
					case 20://医嘱单位门诊比率
						if(isInteger(celldata)){
							int adviceUnitClinicRatio=Integer.parseInt(celldata);
							dicDrug.setAdviceUnitClinicRatio(adviceUnitClinicRatio);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医嘱单位门诊比率)的数据类型不对(应该为整数)、";
						}
						break;
					case 21://医嘱单位住院比率
						if(isInteger(celldata)){
							int adviceUnitInhosRatio=Integer.parseInt(celldata);
							dicDrug.setAdviceUnitInhosRatio(adviceUnitInhosRatio);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医嘱单位住院比率)的数据类型不对(应该为整数)、";
						}
						break;
					case 22://五笔码
						if(celldata.length()<=64){
							dicDrug.setWb(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(五笔码)的数据长度不对(最多64位)、";
						}
						break;
					case 23://拼音码
						if(celldata.length()<=64){
							dicDrug.setPy(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(拼音码)的数据长度不对(最多64位)、";
						}
						break;
					case 24://药品功能代码
						if(celldata.length()<=16){
							dicDrug.setDrugFunction(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(药品功能代码)的数据长度不对(最多16位)、";
						}
						break;
					case 25://药品类别
						if(celldata.length()<=16){
							dicDrug.setDrugClass(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(药品类别)的数据长度不对(最多16位)、";
						}
						break;
					case 26://药品剂型
						if(celldata.length()<=16){
							dicDrug.setDrugFrom(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(药品剂型)的数据长度不对(最多16位)、";
						}
						break;
					case 27://是否为复合项目
						if(isInteger(celldata)){
							int compositeItem=Integer.parseInt(celldata);
							dicDrug.setCompositeItem(compositeItem);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(是否为复合项目)的数据类型不对(应该为整数)、";
						}
						break;
					case 28://是否有自选子项目
						if(isInteger(celldata)){
							int subItemSelect=Integer.parseInt(celldata);
							dicDrug.setSubItemSelect(subItemSelect);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(是否有自选子项目)的数据类型不对(应该为整数)、";
						}
						break;
					case 29://是否自选部位（专指检查项目）
						if(isInteger(celldata)){
							int partSelect=Integer.parseInt(celldata);
							dicDrug.setPartSelect(partSelect);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(是否自选部位)的数据类型不对(应该为整数)、";
						}
						break;
					case 30://是否可以更改价格
						if(isInteger(celldata)){
							int changePrice=Integer.parseInt(celldata);
							dicDrug.setChangePrice(changePrice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(是否可以更改价格)的数据类型不对(应该为整数)、";
						}
						break;
					case 31://是否在医嘱中显示
						if(isInteger(celldata)){
							int showInAdvice=Integer.parseInt(celldata);
							dicDrug.setShowInAdvice(showInAdvice);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(是否在医嘱中显示)的数据类型不对(应该为整数)、";
						}
						break;
					case 32://是否启用
						if(isInteger(celldata)){
							int enabled=Integer.parseInt(celldata);
							dicDrug.setEnabled(enabled);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(是否启用)的数据类型不对(应该为整数)、";
						}
						break;
					case 33://合作医疗对应码
						if(celldata.length()<=32){
							dicDrug.setHzylCode(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(合作医疗对应码)的数据长度不对(最多32位)、";
						}
						break;
					case 34://合作医疗审批标志
						if(isInteger(celldata)){
							int hzylVerify=Integer.parseInt(celldata);
							dicDrug.setHzylVerify(hzylVerify);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(合作医疗审批标志)的数据类型不对(应该为整数)、";
						}
						break;
					case 35://合作医疗是否报销
						if(isInteger(celldata)){
							int hzylReimburse=Integer.parseInt(celldata);
							dicDrug.setHzylReimburse(hzylReimburse);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(合作医疗是否报销)的数据类型不对(应该为整数)、";
						}
						break;
					case 36://医疗保险对应码
						if(celldata.length()<=32){
							dicDrug.setYbCode(celldata);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医疗保险对应码)的数据长度不对(最多32位)、";
						}
						break;
					case 37://医疗保险门诊自理比例
						int length37=isDouble(celldata);
						if(length37<=2 && length37!=-1){
							double ybClinicSelfRatio=Double.parseDouble(celldata);
							dicDrug.setYbClinicSelfRatio(ybClinicSelfRatio);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医疗保险门诊自理比例)的数据类型不对(最多保留2位小数)、";
						}
						break;
					case 38://医疗保险住院自理比例
						int length38=isDouble(celldata);
						if(length38<=2 && length38!=-1){
							double ybInhosSelfRatio=Double.parseDouble(celldata);
							dicDrug.setYbInhosSelfRatio(ybInhosSelfRatio);
						}else{
							msg+="第"+(i+1)+"行第"+(j+1)+"列(医疗保险住院自理比例)的数据类型不对(最多保留2位小数)、";
						}
						break;
					default: break;
		        }
			}
			if(msg==""){
				//将数据插入到药品基础信息表中
				dicDrugMapper.insert(dicDrug);
			}
		}
		if(msg!=""){
			msg=msg.substring(0, msg.length()-1);
			result.put("code",300);
			result.put("msg",msg);
			return result;
		}
		result.put("code",200);
		return result;
	}
	
	@SuppressWarnings("unused")
	public int isDouble(String str){
        int length;
        try{
            double d = Double.parseDouble(str);
            int index=str.indexOf(".");
            if(index==-1){
            	length=0;
            }else{
            	length=str.substring(str.indexOf(".")+1).length();
            }
        }catch(Exception ex){
        	length = -1;
        }
        return length;
    }

	@SuppressWarnings("unused")
	public boolean isInteger(String str){
        boolean ret = true;
        try{
            int d = Integer.parseInt(str);
            ret = true;
        }catch(Exception ex){
            ret = false;
        }
        return ret;
    }

}
