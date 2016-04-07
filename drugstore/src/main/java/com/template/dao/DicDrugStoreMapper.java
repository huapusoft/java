package com.template.dao;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDrugStore;

/**
 * 药库dao
 * @Description: 提供药库dao操作方法
 * @author army.liu
 */
public interface DicDrugStoreMapper {

	public DicDrugStore getById(int id) throws Exception ;

	public List<DicDrugStore> getByConditions(Map<String, Object> params)  throws Exception;

}
