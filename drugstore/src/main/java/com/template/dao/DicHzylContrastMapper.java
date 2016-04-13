package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicHzylContrast;

/**
 * 合作医疗dao
 * @Description: 提供合作医疗操作方法
 * @author army.liu
 */
public interface DicHzylContrastMapper {

	public List<DicHzylContrast> getByConditions(Map<String, Object> params)  throws Exception;
	
}
