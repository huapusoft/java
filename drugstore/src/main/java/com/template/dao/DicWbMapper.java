package com.template.dao;

import com.template.domain.DicWb;

/**
 * 五笔dao
 * @author  army.liu
 * @date 2016年4月14日 上午9:25:14
 */
public interface DicWbMapper {

	public DicWb getByChn(String chn) throws Exception ;

	
}
