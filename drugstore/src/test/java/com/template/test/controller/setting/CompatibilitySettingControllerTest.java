package com.template.test.controller.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DicCompatibility;
import com.template.domain.DicCompatibilityDetail;
import com.template.service.DicCompatibilityService;

/**
 * 系统设置：配伍禁忌controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class CompatibilitySettingControllerTest {
	
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
		DicCompatibilityService dicCompatibilityService = (DicCompatibilityService) context.getBean("dicCompatibilityService");
		
		DicCompatibility bean = new DicCompatibility();
		bean.setContentA("阿莫西林");
		bean.setContentB("维生素C、罗红霉素");
		bean.setResult("沉淀、分解、失败");
		
		List<DicCompatibilityDetail> detailList= new ArrayList<DicCompatibilityDetail>();
		DicCompatibilityDetail detail = new DicCompatibilityDetail();
		detail.setComTypeA(1);
		detail.setComTypeB(0);
		detail.setDrugClassIdA(1021);
		detail.setFunctionCodeB("1221");
		detailList.add(detail);
		
		bean.setDetailList(detailList);
		
		dicCompatibilityService.save(bean);
		
	}
	
	/**
	 * 获取信息
	* @author  fengql 
	* @date 2016年4月21日 上午9:56:40 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicCompatibilityService dicCompatibilityService = (DicCompatibilityService) context.getBean("dicCompatibilityService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyWords", "C");
		List<DicCompatibility> list = dicCompatibilityService.getByConditions(params);
		System.out.println(list.size());
		
	}
	
	/**
	 * 删除
	* @author  fengql 
	* @date 2016年4月21日 上午10:01:44 
	* @parameter  
	* @return
	 */
	@Test
	public void testdelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicCompatibilityService dicCompatibilityService = (DicCompatibilityService) context.getBean("dicCompatibilityService");
		
		dicCompatibilityService.delete(6);
		
	}
}

