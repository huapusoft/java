package com.template.dao;

import com.template.domain.DicPy;

/**
 * 拼音dao
 * @author  army.liu
 * @date 2016年4月14日 上午9:25:14
 */
public interface DicPyMapper {

	public DicPy getByChn(String chn) throws Exception ;

	
}
