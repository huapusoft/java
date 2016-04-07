package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicProvider;

/**
 * 供应商dao
 * @Description: 提供供应商操作方法
 * @author army.liu
 */
public interface DicProviderMapper {

	public DicProvider getById(int departmentId) throws Exception ;

	public List<DicProvider> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicProvider bean)  throws Exception;
	
	public void update(DicProvider bean)  throws Exception;
	
	public void delete(int departmentId)  throws Exception;
	
}
