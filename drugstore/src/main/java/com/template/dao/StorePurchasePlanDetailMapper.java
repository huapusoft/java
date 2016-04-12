package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.DrugAndPurchasePlanDetail;
import com.template.domain.StorePurchasePlanDetail;

/**
 * 采购计划明细表dao
* @author  fengql 
* @date 2016年4月11日 上午9:25:41
 */
public interface StorePurchasePlanDetailMapper {

	public StorePurchasePlanDetail getByPurchaseNo(int purchaseNo) throws Exception ;

	public List<StorePurchasePlanDetail> getByConditions(Map<String, Object> params)  throws Exception;

	public void insert(StorePurchasePlanDetail bean)  throws Exception;
	
	public void delete(int purchaseNo)  throws Exception;
	
	public List<DrugAndPurchasePlanDetail> getPurchaseDetailList(int purchaseNo) throws Exception ;
	
}
