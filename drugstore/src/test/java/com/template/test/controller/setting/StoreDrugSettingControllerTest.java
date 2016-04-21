package com.template.test.controller.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDrug;
import com.template.domain.DicDrugFunction;
import com.template.domain.DicStoreClass;
import com.template.domain.DrugAndStore;
import com.template.service.CommonService;
import com.template.service.DicDrugService;
import com.template.service.DicStoreClassService;
import com.template.service.StoreService;

/**
 * 系统设置：药品库存分类controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class StoreDrugSettingControllerTest {

	/**
	 * 获取库存分类下拉框中数据
	 * @Description: 从库存分类信息表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testdicStoreClassService() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicStoreClassService dicStoreClassService = (DicStoreClassService) context.getBean("dicStoreClassService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<DicStoreClass> list = dicStoreClassService.getByConditions(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 获取查询页面的列表数据
	 * @Description: 获取查询页面的列表数据
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	@Test
	public void testgetList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreService storeService = (StoreService) context.getBean("storeService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("expenseClass", "");
		params.put("drugForm", "");
		params.put("itemName", "");
		
		List<DrugAndStore> list = storeService.getByConditionsForQuery(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 获取库存分类下拉框中数据
	 * @Description: 从库存分类信息表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testgetStoreClassList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicStoreClassService dicStoreClassService = (DicStoreClassService) context.getBean("dicStoreClassService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<DicStoreClass> list = dicStoreClassService.getByConditions(params);
		System.out.println(list.size());
	}
	
	/**
	 * 获取药品功能下拉数据
	 * @Description: 从药品功能表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testgetDrugFunctionList() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<DicDrugFunction> list = commonService.getDrugFunctionList(params);
		System.out.println(list.size());
	}
	
	/**
	 * 保存
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	@Test
	public void testsave() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		DrugAndStore bean = new DrugAndStore();
//						storeIds--支持多个
//		  * 			drugFunction
//		  * 			storeClass
//		  * 			place1
//		  * 			customOrderCode
//		  * 			amountLowLimit
//		  * 			validDateWarnDays
		bean.setStoreIds("3");
		bean.setDrugFunction("功能1");
		bean.setStoreClass("分类1");
		bean.setPlace1("位置1");
		bean.setCustomOrderCode(8989);
		bean.setValidDateWarnDays(90);
		bean.setAmountLowLimit(80);
		
		commonService.saveStoreDrugSetting(bean);
		
	}

}

