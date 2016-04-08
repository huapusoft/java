package com.template.service;

import java.util.List;

import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;

/**
 * 退货service
 * @Description: 提供退货业务方法
 * @author army.liu
 */
public interface SalesReturnService {
	
	/**
	  * 保存草稿
	  * @Description: 保存草稿
	  * @author army.liu
	  * @param  inOut-药库出入库主表数据
	  * 		detail-药库出入库详细表数据
	  * 		billOper-操作员
	  * @return void
	  * @throws
	  */
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName)  throws Exception;
	
	/**
	  * 提交草稿
	  * @Description: 提交退货
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
	
//	/**
//	  * 复核
//	  * @Description: 复核退货
//	  * @author army.liu
//	  * @param  billNo-票据号
//	  * 		verifyOper-复核员
//	  * @return void
//	  * @throws
//	  */
//	public void verify(Integer billNo, String verifyOper);

}