package com.template.test.controller.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.template.domain.DrugAndInOutStatistics;
import com.template.domain.DrugAndStoreInOutDetail;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;

/**
 * 查询统计：进出统计controller测试类
* @author  fengql 
* @date 2016年4月20日 下午3:19:39
 */
public class InOutStatisticsControllerTest {

	/**
	 * 获取药品下拉框数据，从出入库明细表中获取
	* @author  fengql 
	* @date 2016年4月20日 下午4:04:18 
	* @parameter  
	* @return
	 */
	@Test
	public void testgetDrugListFromInOutDetail() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
			
		List<DrugAndStoreInOutDetail> list = commonService.getDrugListFromInOutDetail("一号仓库", "阿莫西林");
		
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
	public void testgetBatchNoFromInOutDetail() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CommonService commonService = (CommonService) context.getBean("commonService");
			
		List<StoreInOutDetail> list = commonService.getBatchNoFromInOutDetail("一号仓库", 10021);
		
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
		CommonService commonService = (CommonService) context.getBean("commonService");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemName", "阿");
		params.put("batchNo", "PIHAO0001");
		params.put("storeName", "一号仓库");
		
		List<DrugAndInOutStatistics> list = commonService.getInOutStatisticsDetail(params);
		
		System.out.println(list.size());
		
	}

	
}

