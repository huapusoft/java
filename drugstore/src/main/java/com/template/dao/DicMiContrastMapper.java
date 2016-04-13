package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicMiContrast;

/**
 * 医保编码dao
 * @Description: 提供医保编码操作方法
 * @author army.liu
 */
public interface DicMiContrastMapper {

	public List<DicMiContrast> getByConditions(Map<String, Object> params)  throws Exception;
	
}
