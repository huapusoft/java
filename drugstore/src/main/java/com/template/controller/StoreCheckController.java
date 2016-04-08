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
import com.template.domain.DrugAndCheckDetail;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;
import com.template.service.DicDrugService;
import com.template.service.StoreCheckService;
import com.template.util.CommonUtil;

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
	
	@Resource  
	private DicDrugService dicDrugService;

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
		result.put("msg", "新建失败");
		
		try{
			List<DrugAndStore>  drugAndStore= storeCheckService.getStoreDrugList();
			result.put("code", "200");
			result.put("data", drugAndStore);
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "新建失败："+e.getMessage());		
		}
		 
		return result;
	}
	
	/**
	 * 获取盘点内容
	* @author  fengql 
	* @date 2016年4月7日 下午4:55:20 
	* @parameter  checkNo-盘点号
	* @return 盘点明细
	 */
	@RequestMapping(value = "/getCheckDataList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCheckDataList(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestParam("checkNo") int checkNo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DrugAndCheckDetail>  drugAndStore= storeCheckService.getCheckDetailList(checkNo);
			result.put("code", "200");
			result.put("data", drugAndStore);
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		 
		return result;
	}

	/**
	 * 获取药品下拉框数据，从药品基础表中获取
	* @author  fengql 
	* @date 2016年4月8日 上午10:48:32 
	* @parameter  itemName-输入的内容
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
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		
		return result;
	}
	
	/**
	 * 保存盘点内容-未封存
	* @author  fengql 
	* @date 2016年4月8日 上午10:52:43 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestBody StoreCheck checkData ) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			//详细信息
			List<StoreCheckDetail> detailList = checkData.getDetailList();
			
			String checkOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			storeCheckService.save(checkData, detailList, checkOper, storeName);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());	
		}
		 
		return result;
	}
	
	/**
	 * 作废盘点单-未封存的
	* @author  fengql 
	* @date 2016年4月8日 下午2:20:06 
	* @parameter  checkNo-盘点号
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("checkNo") int checkNo ) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			storeCheckService.delete(checkNo);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());		
		}
		
		return result;
	}

	/**
	 * 封存盘点单
	* @author  fengql 
	* @date 2016年4月8日 下午2:35:42 
	* @parameter  checkNo-盘点号
	* @return
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("checkNo") int checkNo ) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "封存失败");
		
		try{
			storeCheckService.submit(checkNo);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "封存失败："+e.getMessage());	
		}
		
		return result;
	}
	
}
