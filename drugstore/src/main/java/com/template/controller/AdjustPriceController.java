package com.template.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.AdjustPriceService;
import com.template.util.CommonUtil;

/**
 * 调价Controller
* @author  fengql 
* @date 2016年4月7日 下午1:39:48
 */
@Controller
@RequestMapping("/adjustPrice")
public class AdjustPriceController {
	
	@Resource  
	private AdjustPriceService adjustPriceService;
	
	/**
	 * 调价登记页面
	* @author  fengql 
	* @date 2016年4月7日 下午1:40:51 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/adjustPrice/new");
		return mv;
		
	}
	
	/**
	 * 获取药品名称下拉框内容，从库存表中读取
	* @author  fengql 
	* @date 2016年4月7日 下午1:49:31 
	* @parameter  itemName-页面输入的名称代码
	* @return
	 */
	@RequestMapping(value = "/getStoreDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStoreDrugList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DrugAndStore>  drugAndStore= adjustPriceService.getStoreDrugList(itemName);
			result.put("code", "200");
			result.put("data", drugAndStore);
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		
		return result;
	}

	/**
	 * 保存调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午3:09:30 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestBody StoreInOut inOut
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			//详细信息
			List<StoreInOutDetail> detailList = inOut.getDetailList();
			
			String billOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			adjustPriceService.save(inOut, detailList, billOper, storeName);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());		
		}
		 
		return result;
	}
	
	/**
	 * 提交调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午3:14:49 
	* @parameter  billNo-票据号
	* @return
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("billNo") int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "提交失败");
		
		try{
			adjustPriceService.submit(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "提交失败："+e.getMessage());	
		}
		
		return result;
	}
	
	/**
	 * 作废调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午3:15:36 
	* @parameter  billNo-票据号
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("billNo") int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			adjustPriceService.delete(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());	
		}
		
		return result;
	}
	
	/**
	 * 调价查询页面
	* @author  fengql 
	* @date 2016年4月11日 下午2:46:31 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/adjustPrice/list");
		return mv;
		
	}
	
	/**
	 * 获取查询数据
	* @author  fengql 
	* @date 2016年4月11日 下午2:48:58 
	* @parameter  startTime-开始日期,yyyy-MM-dd
	     		  endTime-结束日期,yyyy-MM-dd
	              itemName-药品名称
	              status-状态
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("itemName") String itemName,
			@RequestParam("status") String status
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			
			if( StringUtils.isNotEmpty(startTime) ){
				Date startTimeObj = CommonUtil.parseStringToDate("yyyy-MM-dd", startTime);
				params.put("startTime", startTimeObj);
				
			}
			if( StringUtils.isNotEmpty(endTime) ){
				Date endTimeObj = CommonUtil.parseStringToDate("yyyy-MM-dd", endTime);
				params.put("endTime", endTimeObj);
				
			}
			params.put("itemName", itemName);
			params.put("status", status);
			
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<StoreInOut> list = adjustPriceService.getListData(params);
			result.put("data", list);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		 
		return result;
	}
	
	/**
	 * 编辑调价页面
	* @author  fengql 
	* @date 2016年4月11日 下午3:01:33 
	* @parameter  billNo-票据号
	* @return
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/adjustPrice/edit");
		String billNo = request.getParameter("billNo");
		mv.addObject("billNo", billNo);
		return mv;
	}
	
	/**
	 * 获取调价草稿的详细信息
	* @author  fengql 
	* @date 2016年4月11日 下午3:03:34 
	* @parameter   billNo-票据号
	* @return detailAndDrugList[] 详细信息
	 */
	@RequestMapping(value = "/getDetailData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("billNo") int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			StoreInOut bean = adjustPriceService.getDetailData( billNo );
			result.put("data", bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
		}
		 
		return result;
	}
	
}
