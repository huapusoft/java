package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.Notices;

/**
 * 系统公告dao
 * @Description: 提供系统公告dao操作方法
 * @author army.liu
 */
public interface NoticesMapper {

	public Notices getById(int id) throws Exception ;

	public List<Notices> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(Notices bean)  throws Exception;
	
	public void update(Notices bean)  throws Exception;
	
	public void delete(int id)  throws Exception;
	
}
