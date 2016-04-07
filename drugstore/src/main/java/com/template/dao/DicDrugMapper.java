package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDrug;

/**
 * 药品基础信息dao
 * @Description: 提供药品基础信息操作方法
 * @author army.liu
 */
public interface DicDrugMapper {

	public DicDrug getById(int departmentId) throws Exception ;

	public List<DicDrug> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicDrug bean)  throws Exception;
	
	public void update(DicDrug bean)  throws Exception;
	
	public void delete(int departmentId)  throws Exception;
	
}