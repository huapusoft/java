package com.template.serviceImpl;

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
import com.template.domain.Store;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.InStorageService;

/**
 * 入库serviceimpl
* @author  fengql 
* @date 2016年4月5日 上午10:59:41
 */
@Service("inStorageService")
public class InStorageServiceImpl implements InStorageService{
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreMapper storeMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Resource
	private CommonService commonService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception{
		commonService.saveStoreInOut(inOut, detailList, billOper, storeName);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void submit(int billNo) throws Exception {
		commonService.submitStoreInOut(billNo);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int billNo) throws Exception {
		commonService.deleteStoreInOut(billNo);
		
	}

	@Override
	public Map<String, Object> getDrugLatestPrice(String storeName, int id,
			String batchNo) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("price1", null);
		result.put("price2", null);
		
		//1.查询出入库表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeName", storeName);
		params.put("drugId", id);
		params.put("batchNo", batchNo);
		StoreInOutDetail detail = storeInOutDetailMapper.getDrugLatestPrice(params);
		if( null != detail ){
			result.put("price1", detail.getPrice1());
			result.put("price2", detail.getPrice2());
			
		}else{
			//2.从库存表中获取
			Store store = storeMapper.getDrugLatestPrice(params);
			if( null != store ){
				result.put("price1", store.getInPrice());
				result.put("price2", store.getPrice());
			}
			
		}
		
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifySuccess(int billNo, String verifyOper) throws Exception {
		commonService.verifySuccess(billNo, verifyOper);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyFail(int billNo, String verifyOper) throws Exception {
		commonService.verifyFail(billNo, verifyOper);
		
	}

}
