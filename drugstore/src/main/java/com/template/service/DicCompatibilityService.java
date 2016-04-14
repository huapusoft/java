package com.template.service;

import java.util.List;
import java.util.Map;
import com.template.domain.DicCompatibility;
import com.template.domain.DicDrugClass;
import com.template.domain.DicDrugFunction;

/**
 * 配伍service
* @author  fengql 
* @date 2016年4月14日 上午10:09:44
 */
public interface DicCompatibilityService {
	
	/**
	 * 获取功能代码
	* @author  fengql 
	* @date 2016年4月14日 上午10:13:50 
	* @parameter  
	* @return
	 */
	public List<DicDrugFunction> getfunctionCode() throws Exception;
	
	/**
	 * 获取药类类别id
	* @author  fengql 
	* @date 2016年4月14日 上午10:14:06 
	* @parameter  
	* @return
	 */
	public List<DicDrugClass> getdrugClassId() throws Exception;
	
	/**
	 * 保存配伍-根据id判断是新增还是更新
	* @author  fengql 
	* @date 2016年4月14日 上午10:14:29 
	* @parameter  
	* @return
	 */
	public void save(DicCompatibility bean) throws Exception;
	
	/**
	 * 删除配伍
	* @author  fengql 
	* @date 2016年4月14日 上午10:14:49 
	* @parameter  
	* @return
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 条件查询
	* @author  fengql 
	* @date 2016年4月14日 上午10:15:01 
	* @parameter  
	* @return
	 */
	public List<DicCompatibility> getByConditions(Map<String, Object> params) throws Exception;
	
	/**
	 * 根据id获取配伍信息
	* @author  fengql 
	* @date 2016年4月14日 上午10:50:20 
	* @parameter  
	* @return
	 */
	public DicCompatibility getById(int id) throws Exception;
}
