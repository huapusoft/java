package com.template.controller.query;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.template.domain.DrugAndStore;
import com.template.service.StoreCheckService;
import com.template.util.CommonUtil;

/**
 * 查询统计-库存查询Controller
* @author  fengql 
* @date 2016年4月12日 上午10:07:27
 */
@Controller
@RequestMapping("/storeQuery")
public class StoreQueryController {
	
	@Resource  
	private StoreCheckService storeCheckService;
	
	/**
	 * 查询页面
	* @author  fengql 
	* @date 2016年4月12日 上午10:10:44 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/storeQuery/list");
		return mv;
	}
	
	/**
	 * 根据页面条件 获取查询的列表数据
	* @author  fengql 
	* @date 2016年4月12日 上午10:12:34 
	* @parameter  itemName-药品名称，batchNo-批号
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName,
			@RequestParam("batchNo") String batchNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("itemName", itemName);
			params.put("batchNo", batchNo);
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<DrugAndStore> list = storeCheckService.getStoreDrugList(params);
			result.put("data", list);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
}
