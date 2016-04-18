package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.DicCompatibilityDetail;

/**
 * 配伍明细表dao
* @author  fengql 
* @date 2016年4月14日 上午9:50:12
 */
public interface DicCompatibilityDetailMapper {

	public DicCompatibilityDetail getById(int id) throws Exception ;

	public List<DicCompatibilityDetail> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicCompatibilityDetail bean)  throws Exception;
	
	public void delete(int comId)  throws Exception;
	
}
