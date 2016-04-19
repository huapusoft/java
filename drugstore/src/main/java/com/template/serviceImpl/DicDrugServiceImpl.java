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
	public Map<String, Object> excelImport(Map<String, Object> result)
			throws Exception {
		Map<String, Object> sheetDatas=	(Map<String, Object>) result.get("data");
		List<List<String>> importData=(List<List<String>>) sheetDatas.get("sheetIndex");
		for(int i=0;i<importData.size();i++){
			//得到每一行的数据
			List<String> data=importData.get(i);
			for(int j=0;j<data.size();j++){
				//得到每一列的数据并解析
				switch(j)
		        {
					case 1://收费类别
					;
					case 2://库存类别
						;
					case 3://名称
						;
					case 4://规格
						;
					case 5://生产商
						;
					case 6://进价
						;
					case 7://零售价
						;
	
					case 8://单位
						;
					case 9://门诊进价
						;
					case 10://门诊零售价
						;
					case 11://门诊零售单位
						;
					case 12://门诊零售单位比率
						;
					case 13://住院进价
						;
					case 14://住院零售价
						;
					case 15://
						;
					case 16://收费类别
						;
					case 17://收费类别
						;
					case 18://收费类别
						;
					case 19://收费类别
						;
					case 20://收费类别
						;
					case 21://收费类别
						;
					case 22://收费类别
						;
					case 23://收费类别
						;
					case 24://收费类别
						;
					case 25://收费类别
						;
					case 26://收费类别
						;
					case 27://收费类别
						;
					case 28://收费类别
						;
					case 29://收费类别
						;
					case 30://收费类别
						;
					case 31://收费类别
						;
						
					default:
		        	System.out.println("other");
		        }
			}
		}
		return null;
	}

}
