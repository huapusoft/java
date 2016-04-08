package com.template.service;

import java.util.List;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;

/**
 * 调价service
* @author  fengql 
* @date 2016年4月7日 下午1:44:56
 */
public interface AdjustPriceService {
	
	/**
	 * 保存调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午1:46:56
	* @parameter  inOut-药库出入库主表数据，detailList-药库出入库详细表数据，billOper-操作员，storeName-库房名称
	* @return void
	 */
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception;

	/**
	 * 提交
	* @author  fengql 
	* @date 2016年4月7日 下午1:48:56
	* @parameter  billNo-票据号
	* @return void
	 */
	public void submit(int billNo) throws Exception;
	
	/**
	 * 作废
	* @author  fengql 
	* @date 2016年4月7日 下午1:50:56
	* @parameter  billNo-票据号
	* @return void
	 */
	public void delete(int billNo) throws Exception;
	
	/**
	 * 根据输入内容获取药品及其相关内容
	* @author  fengql 
	* @date 2016年4月7日 下午2:30:09 
	* @parameter  
	* @return
	 */
	public List<DrugAndStore> getStoreDrugList(String itemName) throws Exception;
	
}