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
import com.template.domain.DicDrug;
import com.template.domain.DicProvider;
import com.template.domain.StorePurchasePlan;
import com.template.domain.StorePurchasePlanDetail;
import com.template.service.DicDrugService;
import com.template.service.DicProviderService;
import com.template.service.InStorageService;
import com.template.service.PurchasePlanService;
import com.template.util.CommonUtil;

/**
 * 采购计划Controller
* @author  fengql 
* @date 2016年4月11日 上午9:12:38
 */
@Controller
@RequestMapping("/purchasePlan")
public class PurchasePlanController {
	
	@Resource  
	private PurchasePlanService purchasePlanService;
	
	@Resource  
	private InStorageService inStorageService;
	
	@Resource  
	private DicProviderService dicProviderService;
	
	@Resource  
	private DicDrugService dicDrugService;

	/**
	 * 采购计划登记页面
	* @author  fengql 
	* @date 2016年4月11日 上午9:13:22 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/purchasePlan/new");
		return mv;
		
	}

	/**
	 * 获取供应商下拉数据
	* @author  fengql 
	* @date 2016年4月11日 上午9:14:15 
	* @parameter  providerName-供应商名称输入
	* @return
	 */
	@RequestMapping(value = "/getEnabledDicProviderList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEnabledDicProviderList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("providerName") String providerName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DicProvider> list = dicProviderService.getEnabledDicProviderList(providerName);
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
	 * 获取药品下拉数据
	* @author  fengql 
	* @date 2016年4月11日 上午9:14:41 
	* @parameter  itemName-药品名称输入
	* @return
	 */
	@RequestMapping(value = "/getEnabledDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDicProviderList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DicDrug> list = dicDrugService.getEnabledDrugList(itemName);
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
	 * 获取选中药品的最近一次进价，零售价
	* @author  fengql 
	* @date 2016年4月11日 上午10:04:55 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/getDrugLatestPrice",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugLatestPrice(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("id") int id
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			Map<String, Object> data = inStorageService.getDrugLatestPrice( storeName, id);
			result.put("data", data);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());	
		}
		
		return result;
	}
	
	/**
	 * 保存采购计划草稿
	* @author  fengql 
	* @date 2016年4月11日 上午10:05:19 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestBody StorePurchasePlan purchaseData
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			//详细信息
			List<StorePurchasePlanDetail> detailList = purchaseData.getDetailList();
			
			String oper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			purchasePlanService.save(purchaseData, detailList, oper, storeName);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 提交采购计划草稿
	* @author  fengql 
	* @date 2016年4月11日 上午11:08:06 
	* @parameter  purchaseNo-采购号
	* @return
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("purchaseNo")int purchaseNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "提交失败");
		
		try{
			purchasePlanService.submit(purchaseNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "提交失败："+e.getMessage());
			
		}
		
		return result;
	}
	
	/**
	 * 作废采购计划
	* @author  fengql 
	* @date 2016年4月11日 上午11:09:30 
	* @parameter  purchaseNo-采购号
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("purchaseNo")int purchaseNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			purchasePlanService.delete(purchaseNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());
			
		}
		
		return result;
	}
	
	/**
	 * 采购计划查询页面
	* @author  fengql 
	* @date 2016年4月11日 下午5:02:08 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/purchasePlan/list");
		return mv;
		
	}
	
	/**
	 * 获取查询页面的列表数据
	* @author  fengql 
	* @date 2016年4月11日 下午5:02:47 
	* @parameter  startTime-开始日期,yyyy-MM-dd
				  endTime-结束日期,yyyy-MM-dd
	           	  providerName-供应商名称
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
			@RequestParam("providerName") String providerName,
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
			params.put("providerName", providerName);
			params.put("itemName", itemName);
			params.put("status", status);
			
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<StorePurchasePlan> list = purchasePlanService.getListData(params);
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
	 * 编辑采购计划登记页面
	* @author  fengql 
	* @date 2016年4月11日 下午5:07:57 
	* @parameter  purchaseNo-采购号
	* @return
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/purchasePlan/edit");
		String purchaseNo = request.getParameter("purchaseNo");
		mv.addObject("purchaseNo", purchaseNo);
		
		return mv;
	}
	
	/**
	 * 获取采购计划草稿的详细信息
	* @author  fengql 
	* @date 2016年4月11日 下午5:08:59 
	* @parameter  purchaseNo-采购号
	* @return detailAndDrugList--详细信息
	 */
	@RequestMapping(value = "/getDetailData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("purchaseNo") int purchaseNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			StorePurchasePlan bean = purchasePlanService.getDetailData( purchaseNo );
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
