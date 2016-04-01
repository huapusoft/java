package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DictEmployee;

/**
 *
 */
public interface DicEmployeeService {
	/**
	 * 根据用户主键查询用户信息
	 * @param id
	 * @return
	 */
	public DictEmployee getById(String id);

	/**
	 * 条件查询
	 * @param params
	 * @return
	 */
	public List<DictEmployee> getByConditions(Map<String, Object> params);

	/**
	 * 事物测试
	 * @param name1
	 * @param name2
	 */
	public void test(String name1, String name2);

}
