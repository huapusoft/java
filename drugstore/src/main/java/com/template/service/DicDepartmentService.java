package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.DicDepartment;

/**
 * 部门service
 * @Description: 提供部门相关业务方法
 * @author army.liu
 */
public interface DicDepartmentService {
	
	/**
	  * 保存
	  * @Description: 保存:departmentId是否为0，判断插入还是更新
	  * @author army.liu
	  * @param  bean-部门数据
	  * @return void
	  * @throws
	  */
	public void save(DicDepartment bean) throws Exception;
	
	/**
	 * 删除
	 * @Description: 删除部门，若存在子部门，提示不可以直接删除
	 * @author army.liu
	 * @param  departmentId-部门编号
	 * @return void
	 * @throws
	 */
	public void delete(int departmentId) throws Exception;
	
	/**
	 * 条件查询
	 * @Description: 根据传入参数进行条件查询
	 * @author army.liu
	 * @param  params-查询条件
	 * @return List<DicDepartment>
	 * @throws
	 */
	public List<DicDepartment> getByConditions(Map<String, Object> params) throws Exception;
	
	
}
