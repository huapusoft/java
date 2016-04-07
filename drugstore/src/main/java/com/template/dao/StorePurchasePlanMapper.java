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

	public StorePurchasePlan getById(int id) throws Exception ;

	public List<StorePurchasePlan> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(StorePurchasePlan bean)  throws Exception;
	
	public void update(StorePurchasePlan bean)  throws Exception;
	
	public void delete(int id)  throws Exception;
	
	public List<StorePurchasePlanForCount> getWaitingDataCount(Map<String, Object> params)  throws Exception;
	
}
