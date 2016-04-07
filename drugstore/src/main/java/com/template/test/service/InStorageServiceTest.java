package com.template.test.service;

import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicProvider;
import com.template.service.DicProviderService;
//import com.template.service.InStorageService;

/**
 * commonservice测试类
 * @Description: 测试
 * @author army.liu
 */
public class InStorageServiceTest {

	/**
	 * 获取供应商列表
	 */
	@Test
	public void testgetEnabledDicProviderList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		InStorageService service = (InStorageService) context.getBean("inStorageService");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		List<DicProvider> list = dicProviderService.getEnabledDicProviderList("a");
		System.out.println( list.size());
		
	}
	
//	@Test
//	public void testgetEnabledDicProviderList() throws Exception{
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
////		InStorageService service = (InStorageService) context.getBean("inStorageService");
//		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
//		
//		List<DicProvider> list = dicProviderService.getEnabledDicProviderList();
//		System.out.println( list.size());
//		
//	}
	
}

