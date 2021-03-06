package com.template.service;

import java.util.List;
import java.util.Map;
import com.template.domain.DicProvider;

/**
 * 供应商service
 * @Description: 提供供应商相关业务方法
 * @author army.liu
 */
public interface DicProviderService {
	
	/**
	  * 插入
	  * @Description: 插入
	  * @author army.liu
	  * @param  bean-供应商数据
	  * @return void
	  * @throws
	  */
	public void insert(DicProvider bean) throws Exception;
	
	/**
	 * 更新
	 * @Description: 更新
	 * @author army.liu
	 * @param  bean-供应商数据
	 * @return void
	 * @throws
	 */
	public void update(DicProvider bean) throws Exception;
	
	/**
	 * 保存:id是否为0，判断插入还是更新
	* @author  fengql 
	* @date 2016年4月13日 上午9:29:42 
	* @parameter  bean-供应商数据
	* @return
	 */
	public void save(DicProvider bean) throws Exception;
	
	/**
	 * 删除
	 * @Description: 删除供应商
	 * @author army.liu
	 * @param  departmentId-供应商编号
	 * @return void
	 * @throws
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 条件查询
	 * @Description: 根据传入参数进行条件查询
	 * @author army.liu
	 * @param  params-查询条件
	 * @return List<DicProvider>
	 * @throws
	 */
	public List<DicProvider> getByConditions(Map<String, Object> params) throws Exception;

	/**
	 * @param providerName 
	 * 获取已启用的供应商列表
	 * @Description: 查询供应商表，已启用的数据
	 * @author army.liu
	 * @param  providerName - 名称模糊查询
	 * @return List<DicProvider>
	 * @throws
	 */
	public List<DicProvider> getEnabledDicProviderList(String providerName) throws Exception;
	
	/**
	 * 获取供应商信息
	* @author  fengql 
	* @date 2016年4月13日 上午9:55:13 
	* @parameter  
	* @return
	 */
	public DicProvider getById(int id) throws Exception;
	
	/**
	 * 修改供应商启用状态
	* @author  fengql 
	* @date 2016年4月13日 上午10:00:18 
	* @parameter  
	* @return
	 */
	public void updateStatus(int id,int status) throws Exception;
}
