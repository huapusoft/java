package com.template.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDrug;
import com.template.domain.DicProvider;
import com.template.domain.DrugAndStore;
import com.template.domain.StorePurchasePlan;
import com.template.domain.StorePurchasePlanDetail;
import com.template.service.DicDrugService;
import com.template.service.DicProviderService;
import com.template.service.InStorageService;
import com.template.service.PurchasePlanService;
import com.template.service.StoreService;
import com.template.util.CommonUtil;

/**
 * 采购计划controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class PurchasePlanControllerTest {

	/**
	 * 获取缺货列表
	* @author  fengql 
	* @date 2016年4月20日 下午2:59:48 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetDrugsForStockout() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreService storeService = (StoreService) context.getBean("storeService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stockOutFlag", "Y");
		params.put("storeName", "一号仓库");
		
		List<DrugAndStore> list = storeService.getByConditionsForQuery(params);
		System.out.println( list.size());
		
	}
	
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
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		StorePurchasePlan spp = new StorePurchasePlan();
		spp.setRemark("2016年第一季度采购计划2");
		spp.setInSum( 500*20.000+500*11.000 );
		spp.setRetailSum( 500*35.000+500*20.000 );
		
		List<StorePurchasePlanDetail> detailList = new ArrayList<StorePurchasePlanDetail>();
		StorePurchasePlanDetail detail = new StorePurchasePlanDetail();
		detail.setOrderNo(1);
		detail.setArriveTime(new Date());
		detail.setProvider("国药控股盐城有限公司");
		detail.setDrugId(10017);
		detail.setAmount(500);
		detail.setInPrice(20.000);
		detail.setPrice(35.000);
		detail.setRemark("test111");
		detailList.add(detail);
		
		detail = new StorePurchasePlanDetail();
		detail.setOrderNo(2);
		detail.setArriveTime(new Date());
		detail.setProvider("国药控股盐城有限公司");
		detail.setDrugId(10021);
		detail.setAmount(500);
		detail.setInPrice(11.000);
		detail.setPrice(20.000);
		detail.setRemark("test111");
		detailList.add(detail);
		
		purchasePlanService.save(spp, detailList, "张三", "一号仓库");
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
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		purchasePlanService.submit(2016041001);
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
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		purchasePlanService.delete(2016041001);
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
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		purchasePlanService.financeAudit(2016041001, "财务1", 1);
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
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		purchasePlanService.financeAudit(2016041001, "财务1", 0);
	}
	
	/**
	 * 复核通过
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testleaderSuccess() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		purchasePlanService.leaderAudit(2016041001, "领导1", 1);
	}
	
	/**
	 * 复核驳回
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 2016年4月8日 上午8:56:48
	 */
	@Test
	public void testleaderFail() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		purchasePlanService.leaderAudit(2016041001, "领导1", 0);
	}
	
	/**
	 * 获取采购计划登记详细信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetDetailData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		StorePurchasePlan detailData = purchasePlanService.getDetailData(2016041000);
		System.out.println(detailData);
	}
	
	/**
	 * 获取采购计划查询列表信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PurchasePlanService purchasePlanService = (PurchasePlanService) context.getBean("purchasePlanService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("storeName", "一号仓库");
		params.put("startTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-08") );
		params.put("endTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-20") );
		params.put("itemName", "阿莫西林颗粒");
		params.put("status", null);
		
		List<StorePurchasePlan> list = purchasePlanService.getListData(params);
		System.out.println(list.size());
		
	}
}
