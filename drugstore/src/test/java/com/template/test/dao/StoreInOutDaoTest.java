package com.template.test.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.dao.StoreInOutMapper;
import com.template.domain.StoreInOut;


public class StoreInOutDaoTest {

	@Test
	public void testInsert() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreInOutMapper storeInOutDao = (StoreInOutMapper) context.getBean("storeInOutMapper");
		
		//插入
		StoreInOut bean = new StoreInOut();
		bean.setBillNo(1000);
		bean.setBillOper("张三");
		bean.setBillTime(new Date());
		bean.setBillType("出库");
		bean.setStatus("0");
		bean.setStoreName("三院库1");
		bean.setSum1(12.123);
		bean.setSum2(12.456);
		bean.setTypeData("盐城第一制药厂");
		storeInOutDao.insert(bean);
		
	}
	
	@Test
	public void testGetById() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreInOutMapper storeInOutDao = (StoreInOutMapper) context.getBean("storeInOutMapper");
		
		StoreInOut bean = storeInOutDao.getById(1000);
		System.out.println(bean.getStoreName());
		
	}
	
	@Test
	public void testUpdate() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreInOutMapper storeInOutDao = (StoreInOutMapper) context.getBean("storeInOutMapper");
		
		StoreInOut bean = storeInOutDao.getById(2016041000);
		System.out.println(bean.getStoreName());
		bean.setStatus("1");
		bean.setSubmitTime(new Date());
		storeInOutDao.update(bean);
		
		bean = storeInOutDao.getById(2016041000);
		Assert.assertEquals(bean.getStatus(), "1");
		
	}
	
	
}
