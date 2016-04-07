package com.template.test.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDrug;
import com.template.domain.DicProvider;
import com.template.service.DicDrugService;
import com.template.service.DicProviderService;
import com.template.service.InStorageService;

/**
 * InStorageService测试类
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
		
		List<DicProvider> list = dicProviderService.getEnabledDicProviderList("德");
		System.out.println( list.size());
		
	}
	
	@Test
	public void testgetEnabledDrugList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		InStorageService service = (InStorageService) context.getBean("inStorageService");
		DicDrugService dicDrugService = (DicDrugService) context.getBean("dicDrugService");
		
		List<DicDrug> list = dicDrugService.getEnabledDrugList("aa");
		System.out.println( list.size());
		
		list = dicDrugService.getEnabledDrugList("a");
		System.out.println( list.size());
		
	}
	
	@Test
	public void testgetDrugLatestPrice() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		Map<String, Object> data = inStorageService.getDrugLatestPrice( "一号仓库", 1000, "PCdsfsdfsd111");
		System.out.println(data);
	}
	
}

