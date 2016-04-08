package com.template.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDepartment;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.DicDepartmentService;
import com.template.service.OutStorageService;

/**
 * 出库controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class OutStorageControllerTest {

	/**
	 * 获取部门列表
	 */
	@Test
	public void testgetAllDicDepartmentList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDepartmentService dicDepartmentService = (DicDepartmentService) context.getBean("dicDepartmentService");
		
		List<DicDepartment> list = dicDepartmentService.getAllDicDepartmentList();
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
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		List<DrugAndStore> list = commonService.getDrugListForOutStorage("一号仓库");
		System.out.println( list.size());
		
	}
	
	/**
	  * 保存草稿
	  * 
	  * @Description: 方法功能描述
	  * @author army.liu
	  * @date 2016年4月5日 下午3:28:25
	  */
	@Test
	public void testSave() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		StoreInOut inOut = new StoreInOut();
		inOut.setBillType("出库");
		inOut.setTypeData("领药部门1");
		inOut.setSum1(123.32);
		inOut.setSum2(2123.32);
		
		List<StoreInOutDetail> detailList = new ArrayList<StoreInOutDetail>();
		StoreInOutDetail detail = new StoreInOutDetail();
		detail.setAmount(2);
		detail.setBatchNo("PCdsfsdfsd111");
		detail.setDrugId(1000);
		detail.setInvoiceNo("P3243243");
		detail.setOrderNo(1);
		detail.setPrice1(20);
		detail.setPrice2(200);
		detail.setValidDate(new Date());
		detailList.add(detail);
		
		detail = new StoreInOutDetail();
		detail.setAmount(4);
		detail.setBatchNo("PCdsfsdfs222");
		detail.setDrugId(1001);
		detail.setInvoiceNo("P3243243332");
		detail.setOrderNo(2);
		detail.setPrice1(4);
		detail.setPrice2(23);
		detail.setValidDate(new Date());
		detailList.add(detail);
		
		String billOper = "test";
		String storeName = "药库1";
		outStorageService.save(inOut, detailList, billOper, storeName);
	}
	
	/**
	  * 提交草稿
	  * 
	  * @Description: 方法功能描述
	  * @author army.liu
	  * @date 2016年4月5日 下午3:28:25
	  */
	@Test
	public void testSubmit() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		int billNo = 2016041000;
		outStorageService.submit(billNo);
	}
	
	/**
	  * 作废草稿
	  * 
	  * @Description: 方法功能描述
	  * @author army.liu
	  * @date 2016年4月5日 下午3:28:25
	  */
	@Test
	public void testDelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		int billNo = 2016041000;
		outStorageService.delete(billNo);
	}
	
}
