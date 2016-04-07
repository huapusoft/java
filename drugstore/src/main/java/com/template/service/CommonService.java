package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDrugStore;
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
}
