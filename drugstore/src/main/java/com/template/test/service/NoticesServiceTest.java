package com.template.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.Notices;
import com.template.service.NoticesService;

/**
 * noticesservice测试类
 * @Description: 测试
 * @author army.liu
 */
public class NoticesServiceTest {

	@Test
	public void testgetEnabledNotices() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		NoticesService noticesService = (NoticesService) context.getBean("noticesService");
		
		List<Notices> list = noticesService.getEnabledNotices();
		System.out.println(list.size());
		
	}
	
}
