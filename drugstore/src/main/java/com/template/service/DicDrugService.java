package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDrug;

/**
 * 药品基础信息service
 * @Description: 提供药品基础信息相关业务方法
 * @author army.liu
 */
public interface DicDrugService {
	
	/**
	  * 保存
	  * @Description: 保存:id是否为0，判断插入还是更新
	  * @author army.liu
	  * @param  bean-药品基础信息数据
	  * @return void
	  * @throws
	  */
	public void save(DicDrug bean) throws Exception;
	
	/**
	 * 删除
	 * @Description: 删除药品基础信息
	 * @author army.liu
	 * @param  departmentId-药品基础信息编号
	 * @return void
	 * @throws
	 */
	public void delete(int departmentId) throws Exception;
	
	/**
	 * 条件查询
	 * @Description: 根据传入参数进行条件查询
	 * @author army.liu
	 * @param  params-查询条件
	 * @return List<DicDrug>
	 * @throws
	 */
	public List<DicDrug> getByConditions(Map<String, Object> params) throws Exception;

	/**
	 * 获取已启用的药品信息
	 * @Description: 根据传入参数，获取已启用的药品信息
	 * @author army.liu
	 * @param  itemName-药品名称简写
	 * @return List<DicDrug>
	 * @throws
	 */
	public List<DicDrug> getEnabledDrugList(String itemName);
	
	
}
