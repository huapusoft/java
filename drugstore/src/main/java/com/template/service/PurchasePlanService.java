package com.template.service;

import java.util.List;
import java.util.Map;
import com.template.domain.StorePurchasePlan;
import com.template.domain.StorePurchasePlanDetail;

/**
 * 采购计划service
* @author  fengql 
* @date 2016年4月11日 上午9:36:58
 */
public interface PurchasePlanService {
	
	/**
	 * 保存采购计划草稿
	* @author  fengql 
	* @date 2016年4月11日 上午10:21:55 
	* @parameter  purchaseData-采购计划主表数据，detailList-采购计划明细表数据，oper-操作员，storeName-库房名称
	* @return
	 */
	public void save(StorePurchasePlan purchaseData, List<StorePurchasePlanDetail> detailList, String oper, String storeName) throws Exception;

	/**
	 * 提交采购计划
	* @author  fengql 
	* @date 2016年4月11日 上午11:11:57 
	* @parameter  purchaseNo-采购号
	* @return
	 */
	public void submit(int purchaseNo) throws Exception;
	
	/**
	 * 作废采购计划
	* @author  fengql 
	* @date 2016年4月11日 上午11:12:16 
	* @parameter  purchaseNo-采购号
	* @return
	 */
	public void delete(int purchaseNo) throws Exception;
	
	/**
	 * 财务审核
	* @author  fengql 
	* @date 2016年4月11日 下午1:32:44 
	* @parameter  purchaseNo-采购号，oper-操作员，auditType-1审核通过，0审核不通过(驳回)
	* @return
	 */
	public void financeAudit(int purchaseNo, String oper, int auditType) throws Exception;
	
	/**
	 * 领导审核
	* @author  fengql 
	* @date 2016年4月11日 下午1:35:44 
	* @parameter  purchaseNo-采购号，oper-操作员，auditType-1审核通过，0审核不通过(驳回)
	* @return
	 */
	public void leaderAudit(int purchaseNo, String oper, int auditType) throws Exception;
	
	/**
	 * 获取查询列表数据
	* @author  fengql 
	* @date 2016年4月11日 下午5:05:01 
	* @parameter  
	* @return
	 */
	public List<StorePurchasePlan> getListData(Map<String, Object> params) throws Exception;
	
	/**
	 * 获取采购计划草稿的详细数据
	* @author  fengql 
	* @date 2016年4月11日 下午5:05:17 
	* @parameter  purchaseNo-采购号
	* @return
	 */
	public StorePurchasePlan getDetailData(int purchaseNo) throws Exception;
}
