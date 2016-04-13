package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DrugAndStore;
import com.template.domain.Store;

/**
 * 库存service
 * @Description: 提供库存相关业务方法
 * @author army.liu
 */
public interface StoreService {

	/**
	 * 条件查询
	 * @param params
	 * @return
	 */
	public List<Store> getByConditions(Map<String, Object> params) throws Exception;

	/**
	 * 复核通过
	 * @Description: 复核通过时，执行库存表的更新操作
	 * @author army.liu
	 * @param  billNo-票据号
	 * 		currStoreName-药库名称
	 * @return void
	 * @throws
	 */
	public void verifySuccess(int billNo, String currStoreName) throws Exception;

	/**
	 * 获取库存药品设置列表数据
	 * @Description: 获取库存药品设置列表数据
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	public List<DrugAndStore> getByConditionsForQuery(Map<String, Object> params)throws Exception;
	
}
