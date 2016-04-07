package com.template.service;

import java.util.List;
import java.util.Map;

import com.template.domain.Notices;

/**
 * 系统公告service
 * @Description: 提供系统公告相关业务方法
 * @author army.liu
 */
public interface NoticesService {
	
	/**
	  * 插入
	  * @Description: 插入
	  * @author army.liu
	  * @param  bean-数据
	  * @return void
	  * @throws
	  */
	public void insert(Notices bean) throws Exception;
	
	/**
	 * 更新
	 * @Description: 更新
	 * @author army.liu
	 * @param  bean-数据
	 * @return void
	 * @throws
	 */
	public void update(Notices bean) throws Exception;
	
	/**
	 * 删除
	 * @Description: 删除
	 * @author army.liu
	 * @param  id-编号
	 * @return void
	 * @throws
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 条件查询
	 * @Description: 根据传入参数进行条件查询
	 * @author army.liu
	 * @param  params-查询条件
	 * @return List<Notices>
	 * @throws
	 */
	public List<Notices> getByConditions(Map<String, Object> params) throws Exception;

	/**
	 * 获取未到期的所有公告
	 * @Description: 获取未到期的所有公告
	 * @author army.liu
	 * @param  params-查询条件
	 * @return List<Notices>
	 * @throws
	 */
	public List<Notices> getEnabledNotices() throws Exception;
	
}
