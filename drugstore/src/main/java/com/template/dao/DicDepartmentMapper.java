package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDepartment;

/**
 * 部门dao
 * @Description: 提供部门操作方法
 * @author army.liu
 */
public interface DicDepartmentMapper {

	public DicDepartment getById(int departmentId) throws Exception ;

	public List<DicDepartment> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicDepartment bean)  throws Exception;
	
	public void update(DicDepartment bean)  throws Exception;
	
	public void delete(int departmentId)  throws Exception;
	
}
