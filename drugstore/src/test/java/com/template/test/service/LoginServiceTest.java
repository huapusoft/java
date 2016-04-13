package com.template.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.service.CommonService;

/**
 * Login测试类
 * @Description: 测试
 * @author army.liu
 */
public class LoginServiceTest {

	@Test
	public void testgetDicEmployeeBySotreName() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		List<String> list = commonService.getDicEmployeeBySotreName("一号仓库");
		System.out.println(list.size());
		
	}
	
}

