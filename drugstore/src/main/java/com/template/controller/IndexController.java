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

import com.template.domain.Notices;
import com.template.domain.Store;
import com.template.service.CommonService;
import com.template.service.NoticesService;
import com.template.util.CommonUtil;

/**
 * 首页controller
 * @Description: 提供公共查看，未完成事项统计，信息提醒等功能
 * @author army.liu
 */
@Controller
public class IndexController {

	@Resource 
	private CommonService commonService;
	
	@Resource 
	private NoticesService noticesService;
	
	/**
	  * 首页
	  * @Description: 首页
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/index",method=RequestMethod.GET)		
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("index");
		
		return mv;
		
	}
	
	/**
	  * 获取系统公告
	  * @Description: 读取公告表，显示在有效期内的公告
	  * @author army.liu
	  * @param  
	  * @return {
	  * 			code : 200 --成功
	  * 				   300 --失败
	  * 			data --成功时的数据，List
	  * 			msg  --失败时的原因
	  * 		}
	  * @throws
	  */
	@RequestMapping(value = "/getEnabledNotices",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllDicDrugStore(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<Notices> list = noticesService.getEnabledNotices();
			result.put("data", list);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 获取未完成事项的统计数据
	 * @Description: 读取药库出入库表，统计当前用户的各项业务的未完成情况
	 * @author army.liu
	 * @param  
	 * @return {
	 * 			code : 200 --成功
	 * 				   300 --失败
	 * 			data --成功时的数据，{
	 * 									inSum0 : 0--入库草稿
	 *									inSum1 : 0--入库已提交
	 *									inSum3 : 0--入库驳回
	 *									outSum0 : 0--出库草稿
	 *									outSum1 : 0--出库已提交
	 *									outSum3 : 0--出库驳回
	 *									adjustPriceSum0 : 0--调价草稿
	 *									adjustPriceSum1 : 0--调价已提交
	 *									adjustPriceSum3 : 0--调价驳回
	 *									salesRetSum0 : 0--退货草稿
	 *									salesRetSum1 : 0--退货已提交
	 *									salesRetSum3 : 0--调价驳回
	 *									breakageSum0 : 0--报损草稿
	 *									breakageSum1 : 0--报损已提交
	 *									breakageSum3 : 0--报损驳回
	 *									storeCheckSum0 : 0--盘点草稿
	 *									storePurchasePlanSum0 : 0--采购计划草稿
	 *									storePurchasePlanSum1 : 0--采购计划已提交
	 *									storePurchasePlanSum3 : 0--采购计划复核驳回
	 *									storePurchasePlanSum5 : 0--采购计划领导驳回
	 * 							}
	 * 			msg  --失败时的原因
	 * 		}
	 * @throws
	 */
	@RequestMapping(value = "/getWaitingDataCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWaitingDataCount(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String name = CommonUtil.getUserNameFromSession(request);
			
			Map<String, Object> data = commonService.getWaitingDataCount(name);
			result.put("data", data);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		
		return result;
	}
	
	/**
	  * 获取将要缺货的药品列表
	  * @Description: 读取药库库存表，查询达到下限的药品
	  * @author army.liu
	  * @param  
	  * @return {
	  * 			code : 200 --成功
	  * 				   300 --失败
	  * 			data --成功时的数据，List,库存表记录
	  * 			msg  --失败时的原因
	  * 		}
	  * @throws
	  */
	@RequestMapping(value = "/getDrugsForStockout",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugsForStockout(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<Store> list = commonService.getDrugsForStockout();
			result.put("data", list);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	  * 获取达到有效期设置的药品列表
	  * @Description: 读取药库库存表，查询达到有效期设置的药品
	  * @author army.liu
	  * @param  
	  * @return {
	  * 			code : 200 --成功
	  * 				   300 --失败
	  * 			data --成功时的数据，List,库存表记录
	  * 			msg  --失败时的原因
	  * 		}
	  * @throws
	  */
	@RequestMapping(value = "/getDrugsForDeadline",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugsForDeadline(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<Store> list = commonService.getDrugsForDeadline();
			result.put("data", list);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
}
