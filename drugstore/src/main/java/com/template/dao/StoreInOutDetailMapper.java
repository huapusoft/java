package com.template.dao;

import com.template.domain.StoreInOutDetail;

/**
 * 出入库明细表Mapper
* @author  fengql 
* @date 2016年4月5日 上午10:46:50
 */
public interface StoreInOutDetailMapper {

	public void insert(StoreInOutDetail bean)  throws Exception;
	public void delete(int billNo)  throws Exception;
	public void getByBillNo(int billNo)  throws Exception;
}
