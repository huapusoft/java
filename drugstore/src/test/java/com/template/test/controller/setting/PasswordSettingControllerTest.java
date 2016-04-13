package com.template.test.controller.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DictEmployee;
import com.template.service.DicEmployeeService;

/**
 * 查询统计：修改密码controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class PasswordSettingControllerTest {

	/**
	 * 校验原密码是否正确
	 * @Description: 校验原密码是否正确
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testvalidateOldPassword() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicEmployeeService dicEmployeeService = (DicEmployeeService) context.getBean("dicEmployeeService");
		
		String name = "李四";
		String password = "123123";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("password", password);
		List<DictEmployee> list = dicEmployeeService.getByConditions(params);
		if( null != list && list.size() > 0 ){
			System.out.println(list.size());
			
		}
		
		
	}
	
	/**
	  * 保存
	  * @Description: 保存
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@Test
	public void testupdate() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicEmployeeService dicEmployeeService = (DicEmployeeService) context.getBean("dicEmployeeService");
		
		String name = "李四";
		String oldPassword = "123123";
		String password = "123456";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("password", oldPassword);
		List<DictEmployee> list = dicEmployeeService.getByConditions(params);
		if( null != list && list.size() > 0 ){
			DictEmployee bean = list.get(0);
			bean.setPassword(password);
			dicEmployeeService.update( bean );
			
		}
		
	}
	
}

