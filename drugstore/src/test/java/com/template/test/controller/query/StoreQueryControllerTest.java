package com.template.test.controller.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.template.domain.DrugAndStore;
import com.template.domain.Store;
import com.template.service.CommonService;
import com.template.service.StoreService;

/**
 * 查询统计：库存查询controller测试类
* @author  fengql 
* @date 2016年4月20日 下午3:19:39
 */
public class StoreQueryControllerTest {

	/**
	 * 获取药品下拉框数据，从库存表中获取
	* @author  fengql 
	* @date 2016年4月20日 下午3:20:55 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetDrugListFromStore() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
			
		List<DrugAndStore> list = commonService.getDrugListForOutStorage("一号仓库", "阿莫西林");
		
		System.out.println(list.size());
		
	}
	
	/**
	 * 获取批号
	* @author  fengql 
	* @date 2016年4月20日 下午3:43:45 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetBatchNoFromStore() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
			
		List<Store> list = commonService.getBatchNoFromStore("一号仓库", 10021);
		System.out.println(list.size());
		
	}
	
	/**
	 * 查询库存
	* @author  fengql 
	* @date 2016年4月20日 下午3:51:09 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreService storeService = (StoreService) context.getBean("storeService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemName", "阿");
		params.put("batchNo", "PIHAO0001");
		params.put("storeName", "一号仓库");
		
		List<DrugAndStore> list = storeService.getByConditionsForQuery(params);
		
		System.out.println(list.size());
		
	}
	
	/**
	 * 导出
	* @author  fengql 
	* @date 2016年4月20日 下午3:54:20 
	* @parameter  
	* @return
	 */
	@Test
	public void testexport() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreService storeService = (StoreService) context.getBean("storeService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("itemName", "阿");
		params.put("batchNo", "PIHAO0001");
		params.put("storeName", "一号仓库");
		
		Map<String, Object> formatData = storeService.getExportData(params);
		System.out.println(formatData);
	}
	
	
}

