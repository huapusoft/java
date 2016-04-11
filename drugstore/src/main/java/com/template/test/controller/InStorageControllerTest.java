package com.template.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDrug;
import com.template.domain.DicProvider;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.DicDrugService;
import com.template.service.DicProviderService;
import com.template.service.InStorageService;

/**
 * 入库controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class InStorageControllerTest {

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
	
	/**
	 * 保存草稿
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testsave() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		StoreInOut inOut = new StoreInOut();
		inOut.setBillType("入库");
		inOut.setTypeData("国药控股盐城有限公司");
		inOut.setSum1(3010.000);
		inOut.setSum2(4510.000);
		
		List<StoreInOutDetail> detailList = new ArrayList<StoreInOutDetail>();
		StoreInOutDetail detail = new StoreInOutDetail();
		detail.setAmount(100);
		detail.setBatchNo("PIHAO0002");
		detail.setDrugId(10021);//阿莫西林颗粒
		detail.setInvoiceNo("FAPIAO0002");
		detail.setOrderNo(1);
		detail.setPrice1(10.10);
		detail.setPrice2(15.00);
		detail.setValidDate(new Date());
		detailList.add(detail);
		
//		detail = new StoreInOutDetail();
//		detail.setAmount(100);
//		detail.setBatchNo("PIHAO0002");
//		detail.setDrugId(10017);//阿莫西林胶囊
//		detail.setInvoiceNo("FAPIAO0002");
//		detail.setOrderNo(2);
//		detail.setPrice1(20.00);
//		detail.setPrice2(30.10);
//		detail.setValidDate(new Date());
//		detailList.add(detail);
		
		inStorageService.save(inOut, detailList, "张三", "一号仓库");
	}
	
	/**
	 * 提交
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testsubmit() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		inStorageService.submit(2016041006);
	}
	
	/**
	 * 删除
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testdelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		inStorageService.delete(2016041001);
	}
	
	/**
	 * 复核通过
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testverifySuccess() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		inStorageService.verifySuccess(2016041006, "财务1", "一号仓库");
	}
	
	/**
	 * 复核驳回
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testverifyFail() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InStorageService inStorageService = (InStorageService) context.getBean("inStorageService");
		
		inStorageService.verifyFail(2016041000, "财务1");
	}
}

