package com.template.service;

import java.util.List;

import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;

/**
 * 入库service
* @author  fengql 
* @date 2016年4月5日 上午10:47:40
 */
public interface InStorageService {
	
	/**
	 * 保存入库草稿
	* @author  fengql 
	* @date 2016年4月5日 上午10:48:32 
	* @parameter  inOut-药库出入库主表数据，detailList-药库出入库详细表数据，billOper-操作员，storeName-库房名称
	* @return void
	 */
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception;

	/**
	 * 提交
	* @author  fengql 
	* @date 2016年4月5日 上午10:51:55 
	* @parameter  billNo-票据号
	* @return void
	 */
	public void submit(int billNo) throws Exception;
	
	/**
	 * 作废
	* @author  fengql 
	* @date 2016年4月5日 上午11:32:44 
	* @parameter  billNo-票据号
	* @return void
	 */
	public void delete(int billNo) throws Exception;
	
}
