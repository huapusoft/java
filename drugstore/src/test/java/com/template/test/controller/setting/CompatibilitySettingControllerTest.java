package com.template.test.controller.setting;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicCompatibility;
import com.template.service.DicCompatibilityService;

/**
 * 系统设置：配伍禁忌controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class CompatibilitySettingControllerTest {
	
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
		DicCompatibilityService dicCompatibilityService = (DicCompatibilityService) context.getBean("dicCompatibilityService");
		
		DicCompatibility bean = new DicCompatibility();
		bean.setResult("ttt");
		dicCompatibilityService.save(bean);
		
	}
	
}

