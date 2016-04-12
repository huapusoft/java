package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.StorePurchasePlan;
import com.template.domain.StorePurchasePlanForCount;

/**
 * 药库采购计划dao
 * @Description: 提供药库采购计划dao操作方法
 * @author army.liu
 */
public interface StorePurchasePlanMapper {

	public StorePurchasePlan getByPurchaseNo(int purchaseNo) throws Exception ;

	public List<StorePurchasePlan> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(StorePurchasePlan bean)  throws Exception;
	
	public void update(StorePurchasePlan bean)  throws Exception;
	
	public void delete(int purchaseNo)  throws Exception;
	
	public List<StorePurchasePlanForCount> getWaitingDataCount(Map<String, Object> params)  throws Exception;
	
	/**
	 * 获取采购计划查询数据
	* @author  fengql 
	* @date 2016年4月11日 下午5:22:44 
	* @parameter  
	* @return
	 */
	public List<StorePurchasePlan> getByConditionsForQuery(Map<String, Object> params)  throws Exception;
	
}
