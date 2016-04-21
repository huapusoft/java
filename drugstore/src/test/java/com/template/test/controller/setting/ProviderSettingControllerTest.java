package com.template.test.controller.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.template.domain.DicProvider;
import com.template.service.CommonService;
import com.template.service.DicProviderService;

/**
 * 系统设置：供应商controller测试类
* @author  fengql 
* @date 2016年4月20日 下午4:27:25
 */
public class ProviderSettingControllerTest {

	/**
	 * 获取供应商下拉
	* @author  fengql 
	* @date 2016年4月20日 下午4:32:51 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetProvider() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("providerName", "德");
		
		List<DicProvider> list = dicProviderService.getByConditions(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 获取供应商查询数据
	* @author  fengql 
	* @date 2016年4月20日 下午4:32:51 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("providerName", "德");
		params.put("status", "1");
		
		List<DicProvider> list = dicProviderService.getByConditions(params);
		System.out.println(list.size());
		
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
	
	/**
	 * 保存供应商
	* @author  fengql 
	* @date 2016年4月20日 下午4:59:42 
	* @parameter  
	* @return
	 */
	@Test
	public void testsave() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		DicProvider bean = new DicProvider();
		bean.setProviderName("nnnnnn");
		dicProviderService.save(bean);	
		
	}
	
	/**
	 * 获取供应商详情
	* @author  fengql 
	* @date 2016年4月20日 下午5:03:08 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetDetailData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		DicProvider bean = dicProviderService.getById(24);
		System.out.println(bean.getProviderName());
	}
	
	/**
	 * 删除供应商
	* @author  fengql 
	* @date 2016年4月20日 下午5:04:02 
	* @parameter  
	* @return
	 */
	@Test
	public void testdelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		dicProviderService.delete(24);
	}
	
	/**
	 * 修改状态
	* @author  fengql 
	* @date 2016年4月20日 下午5:30:07 
	* @parameter  
	* @return
	 */
	@Test
	public void testupdateStatus() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicProviderService dicProviderService = (DicProviderService) context.getBean("dicProviderService");
		
		dicProviderService.updateStatus(1,1);
	}
}

