package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DictEmployee;

/**
 * 
 */
public interface DicEmployeeMapper {

	public DictEmployee getById(String id) throws Exception ;

	public List<DictEmployee> getByConditions(Map<String, Object> params)  throws Exception;

	public void add(DictEmployee bean)  throws Exception;
}
