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
 * 入库controller测试类
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
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		List<DicProvider> list = dicProviderService.getEnabledDicProviderList("德");
		System.out.println( list.size());
		
	}
	
	/**
	 * 获取药品下拉列表
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:55:46
	 */
	@Test
	public void testgetEnabledDrugList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDrugService dicDrugService = (DicDrugService) context.getBean("dicDrugService");
		
		List<DicDrug> list = dicDrugService.getEnabledDrugList("aa");
		System.out.println( list.size());
		
		list = dicDrugService.getEnabledDrugList("a");
		System.out.println( list.size());
		
	}
	
	/**
	 * 获取药品最新进价，零售价
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testgetDrugLatestPrice() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		Map<String, Object> data = inStorageService.getDrugLatestPrice( "一号仓库", 1000);
		System.out.println(data);
	}
	
}

