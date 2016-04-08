package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.DrugAndCheckDetail;
import com.template.domain.StoreCheckDetail;

/**
 * 盘点明细dao
* @author  fengql 
* @date 2016年4月8日 上午9:34:31
 */
public interface StoreCheckDetailMapper {
	
	public StoreCheckDetail getByCheckNo(int checkNo) throws Exception ;

	public List<DrugAndCheckDetail> getCheckDetailList(Map<String, Object> params)  throws Exception;
	
	public void insert(StoreCheckDetail bean)  throws Exception;
	
	public void delete(int checkNo)  throws Exception;

	//public void insert(StoreCheck bean)  throws Exception;
	
	//public void update(StoreCheck bean)  throws Exception;
	
	
	
}
