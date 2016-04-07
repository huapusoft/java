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
import com.template.service.OutStorageService;

/**
 * 出库serviceimpl
 * @Description: 提供出库业务方法
 * @author army.liu
 */
@Service("outStorageService")
public class OutStorageServiceImpl implements OutStorageService {
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Resource
	private CommonService commonService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception {
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

//	@Override
//	public void verify(Integer billNo, String verifyOper) {
//		// TODO Auto-generated method stub
//		
//	}

	
}
