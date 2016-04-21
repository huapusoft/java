package com.template.test.controller.setting;

import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.template.domain.DicDepartment;
import com.template.service.DicDepartmentService;

/**
 * 系统设置：领药部门controller测试类
* @author  fengql 
* @date 2016年4月21日 上午8:47:43
 */
public class DepartmentSettingControllerTest {

	/**
	 * 获取领药部门
	* @author  fengql 
	* @date 2016年4月21日 上午9:03:10 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDepartmentService dicDepartmentService = (DicDepartmentService) context.getBean("dicDepartmentService");
	
		List<DicDepartment> list = dicDepartmentService.getAllDicDepartmentList();
		System.out.println(list.size());
		
	}
	
	/**
	 * 保存
	* @author  fengql 
	* @date 2016年4月21日 上午9:03:26 
	* @parameter  
	* @return
	 */
	@Test
	public void testsave() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDepartmentService dicDepartmentService = (DicDepartmentService) context.getBean("dicDepartmentService");
		
		DicDepartment bean = new DicDepartment();
		bean.setDepartmentName("外科");
		bean.setParentCode("10");
		dicDepartmentService.save(bean);	
		
	}

	/**
	 * 获取领药部门详情
	* @author  fengql 
	* @date 2016年4月21日 上午9:30:32 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetDetailData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDepartmentService dicDepartmentService = (DicDepartmentService) context.getBean("dicDepartmentService");
		
		DicDepartment bean = dicDepartmentService.getByDepartmentId(2);
		System.out.println(bean.getDepartmentName());
	}
	
	/**
	 * 删除
	* @author  fengql 
	* @date 2016年4月21日 上午9:31:10 
	* @parameter  
	* @return
	 */
	@Test
	public void testdelete() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DicDepartmentService dicDepartmentService = (DicDepartmentService) context.getBean("dicDepartmentService");
		
		dicDepartmentService.delete("1020");
	}

}

