package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;

/**
 * 出库service
 * @Description: 提供出库业务方法
 * @author army.liu
 */
public interface OutStorageService {
	
	/**
	  * 保存草稿
	  * @Description: 保存出库草稿
	  * @author army.liu
	  * @param  inOut-药库出入库主表数据
	  * 		detail-药库出入库详细表数据
	  * 		billOper-操作员
	  * @return 票据号
	  * @throws
	  */
	public int save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName)  throws Exception;
	
	/**
	  * 提交草稿
	  * @Description: 提交出库
	  * @author army.liu
	  * @param  billNo-票据号
	  * @return void
	  * @throws
	  */
	public void submit(int billNo) throws Exception;
	
	/**
	 * 作废
	 * @Description: 作废草稿
	 * @author army.liu
	 * @param  billNo-票据号
	 * @return void
	 * @throws
	 */
	public void delete(int billNo) throws Exception;
	
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
	  * 获取出库登记详细信息
	  * @Description: 根据票据号，获取出库登记详细信息
	  * @author army.liu
	  * @param  billNo-票据号
	  * @return 
	  * @throws
	  */
	public StoreInOut getDetailData(int billNo) throws Exception;
	
}
