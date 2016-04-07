package com.template.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.StoreInOutDetailMapper;
import com.template.dao.StoreInOutMapper;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.InStorageService;

/**
 * 入库serviceimpl
* @author  fengql 
* @date 2016年4月5日 上午10:59:41
 */
@Service("InStorageService")
public class InStorageServiceImpl implements InStorageService{
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
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
	
}
