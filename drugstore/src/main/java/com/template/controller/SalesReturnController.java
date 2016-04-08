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

import com.template.domain.DicProvider;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.DicProviderService;
import com.template.service.SalesReturnService;
import com.template.util.CommonUtil;

/**
 * 退货controller
 * @Description: 提供方法与前台页面交互:退货登记，修改，提交，作废等
 * @author army.liu
 */
@Controller
@RequestMapping("/salesReturn")
public class SalesReturnController {

	@Resource 
	private SalesReturnService salesReturnService;
	
	@Resource 
	private DicProviderService dicProviderService;
	
	@Resource 
	private CommonService commonService;
	
	/**
	 * 退货登记页面
	 * @Description: 退货登记页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView news(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/salesReturn/new");
		
		return mv;
		
	}
	
	/**
	  * 获取供应商下拉数据
	  * @Description: 从供应商表中，获取供应商下拉数据
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
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
	 * 获取药品下拉框中数据
	 * @Description: 从药品库存表中，读取药品数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getDrugListFromStore",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugListFromStore(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			List<DrugAndStore> list = commonService.getDrugListForOutStorage(storeName, itemName);
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
	  * 保存草稿
	  * @Description: 保存草稿
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
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
			salesReturnService.save(inOut, detailList, billOper, storeName);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 提交草稿
	 * @Description: 提交草稿
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
			salesReturnService.submit(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "提交失败："+e.getMessage());
			
		}
		
		return result;
	}
	
	/**
	 * 作废草稿
	 * @Description: 作废草稿
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
			salesReturnService.delete(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());
			
		}
		
		return result;
	}
	
}
