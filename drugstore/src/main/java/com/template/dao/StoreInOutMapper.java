package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DrugAndReports;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutForCount;

/**
 * 药库出入库dao
 * @Description: 提供出入库dao操作方法
 * @author army.liu
 */
public interface StoreInOutMapper {

	public StoreInOut getById(int billNo) throws Exception ;

	public List<StoreInOut> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(StoreInOut bean)  throws Exception;
	
	public void update(StoreInOut bean)  throws Exception;
	
	public void delete(int billNo)  throws Exception;

	public List<StoreInOutForCount> getWaitingDataCount(Map<String, Object> params)  throws Exception;
	
	/**
	  * 获取入库查询页面的列表数据
	  * @Description: 获取入库查询页面的列表数据
	  * @author army.liu
	  * @param  
	  * @return 
	  * @throws
	  */
	public List<StoreInOut> getByConditionsForQuery(Map<String, Object> params)  throws Exception;

	/**
	 * 获取药品台账的列表数据
	 * @Description: 根据参数，获取药品台账数据
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	public List<DrugAndReports> getListDataForDrugReports( Map<String, Object> params );
}
