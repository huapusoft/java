package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.DicCompatibility;

/**
 * 配伍主表dao
* @author  fengql 
* @date 2016年4月14日 上午9:50:12
 */
public interface DicCompatibilityMapper {

	public DicCompatibility getById(int id) throws Exception ;

	public List<DicCompatibility> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicCompatibility bean)  throws Exception;
	
	public void update(DicCompatibility bean)  throws Exception;
	
	public void delete(int id)  throws Exception;
	
}
