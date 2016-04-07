package com.template.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.template.domain.DrugAndStore;
import com.template.service.StoreCheckService;

/**
 * 药库盘点Controller
* @author  fengql 
* @date 2016年4月7日 下午4:11:13
 */
@Controller
@RequestMapping("/storeCheck")
public class StoreCheckController {
	
	@Resource  
	private StoreCheckService storeCheckService;

	/**
	 * 盘点新建页面
	* @author  fengql 
	* @date 2016年4月7日 下午4:35:32 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/storeCheck/new");
		return mv;
		
	}
	
	/**
	 * 新建盘点
	* @author  fengql 
	* @date 2016年4月7日 下午4:55:20 
	* @parameter  
	* @return 库存表中的信息
	 */
	@RequestMapping(value = "/getStoreDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStoreDrugList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DrugAndStore>  drugAndStore= storeCheckService.getStoreDrugList();
			result.put("code", "200");
			result.put("data", drugAndStore);
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	
}
