package com.template.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.AdjustPriceService;
import com.template.util.CommonUtil;

/**
 * 调价controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class AdjustPriceControllerTest {

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
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		List<DrugAndStore>  list = adjustPriceService.getStoreDrugList("a");
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
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		StoreInOut inOut = new StoreInOut();
		inOut.setBillType("调价");
		inOut.setTypeData("调价批文1");
		inOut.setSum1(15.00*397);
		inOut.setSum2(18.00*397);
		
		List<StoreInOutDetail> detailList = new ArrayList<StoreInOutDetail>();
		StoreInOutDetail detail = new StoreInOutDetail();
		detail.setAmount(397);
		detail.setDrugId(10021);//阿莫西林颗粒
		detail.setOrderNo(1);
		detail.setPrice1(15.00);
		detail.setPrice2(18.00);
		detailList.add(detail);
		
		adjustPriceService.save(inOut, detailList, "张三", "一号仓库");
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
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		int billNo = 2016041062;
		adjustPriceService.submit(billNo);
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
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		adjustPriceService.verifySuccess(2016041064, "财务1", "一号仓库");
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
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		adjustPriceService.verifyFail(2016041062, "财务1");
		
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
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		int billNo = 2016041062;
		adjustPriceService.delete(billNo);
		
	}
	
	/**
	 * 获取调价登记详细信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetDetailData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		StoreInOut detailData = adjustPriceService.getDetailData(2016041007);
		System.out.println(detailData);
	}
	
	/**
	 * 获取调价查询列表信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdjustPriceService adjustPriceService = (AdjustPriceService) context.getBean("adjustPriceService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("storeName", "一号仓库");
		params.put("startTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-08") );
		params.put("endTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-12") );
		params.put("itemName", "阿莫西林颗粒");
		params.put("status", null);
		
		List<StoreInOut> list = adjustPriceService.getListData(params);
		System.out.println(list.size());
		
	}
	
}
