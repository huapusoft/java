package com.template.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.template.util.CommonUtil;

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
		
		List<DrugAndStore> list = commonService.getDrugListForOutStorage("一号仓库", "");
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
		inOut.setBillNo(2016041027);
		inOut.setBillType("出库");
		inOut.setTypeData("门诊");
		inOut.setSum1(50.1);
		inOut.setSum2(75.2);
		
		List<StoreInOutDetail> detailList = new ArrayList<StoreInOutDetail>();
		StoreInOutDetail detail = new StoreInOutDetail();
		detail.setAmount(1);
		detail.setBatchNo("PIHAO0001");
		detail.setDrugId(10021);//阿莫西林颗粒
		detail.setInvoiceNo("FAPIAO0001");
		detail.setOrderNo(1);
		detail.setPrice1(10.10);
		detail.setPrice2(15.00);
		detail.setValidDate(new Date());
		detailList.add(detail);
		
		detail = new StoreInOutDetail();
		detail.setAmount(2);
		detail.setBatchNo("PIHAO0002");
		detail.setDrugId(10017);//阿莫西林胶囊
		detail.setInvoiceNo("FAPIAO0002");
		detail.setOrderNo(2);
		detail.setPrice1(20.00);
		detail.setPrice2(30.10);
		detail.setValidDate(new Date());
		detailList.add(detail);
		
		int save = outStorageService.save(inOut, detailList, "张三", "一号仓库");
		System.out.println(save);
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
		
		int billNo = 2016041003;
		outStorageService.submit(billNo);
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
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		outStorageService.verifySuccess(2016041003, "财务1", "一号仓库");
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
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		outStorageService.verifyFail(2016041003, "财务1");
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
		
		int billNo = 2016041003;
		outStorageService.delete(billNo);
	}
	
	/**
	 * 获取出库登记详细信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetDetailData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		StoreInOut detailData = outStorageService.getDetailData(2016041001);
		System.out.println(detailData);
	}
	
	/**
	 * 获取出库查询列表信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OutStorageService outStorageService = (OutStorageService) context.getBean("outStorageService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("storeName", "一号仓库");
		params.put("billType", "出库");
		params.put("typeData", "手术室");
		params.put("startTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-08") );
		params.put("endTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-12") );
//		params.put("itemName", "阿莫西林颗粒");
		params.put("status", null);
		
		List<StoreInOut> list = outStorageService.getListData(params);
		System.out.println(list.size());
		
	}
	
}
