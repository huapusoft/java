package com.template.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.template.dao.StoreMapper;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;
import com.template.service.StoreCheckService;

/**
 * 盘点serviceimpl
* @author  fengql 
* @date 2016年4月7日 下午4:59:13
 */
@Service("StoreCheckService")
public class StoreCheckServiceImpl implements StoreCheckService{
	
	@Resource
	private StoreMapper storeMapper;

	@Override
	public List<DrugAndStore> getStoreDrugList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return storeMapper.getDrugAndStoreDataList(params);
	}

	@Override
	public void save(StoreCheck checkmain, List<StoreCheckDetail> detailList,
			String billOper, String storeName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submit(int checkNo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int checkNo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
