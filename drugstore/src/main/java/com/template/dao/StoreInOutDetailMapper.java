package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DrugAndInOutStatistics;
import com.template.domain.DrugAndStoreInOutDetail;
import com.template.domain.StoreInOutDetail;

/**
 * 出入库明细表Mapper
* @author  fengql 
* @date 2016年4月5日 上午10:46:50
 */
public interface StoreInOutDetailMapper {

	public void insert(StoreInOutDetail bean)  throws Exception;
	
	public void delete(int billNo)  throws Exception;
	
	public List<DrugAndStoreInOutDetail> getByBillNo(int billNo)  throws Exception;
	
	public List<StoreInOutDetail> getByConditions(Map<String, Object> params) throws Exception;

	public StoreInOutDetail getDrugLatestPrice(Map<String, Object> params) throws Exception;
	
	public List<DrugAndStoreInOutDetail> getListByConditions(Map<String, Object> params)  throws Exception;
	
	public List<DrugAndInOutStatistics> getInOutStatisticsListData(Map<String, Object> params)  throws Exception;
	
}
