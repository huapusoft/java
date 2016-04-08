package com.template.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.template.dao.StoreInOutDetailMapper;
import com.template.dao.StoreInOutMapper;
import com.template.dao.StoreMapper;
import com.template.domain.Store;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
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
	
	@Override
	public List<Store> getByConditions(Map<String, Object> params)
			throws Exception {
		return storeMapper.getByConditions(params);
		
	}

	@Override
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
					bean.setId(drugId);
					bean.setBatchNo(batchNo);
					bean.setAmount(amount);
					bean.setInTime(new Date());
					bean.setValidDate(validDate);
					bean.setSpecial(0);
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
					bean.setId(drugId);
					bean.setBatchNo(batchNo);
					bean.setAmount(0-amount);
					bean.setInTime(new Date());
					bean.setValidDate(validDate);
					bean.setSpecial(0);
					storeMapper.insert(bean);
					
				}else{//减库存
					Store bean = storeList.get(0);
					if( bean.getAmount()-amount == 0 ){
						storeMapper.delete(bean);
						
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
					Store bean = storeList.get(0);
					bean.setPrice(price2);
					storeMapper.update(bean);
					
				}
			}
			
		}
	}

}
