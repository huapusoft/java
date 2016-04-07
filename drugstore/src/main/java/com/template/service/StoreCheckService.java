package com.template.service;

import java.util.List;

import com.template.domain.DrugAndStore;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;

/**
 * 药库盘点service
* @author  fengql 
* @date 2016年4月7日 下午4:15:42
 */
public interface StoreCheckService {
	
	public List<DrugAndStore> getStoreDrugList() throws Exception;
	
	/**
	 * 保存盘点草稿
	* @author  fengql 
	* @date 2016年4月7日 下午4:26:48 
	* @parameter   checkmain-盘点主表数据，detailList-盘点明细表数据，billOper-操作员，storeName-库房名称
	* @return void
	 */
	public void save(StoreCheck checkmain, List<StoreCheckDetail> detailList, String billOper, String storeName) throws Exception;

	/**
	 * 封存盘点数据
	* @author  fengql 
	* @date 2016年4月7日 下午4:30:48 
	* @parameter  checkNo-盘点号
	* @return
	 */
	public void submit(int checkNo) throws Exception;

	/**
	 * 取消盘点单
	* @author  fengql 
	* @date 2016年4月7日 下午4:33:30 
	* @parameter  checkNo-盘点号
	* @return
	 */
	public void delete(int checkNo) throws Exception;
	
	
}
