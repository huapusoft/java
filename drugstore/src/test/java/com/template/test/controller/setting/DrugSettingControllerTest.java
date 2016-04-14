package com.template.test.controller.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicDrug;
import com.template.domain.DicHzylContrast;
import com.template.domain.DicMiContrast;
import com.template.domain.DicStoreClass;
import com.template.service.CommonService;
import com.template.service.DicDrugService;
import com.template.service.DicStoreClassService;

/**
 * 系统设置：库存分类controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class DrugSettingControllerTest {

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
		DicDrugService dicDrugService = (DicDrugService) context.getBean("dicDrugService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeClass", "");
		params.put("itemName", "千年");
		
		List<DicDrug> list = dicDrugService.getByConditions(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 获取合疗编码下拉框数据
	 * @Description: 从合疗编码信息表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testgetDicHzylContrast() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "b");
		List<DicHzylContrast> list = commonService.getDicHzylContrast(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 获取医保编码下拉框数据
	 * @Description: 从医保编码信息表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testgetAllYbCode() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "b");
		List<DicMiContrast> list = commonService.getDicMiContrast(params);
		System.out.println(list.size());
		
	}
	
	/**
	  * 获取药品详细信息
	  * @Description: 获取药品详细信息
	  * @author army.liu
	  * @param  id-药品标识
	  * @return 
	  * @throws
	  */
	@Test
	public void testgetdetail() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDrugService dicDrugService = (DicDrugService) context.getBean("dicDrugService");
		
		DicDrug bean = dicDrugService.getById( 11714 );
		System.out.println(bean.getItemName());
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
		DicDrugService dicDrugService = (DicDrugService) context.getBean("dicDrugService");
		
		DicDrug bean = new DicDrug();
		bean.setExpenseClass("西药");
		bean.setDrugFrom("喷雾");
		bean.setStoreClass("测试分类1");
		bean.setItemName("药品1");
		bean.setSpec("10ml*8瓶");
		bean.setVendor("国药1号企业");
		dicDrugService.save(bean);
		
//		bean.setId(13219);
//		bean.setItemName("药品2");
//		dicDrugService.save(bean);
		
	}
	
	/**
	 * 删除
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return 
	 * @throws
	 */
	@Test
	public void testdelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDrugService dicDrugService = (DicDrugService) context.getBean("dicDrugService");
		
		dicDrugService.delete(13219);
	}
	
	/**
	 * 获取拼音码
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testgetPyCode() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		String name = "阿莫西林颗粒";
		String codes = commonService.getPyCode(name);
		System.out.println(codes);
		
	}
	
	/**
	 * 获取五笔码
	 * @Description: 
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@Test
	public void testgetWbCode() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		String name = "阿莫西林颗粒";
		String codes = commonService.getWbCode(name);
		System.out.println(codes);
		
	}
}

