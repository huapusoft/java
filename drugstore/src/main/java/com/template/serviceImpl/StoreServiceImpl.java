package com.template.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.StoreInOutDetailMapper;
import com.template.dao.StoreInOutMapper;
import com.template.dao.StoreMapper;
import com.template.domain.DrugAndStore;
import com.template.domain.Store;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.InStorageService;
import com.template.service.StoreService;
import com.template.util.Constants;

/**
 * 库存serviceimpl
 * @Description: 操作库存相关业务方法
 * @author army.liu
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private StoreMapper storeMapper;
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Resource
	private InStorageService inStorageService;	
	
	@Override
	public List<Store> getByConditions(Map<String, Object> params)
			throws Exception {
		return storeMapper.getByConditions(params);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifySuccess(int billNo, String currStoreName) throws Exception {
		// TODO Auto-generated method stub
		StoreInOut inOut = storeInOutMapper.getById(billNo);
		if( null == inOut ){
			throw new RuntimeException("票据号对应的数据为空");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billNo", billNo);
		List<StoreInOutDetail> detailList = storeInOutDetailMapper.getByConditions(params);
		if( null == detailList || detailList.size() == 0 ){
			throw new RuntimeException("出入库明细信息为空");
		}
		
		String billType = inOut.getBillType();
		if( Constants.BusinessType.IN.equals(billType) ){//入库：插入或更新加库存
			for(int i=0; i<detailList.size(); i++){
				StoreInOutDetail detail = detailList.get(i);
				String storeName = currStoreName;
				int drugId = detail.getDrugId();
				String batchNo = detail.getBatchNo();
				double price1 = detail.getPrice1();
				double price2 = detail.getPrice2();
				double amount = detail.getAmount();
				Date validDate = detail.getValidDate();
				
				Map<String, Object> paramsForStore = new HashMap<String, Object>();
				paramsForStore.put("storeName", storeName);
				paramsForStore.put("drugId", drugId);
				paramsForStore.put("batchNo", batchNo);
				paramsForStore.put("inPrice", price1);
				paramsForStore.put("price", price2);
				List<Store> storeList = storeMapper.getByConditions(paramsForStore);
				if( null == storeList || storeList.size() == 0 ){//插入
					Store bean = new Store();
					bean.setStoreName(storeName);
					bean.setDrugId(drugId);
					bean.setBatchNo(batchNo);
					bean.setAmount(amount);
					bean.setInTime(new Date());
					bean.setValidDate(validDate);
					bean.setSpecial(0);
					bean.setInPrice(price1);
					bean.setPrice(price2);
					storeMapper.insert(bean);
					
				}else{//加库存
					Store bean = storeList.get(0);
					bean.setAmount( bean.getAmount()+amount );
					storeMapper.update(bean);
					
				}
			}
			
		}else if( Constants.BusinessType.OUT.equals(billType) 
					|| Constants.BusinessType.SALES_RETURN.equals(billType)
					|| Constants.BusinessType.BREAKAGE.equals(billType) ){//出库：减库存
			for(int i=0; i<detailList.size(); i++){
				StoreInOutDetail detail = detailList.get(i);
				String storeName = currStoreName;
				int drugId = detail.getDrugId();
				String batchNo = detail.getBatchNo();
				double price1 = detail.getPrice1();
				double price2 = detail.getPrice2();
				double amount = detail.getAmount();
				Date validDate = detail.getValidDate();
				
				Map<String, Object> paramsForStore = new HashMap<String, Object>();
				paramsForStore.put("storeName", storeName);
				paramsForStore.put("drugId", drugId);
				paramsForStore.put("batchNo", batchNo);
				paramsForStore.put("inPrice", price1);
				paramsForStore.put("price", price2);
				List<Store> storeList = storeMapper.getByConditions(paramsForStore);
				if( null == storeList || storeList.size() == 0 ){//插入：如果库存中没有的时候
					Store bean = new Store();
					bean.setStoreName(storeName);
					bean.setDrugId(drugId);
					bean.setBatchNo(batchNo);
					bean.setAmount(0-amount);
					bean.setInTime(new Date());
					bean.setValidDate(validDate);
					bean.setSpecial(0);
					bean.setInPrice(price1);
					bean.setPrice(price2);
					storeMapper.insert(bean);
					
				}else{//减库存
					Store bean = storeList.get(0);
					Double rs = bean.getAmount()-amount;
					if( rs.intValue() == 0 ){
						storeMapper.delete(bean.getId());
						
					}else{
						bean.setAmount( bean.getAmount()-amount );
						storeMapper.update(bean);
						
					}
					
				}
			}
			
		}else if( Constants.BusinessType.ADJUST_PRICE.equals(billType) ){//调价：更新零售价
			for(int i=0; i<detailList.size(); i++){
				StoreInOutDetail detail = detailList.get(i);
				String storeName = currStoreName;
				int drugId = detail.getDrugId();
				double price1 = detail.getPrice1();
				double price2 = detail.getPrice2();
				
				Map<String, Object> paramsForStore = new HashMap<String, Object>();
				paramsForStore.put("storeName", storeName);
				paramsForStore.put("drugId", drugId);
				paramsForStore.put("price", price1);
				List<Store> storeList = storeMapper.getByConditions(paramsForStore);
				if( null == storeList || storeList.size() == 0 ){//如果库存中没有的时候
					
				}else{//调零售价
					for(int j=0; j<storeList.size(); j++){
						Store bean = storeList.get(j);
						bean.setPrice(price2);
						storeMapper.update(bean);
					}
					
				}
			}
			
		}
	}

	@Override
	public List<DrugAndStore> getByConditionsForQuery(Map<String, Object> params) throws Exception {
		List<DrugAndStore> detailList=storeMapper.getDrugAndStoreDataList(params);
		
		//以下为采购时用到，取得最近一次进价、零售价
		/*for(int i=0;i<detailList.size();i++){
			DrugAndStore drugAndStore=detailList.get(i);
			String storeName=(String) params.get("storeName");
			Map<String, Object> data = inStorageService.getDrugLatestPrice( storeName, drugAndStore.getDrugId());
			drugAndStore.setInPriceNew((Double)data.get("price1"));
			drugAndStore.setPriceNew((Double)data.get("price2"));
			detailList.set(i, drugAndStore);
		}*/
		
		return detailList;
	}

	@Override
	public Map<String, Object> getExportData(Map<String, Object> params)
			throws Exception {
		//得到库存数据
		List<DrugAndStore> detailList=storeMapper.getDrugAndStoreDataList(params);
		
		//构造导出表格
		Map<String, Object> formatData = new HashMap<String, Object>();
		// sheet
		List<String> sheetList = new ArrayList<String>();
		sheetList.add("sheet");
		formatData.put("sheetList", sheetList);//

		// 标题
		Map<String, Object> sheetData = new HashMap<String, Object>();
		sheetData.put("title", "当前药品库存数据");//
		sheetData.put("titleMergeSize", 11);//导出数据的列数

		// 表头
		List<String> tableHeadList = new ArrayList<String>();
		tableHeadList.add("序号");
		tableHeadList.add("名称");
		tableHeadList.add("规格");
		tableHeadList.add("厂家");
		tableHeadList.add("数量");
		tableHeadList.add("单位");
		tableHeadList.add("进价");
		tableHeadList.add("零售价");
		tableHeadList.add("批号");
		tableHeadList.add("有效期");
		tableHeadList.add("摆放位置");
		sheetData.put("tableHeader", tableHeadList);

		// 表数据
		List<List<String>> tableData = new ArrayList<List<String>>();
		for(int i=0;i<detailList.size();i++){
			//获取每一行数据
			DrugAndStore drugAndStore = detailList.get(i);
			
			//加载数据
			List<String> rowData = new ArrayList<String>();
			rowData.add(String.valueOf(i+1));
			rowData.add(drugAndStore.getItemName());
			rowData.add(drugAndStore.getSpec());
			rowData.add(drugAndStore.getVendor());
			rowData.add(String.valueOf(drugAndStore.getAmount()));
			rowData.add(drugAndStore.getUnit());
			rowData.add(String.valueOf(drugAndStore.getInPrice()));
			rowData.add(String.valueOf(drugAndStore.getPrice()));
			rowData.add(drugAndStore.getBatchNo());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rowData.add( sdf.format(drugAndStore.getValidDate()) );
			rowData.add(drugAndStore.getPlace1());
			tableData.add(rowData);
		}
		sheetData.put("tableData", tableData);
		formatData.put("sheetData", sheetData);
		
		return formatData;
	}

}
