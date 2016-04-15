package com.template.service;

import java.util.List;
import java.util.Map;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;

/**
 * 药库盘点service
* @author  fengql 
* @date 2016年4月7日 下午4:15:42
 */
public interface StoreCheckService {
	
	/**
	 * 打开盘点单
	* @author  fengql 
	* @date 2016年4月8日 上午10:13:46 
	* @parameter  
	* @return
	 */
	//public List<DrugAndCheckDetail> getCheckDetailList(int checkNo) throws Exception;
	
	/**
	 * 打开盘点单-获取信息
	* @author  fengql 
	* @date 2016年4月15日 上午9:24:18 
	* @parameter  
	* @return
	 */
	public StoreCheck getDetailData(int checkNo) throws Exception;
	
	/**
	 * 保存盘点草稿
	* @author  fengql 
	* @date 2016年4月7日 下午4:26:48 
	* @parameter   checkData-盘点主表数据，detailList-盘点明细表数据，checkOper-操作员，storeName-库房名称
	* @return void
	 */
	public int save(StoreCheck checkData, List<StoreCheckDetail> detailList, String checkOper, String storeName) throws Exception;

	/**
	 * 封存盘点数据
	* @author  fengql 
	* @date 2016年4月7日 下午4:30:48 
	* @parameter   checkData-盘点主表数据，detailList-盘点明细表数据，checkOper-操作员，storeName-库房名称
	* @return
	 */
	public int submit(StoreCheck checkData, List<StoreCheckDetail> detailList, String checkOper, String storeName) throws Exception;

	/**
	 * 作废盘点单
	* @author  fengql 
	* @date 2016年4月7日 下午4:33:30 
	* @parameter  checkNo-盘点号
	* @return
	 */
	public void delete(int checkNo) throws Exception;
	
	/**
	 * 获取盘点列表数据
	* @author  fengql 
	* @date 2016年4月11日 下午4:03:10 
	* @parameter  
	* @return
	 */
	public List<StoreCheck> getListData(Map<String, Object> params) throws Exception;
}
