package com.template.test.controller.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.template.domain.DrugAndReports;
import com.template.service.CommonService;
import com.template.util.CommonUtil;

/**
 * 查询统计：药品台账controller测试类
 * @Description: 测试
 * @author army.liu
 */
public class DrugReportsControllerTest {

	/**
	 * 获取查询列表信息
	 * 
	 * @Description: 方法功能描述
	 * @author army.liu
	 * @date 
	 */
	@Test
	public void testgetListData() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("storeName", "一号仓库");
		params.put("billType", "入库");
		params.put("startTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-09") );
		params.put("endTime", CommonUtil.parseStringToDate("yyyy-MM-dd", "2016-04-12") );
		params.put("itemName", "阿莫西林颗粒");
		
		List<DrugAndReports> list = commonService.getListDataForDrugReports(params);
		System.out.println(list.size());
		
	}
	
}

