package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DicStoreClass;

/**
 * 库存分类service
 * @Description: 提供库存分类相关业务方法
 * @author army.liu
 */
public interface DicStoreClassService {
	
	/**
	  * 保存
	  * @Description: 保存:id是否为0，判断插入还是更新
	  * @author army.liu
	  * @param  bean-数据
	  * @return void
	  * @throws
	  */
	public void save(DicStoreClass bean) throws Exception;
	
	/**
	 * 删除
	 * @Description: 删除
	 * @author army.liu
	 * @param  id-编号
	 * @return void
	 * @throws
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 条件查询
	 * @Description: 根据传入参数进行条件查询
	 * @author army.liu
	 * @param  params-查询条件
	 * @return List<DicStoreClass>
	 * @throws
	 */
	public List<DicStoreClass> getByConditions(Map<String, Object> params) throws Exception;

	/**
	 * 主键查询
	 * @Description: 主键查询
	 * @author army.liu
	 * @param  id-编号
	 * @return void
	 * @throws
	 */
	public DicStoreClass getById(int id) throws Exception;

}
