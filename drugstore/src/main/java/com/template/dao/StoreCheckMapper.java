package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.StoreCheck;

/**
 * 药库盘点dao
 * @Description: 提供药库盘点dao操作方法
 * @author army.liu
 */
public interface StoreCheckMapper {

	public StoreCheck getById(int id) throws Exception ;

	public List<StoreCheck> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(StoreCheck bean)  throws Exception;
	
	public void update(StoreCheck bean)  throws Exception;
	
	public void delete(int id)  throws Exception;
	
}
