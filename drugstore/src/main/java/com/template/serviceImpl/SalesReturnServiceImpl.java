package com.template.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.StoreInOutDetailMapper;
import com.template.dao.StoreInOutMapper;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.SalesReturnService;
import com.template.util.Constants;

/**
 * 退货serviceimpl
 * @Description: 提供退货业务方法
 * @author army.liu
 */
@Service("salesReturnService")
public class SalesReturnServiceImpl implements SalesReturnService {
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Resource
	private CommonService commonService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception {
		return commonService.saveStoreInOut(inOut, detailList, billOper, storeName);
		
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifySuccess(int billNo, String verifyOper, String storeName) throws Exception {
		commonService.verifySuccess(billNo, verifyOper, storeName);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyFail(int billNo, String verifyOper) throws Exception {
		commonService.verifyFail(billNo, verifyOper);
		
	}

	@Override
	public List<StoreInOut> getListData(Map<String, Object> params)
			throws Exception {
		return commonService.getListData(Constants.BusinessType.SALES_RETURN, params);
	}

	@Override
	public StoreInOut getDetailData(int billNo) throws Exception {
		return commonService.getDetailData(billNo);
	}


	
}
