package com.template.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.dao.StoreInOutDetailMapper;
import com.template.domain.StoreInOutDetail;


public class StoreInOutDetailDaoTest {

	@Test
	public void testStoreInOutInsert() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreInOutDetailMapper storeInOutDaoDetail = (StoreInOutDetailMapper) context.getBean("storeInOutDetailMapper");
		
		//插入
		StoreInOutDetail bean = new StoreInOutDetail();
		bean.setBillNo(1000);
		bean.setOrderNo(1);
		bean.setDrugId(1001);
		bean.setInvoiceNo("123");
		bean.setBatchNo("1234");
		bean.setAmount(10);
		bean.setPrice1(1);
		bean.setPrice2(2);
		bean.setValidDate(new Date());
		
		storeInOutDaoDetail.insert(bean);
		
	}
}
