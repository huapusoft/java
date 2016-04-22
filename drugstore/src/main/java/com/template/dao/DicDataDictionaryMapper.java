package com.template.dao;

import java.util.List;
import java.util.Map;
import com.template.domain.DicDataDictionary;

/**
 * 数据字典dao 
* @author  fengql 
* @date 2016年4月22日 下午2:34:57
 */
public interface DicDataDictionaryMapper {

	public List<DicDataDictionary> getByConditions(Map<String, Object> params)  throws Exception;
	
}
