package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicStoreClass;

/**
 * 库存分类dao
 * @Description: 提供库存分类操作方法
 * @author army.liu
 */
public interface DicStoreClassMapper {

	public DicStoreClass getById(int id) throws Exception;

	public List<DicStoreClass> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicStoreClass bean)  throws Exception;
	
	public void update(DicStoreClass bean)  throws Exception;
	
	public void delete(int id)  throws Exception;
	
}
