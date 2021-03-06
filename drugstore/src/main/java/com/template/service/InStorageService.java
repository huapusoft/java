package com.template.service;

import java.util.List;
import java.util.Map;

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
	public int save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception;

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

	/**
	  * 获取药品最近入库时的进价，零售价
	  * @Description: 先查询出入库明细表，如果没有查询库存表
	  * @author army.liu
	  * @param  storeName-药库名称
	  * 		id-药品编号
	  * 		batchNo-药品批号
	  * @return void
	  * @throws
	  */
	public Map<String, Object> getDrugLatestPrice(String storeName, int id)throws Exception;
	
	/**
	  * 复核通过
	  * @Description:  复核通过
	  * @author army.liu
	  * @param  billNo-票据号
	  * 		verifyOper-复核员
	  *         storeName-药库名称
	  * @return void
	  * @throws
	  */
	public void verifySuccess(int billNo, String verifyOper, String storeName) throws Exception;
	
	/**
	 * 复核驳回
	 * @Description:  复核驳回
	 * @author army.liu
	 * @param  billNo-票据号
	 * 		verifyOper-复核员
	 * @return void
	 * @throws
	 */
	public void verifyFail(int billNo, String verifyOper) throws Exception;
	
	/**
	  * 获取查询列表数据
	  * @Description: 根据参数，查询出入库表，统计入库记录
	  * @author army.liu
	  * @param  
	  * @return 
	  * @throws
	  */
	public List<StoreInOut> getListData(Map<String, Object> params) throws Exception;

	/**
	  * 获取入库登记详细信息
	  * @Description: 根据票据号，获取入库登记详细信息
	  * @author army.liu
	  * @param  billNo-票据号
	  * @return 
	  * @throws
	  */
	public StoreInOut getDetailData(int billNo) throws Exception;
}
