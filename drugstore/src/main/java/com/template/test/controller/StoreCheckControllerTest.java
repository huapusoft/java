package com.template.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DrugAndCheckDetail;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;
import com.template.service.CommonService;
import com.template.service.StoreCheckService;

/**
 * 盘点controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class StoreCheckControllerTest {

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
		
		List<DrugAndStore> list = commonService.getDrugListForOutStorage("一号仓库", "");
		System.out.println( list.size());
		
	}
	
	/**
	 * 获取新建盘点信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:55:46
	 */
	@Test
	public void testgetStoreDrugList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreCheckService storeCheckService = (StoreCheckService) context.getBean("storeCheckService");
		
		List<DrugAndStore> list = storeCheckService.getStoreDrugList();
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
		StoreCheckService storeCheckService = (StoreCheckService) context.getBean("storeCheckService");
		
		StoreCheck spp = new StoreCheck();
		spp.setCheckTime(new Date());
		spp.setInSum(10.100*287.00+10.100*95.00+20.000*295.00);
		spp.setRetailSum(18.000*287.00+18.000*95.00+30.100*295.00);
		spp.setCheckRetailSum(18.000*277.00+18.000*90.00+30.100*295.00);
		
		List<StoreCheckDetail> detailList = new ArrayList<StoreCheckDetail>();
		StoreCheckDetail detail = new StoreCheckDetail();
		detail.setDrugId(10021);
		detail.setBatchNo("PIHAO0001");
		detail.setOrderNo(1);
		detail.setInPrice(10.100);
		detail.setPrice(18.000);
		detail.setAmount(287.00);
		detail.setRealAmount(277.00);
		detailList.add(detail);
		
		detail = new StoreCheckDetail();
		detail.setDrugId(10021);
		detail.setBatchNo("PIHAO0002");
		detail.setOrderNo(2);
		detail.setInPrice(10.100);
		detail.setPrice(18.000);
		detail.setAmount(95.00);
		detail.setRealAmount(90.00);
		detailList.add(detail);
		
		detail = new StoreCheckDetail();
		detail.setDrugId(10017);
		detail.setBatchNo("PIHAO0002");
		detail.setOrderNo(3);
		detail.setInPrice(20.000);
		detail.setPrice(30.100);
		detail.setAmount(295.00);
		detail.setRealAmount(295.00);
		detailList.add(detail);
		
		storeCheckService.save(spp, detailList, "张三", "一号仓库");
	}
	
	/**
	  * 封存
	  * 
	  * @Description: 方法功能描述
	  * @author army.liu
	  * @date 2016年4月5日 下午3:28:25
	  */
	@Test
	public void testSubmit() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreCheckService storeCheckService = (StoreCheckService) context.getBean("storeCheckService");
		
		StoreCheck spp = new StoreCheck();
		spp.setCheckNo(2016041001);
		spp.setCheckTime(new Date());
		spp.setInSum(10.100*287.00+10.100*95.00+20.000*295.00);
		spp.setRetailSum(18.000*287.00+18.000*95.00+30.100*295.00);
		spp.setCheckRetailSum(18.000*277.00+18.000*90.00+30.100*295.00);
		
		List<StoreCheckDetail> detailList = new ArrayList<StoreCheckDetail>();
		StoreCheckDetail detail = new StoreCheckDetail();
		detail.setDrugId(10021);
		detail.setBatchNo("PIHAO0001");
		detail.setOrderNo(1);
		detail.setInPrice(10.100);
		detail.setPrice(18.000);
		detail.setAmount(287.00);
		detail.setRealAmount(277.00);
		detailList.add(detail);
		
		detail = new StoreCheckDetail();
		detail.setDrugId(10021);
		detail.setBatchNo("PIHAO0002");
		detail.setOrderNo(2);
		detail.setInPrice(10.100);
		detail.setPrice(18.000);
		detail.setAmount(95.00);
		detail.setRealAmount(90.00);
		detailList.add(detail);
		
		detail = new StoreCheckDetail();
		detail.setDrugId(10017);
		detail.setBatchNo("PIHAO0002");
		detail.setOrderNo(3);
		detail.setInPrice(20.000);
		detail.setPrice(30.100);
		detail.setAmount(295.00);
		detail.setRealAmount(295.00);
		detailList.add(detail);
		
		storeCheckService.submit(spp, detailList, "张三", "一号仓库");
		
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
		StoreCheckService storeCheckService = (StoreCheckService) context.getBean("storeCheckService");
		
		storeCheckService.delete(2016041000);
	}
	
	/**
	 * 打开
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月5日 下午3:28:25
	 */
	@Test
	public void testopen() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreCheckService storeCheckService = (StoreCheckService) context.getBean("storeCheckService");
		
		List<DrugAndCheckDetail> list = storeCheckService.getCheckDetailList(2016041000);
		System.out.println(list);
	}
	
}