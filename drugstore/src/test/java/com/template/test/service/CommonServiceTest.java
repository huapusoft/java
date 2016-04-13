package com.template.test.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDrugStore;
import com.template.domain.Store;
import com.template.service.CommonService;

/**
 * commonservice测试类
 * @Description: 测试
 * @author army.liu
 */
public class CommonServiceTest {

	@Test
	public void testCreateBillNo() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		int createBillNo = commonService.createBillNo();
		System.out.println(createBillNo);
		
	}
	
	@Test
	public void testGetall() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		List<DicDrugStore> list = commonService.getAllDicDrugStore();
		System.out.println(list.size());
	}
	
	@Test
	public void testvalidateStoreName() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		boolean flag= commonService.validateStoreName("111");
		Assert.assertEquals(false, flag);
		flag= commonService.validateStoreName("一号仓库");
		Assert.assertEquals(true, flag);
	}
	
	@Test
	public void testgetWaitingDataCount() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		Map<String, Object> data = commonService.getWaitingDataCount("张三");
		System.out.println(data);
	}
	
	@Test
	public void testgetDrugsForStockout() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		List<Store> list = commonService.getDrugsForStockout();
		System.out.println(list.size()+"-"+list.get(0).getBatchNo());
	}
	
	@Test
	public void testgetDrugsForDeadline() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		List<Store> list = commonService.getDrugsForDeadline();
		System.out.println(list.size()+"-"+list.get(0).getBatchNo());
	}
	
}

