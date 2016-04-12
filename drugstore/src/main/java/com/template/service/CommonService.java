package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDrugStore;
import com.template.domain.DrugAndReports;
import com.template.domain.DrugAndStore;
import com.template.domain.Store;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;

/**
 * 公共service
 * @Description: 提供公共业务方法
 * @author army.liu
 */
public interface CommonService {
	
	/**
	  * 测试
	  * @Description: 测试功能
	  * @author army.liu
	  * @param  
	  * @return void
	  * @throws
	  */
	public void test();

	/**
	  * 生成最新票据号
	  * @Description: 使用当前年月，匹配出入库主表中已存在的票据号，生成最新票据号，如果没有，则默认从1000开始
	  * @author army.liu
	  * @param  
	  * @return int-票据号，10位，yyyyMMxxxx
	  * @throws
	  */
	public int createBillNo() throws Exception;
	
	/**
	 * 生成最新盘点号
	* @author  fengql 
	* @date 2016年4月8日 下午1:55:30 
	* @parameter  
	* @return int-盘点号，10位，yyyyMMxxxx
	 */
	public int createCheckNo() throws Exception;
	
	/**
	 * 生成最新的采购号
	* @author  fengql 
	* @date 2016年4月11日 上午10:34:08 
	* @parameter  
	* @return int-采购号，10位，yyyyMMxxxx
	 */
	public int createPurchaseNo() throws Exception;
	
	/**
	  * 获取所有药库信息
	  * @Description: 获取所有药库信息
	  * @author army.liu
	  * @param  
	  * @return 数组，药库数组
	  * @throws
	  */
	public List<DicDrugStore> getAllDicDrugStore() throws Exception;

	/**
	  * 获取最近一次登录的用户名称
	  * @Description: 从当前电脑的注册表中获取，最近一次登录的用户名称
	  * @author army.liu
	  * @param  
	  * @return 字符串，用户名称
	  * @throws
	  */
	public String getLatestLoginUserName() throws Exception;

	/**
	  * 获取未完成事项的统计数据
	  * @Description: 读取药库出入库表，统计当前用户的各项业务的未完成情况
	  * @author army.liu
	  * @param  name -操作员名称
	  * @return 字符串，用户名称
	  * @throws
	  */
	public Map<String, Object> getWaitingDataCount(String name);

	/**
	  * 获取将要缺货的药品列表
	  * @Description: 读取药库库存表，查询达到下限的药品
	  * @author army.liu
	  * @param  name -操作员名称
	  * @return 字符串，用户名称
	  * @throws
	  */
	public List<Store> getDrugsForStockout();

	/**
	  * 获取达到有效期设置的药品列表
	  * @Description: 读取药库库存表，查询达到有效期设置的药品
	  * @author army.liu
	  * @param  name -操作员名称
	  * @return 字符串，用户名称
	  * @throws
	  */
	public List<Store> getDrugsForDeadline();

	/**
	  * 校验药库名称
	  * @Description: 读取药库表，查询该药库名称是否存在
	  * @author army.liu
	  * @param  name -药库名称
	  * @return true-存在，false-不存在
	  * @throws
	  */
	public boolean validateStoreName(String storeName);
	
	/**
	  * 保存出入库草稿
	  * @Description: 保存出入库草稿
	  * @author army.liu
	  * @param  inOut-药库出入库主表数据
	  * 		detail-药库出入库详细表数据
	  * 		billOper-操作员
	  * @return void
	  * @throws
	  */
	public void saveStoreInOut(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName)  throws Exception;
	
	/**
	  * 提交出入库草稿
	  * @Description: 提交出入库草稿
	  * @author army.liu
	  * @param  billNo-票据号
	  * @return void
	  * @throws
	  */
	public void submitStoreInOut(int billNo) throws Exception;
	
	/**
	 * 作废出入库草稿
	 * @Description: 作废出入库草稿
	 * @author army.liu
	 * @param  billNo-票据号
	 * @return void
	 * @throws
	 */
	public void deleteStoreInOut(int billNo) throws Exception;
	
	/**
	 * 复核通过出入库
	 * @Description: 复核通过出入库
	 * @author army.liu
	 * @param  billNo-票据号
	 * @return void
	 * @throws
	 */
	public void verifySuccess(int billNo, String verifyOper, String storeName) throws Exception;
	
	/**
	 * 复核驳回出入库
	 * @Description: 作废出入库草稿
	 * @author army.liu
	 * @param  billNo-票据号
	 * @return void
	 * @throws
	 */
	public void verifyFail(int billNo, String verifyOper) throws Exception;

	/**
	 * 获取药库的用户数据
	 * @Description: 读取用户表，获取当前药库下的用户名称
	 * @author army.liu
	 * @param  storeName-药库名称
	 * @return void
	 * @throws
	 */
	public List<String> getDicEmployeeBySotreName(String storeName) throws Exception;

	/**
	 * 获取出库时的药品下拉选择列表
	 * @Description: 读取库存表，关联药品基础信息表（已启用）
	 * @author army.liu
	 * @param  itemName-药品搜索名称
	 * 		   storeName-药库名称
	 * @return void
	 * @throws
	 */
	public List<DrugAndStore> getDrugListForOutStorage(String storeName, String itemName) throws Exception;
	
	/**
	  * 获取查询列表数据
	  * @Description: 根据参数，查询出入库表，统计入库记录
	  * @author army.liu
	  * @param  
	  * @return 
	  * @throws
	  */
	public List<StoreInOut> getListData(String billType, Map<String, Object> params) throws Exception;

	/**
	  * 获取入库登记详细信息
	  * @Description: 根据票据号，获取入库登记详细信息
	  * @author army.liu
	  * @param  billNo-票据号
	  * @return 
	  * @throws
	  */
	public StoreInOut getDetailData(int billNo) throws Exception;
	
	/**
	 * 获取药品台账的列表数据
	 * @Description: 根据参数，获取药品台账数据
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	public List<DrugAndReports> getListDataForDrugReports(Map<String, Object> params) throws Exception;
	
	/**
	 * 获取批号-根据药品id
	* @author  fengql 
	* @date 2016年4月12日 下午1:00:10 
	* @parameter  storeName-药库名称，drugId-药品id
	* @return
	 */
	public List<Store> getBatchNoFromStore(String storeName, int drugId) throws Exception;
	
	/**
	 * 获取批号-根据药品id
	* @author  fengql 
	* @date 2016年4月12日 下午1:00:34 
	* @parameter  storeName-药库名称，drugId-药品id
	* @return
	 */
	public List<StoreInOutDetail> getBatchNoFromInOutDetail(String storeName, int drugId) throws Exception;
	
}
