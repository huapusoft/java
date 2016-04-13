package com.template.test.controller.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicStoreClass;
import com.template.service.DicStoreClassService;

/**
 * 系统设置：库存分类controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class StoreClassControllerTest {

	/**
	  * 获取查询页面的列表数据
	  * @Description: 获取查询页面的列表数据
	  * @author army.liu
	  * @param  
	  * @return 
	  * @throws
	  */
	@Test
	public void testvalidateOldPassword() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicStoreClassService dicStoreClassService = (DicStoreClassService) context.getBean("dicStoreClassService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "化验11");
		
		List<DicStoreClass> list = dicStoreClassService.getByConditions(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 保存
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	@Test
	public void testsave() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicStoreClassService dicStoreClassService = (DicStoreClassService) context.getBean("dicStoreClassService");
		
		DicStoreClass bean = new DicStoreClass();
		bean.setName("测试分类1");
		dicStoreClassService.save(bean);
		
		bean.setId(1);
		bean.setName("测试分类111");
		dicStoreClassService.save(bean);
		
	}
	
	/**
	 * 删除
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	@Test
	public void testdelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicStoreClassService dicStoreClassService = (DicStoreClassService) context.getBean("dicStoreClassService");
		
		dicStoreClassService.delete(2);
		
	}
	
	/**
	 * 查询
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	@Test
	public void testgetDetailData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicStoreClassService dicStoreClassService = (DicStoreClassService) context.getBean("dicStoreClassService");
		
		DicStoreClass bean = dicStoreClassService.getById( 1 );
		System.out.println(bean.getId());
	}
	
}

