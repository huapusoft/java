package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.DicDrugClass;

/**
 * 药品类别dao
* @author  fengql 
* @date 2016年4月14日 上午9:25:14
 */
public interface DicDrugClassMapper {

	public DicDrugClass getById(int id) throws Exception ;

	public List<DicDrugClass> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(DicDrugClass bean)  throws Exception;
	
	public void update(DicDrugClass bean)  throws Exception;
	
	public void delete(int id)  throws Exception;
	
}
