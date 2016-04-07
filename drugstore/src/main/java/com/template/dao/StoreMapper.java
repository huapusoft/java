package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.Store;

/**
 * 药库库存dao
 * @Description: 提供药库库存dao操作方法
 * @author army.liu
 */
public interface StoreMapper {

	public Store getById(int id) throws Exception ;

	public List<Store> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(Store bean)  throws Exception;
	
	public void update(Store bean)  throws Exception;
	
	public void delete(int id)  throws Exception;

	public Store getDrugLatestPrice(Map<String, Object> params);
	
}
