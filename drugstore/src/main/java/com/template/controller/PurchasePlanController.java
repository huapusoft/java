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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.domain.DicDrug;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.DicDrugService;
import com.template.service.DicProviderService;
import com.template.service.InStorageService;
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
			dicProviderService.getEnabledDicProviderList(providerName);
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
	 * @Description: 获取选中药品的最近一次进价，零售价，用于添加某个药品后，自动回显进价和零售价
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
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
	  * 保存入库草稿
	  * @Description: 保存入库草稿
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
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
			inStorageService.save(inOut, detailList, billOper, storeName);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 提交入库草稿
	 * @Description: 提交入库草稿
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("billNo")int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "提交失败");
		
		try{
			inStorageService.submit(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "提交失败："+e.getMessage());
			
		}
		
		return result;
	}
	
	/**
	 * 作废入库草稿
	 * @Description: 作废入库草稿
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("billNo")int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			inStorageService.delete(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());
			
		}
		
		return result;
	}
	
}
