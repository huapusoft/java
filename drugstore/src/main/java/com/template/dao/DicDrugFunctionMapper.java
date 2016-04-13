package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDrugFunction;

/**
 * 功能代码dao
 * @Description: 提供功能代码操作方法
 * @author army.liu
 */
public interface DicDrugFunctionMapper {

	public DicDrugFunction getById(int id) throws Exception ;

	public List<DicDrugFunction> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicDrugFunction bean)  throws Exception;
	
	public void update(DicDrugFunction bean)  throws Exception;
	
	public void delete(int departmentId)  throws Exception;
	
}
