package com.template.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.StoreMapper;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.AdjustPriceService;
import com.template.service.CommonService;

/**
 * 调价serviceimpl
* @author  fengql 
* @date 2016年4月7日 下午1:46:56
 */
@Service("adjustPriceService")
public class AdjustPriceServiceImpl implements AdjustPriceService{
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private StoreMapper storeMapper;

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
	public List<DrugAndStore> getStoreDrugList(String itemName) throws Exception {
		return storeMapper.getGroupByItemName(itemName);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifySuccess(int billNo, String verifyOper, String storeName) throws Exception {
		commonService.verifySuccess(billNo, verifyOper, storeName);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyFail(int billNo, String verifyOper) throws Exception {
		commonService.verifyFail(billNo, verifyOper);
		
	}

}
