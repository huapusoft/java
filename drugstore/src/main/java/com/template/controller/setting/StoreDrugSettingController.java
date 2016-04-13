package com.template.controller.setting;

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

import com.template.domain.DicDrugFunction;
import com.template.domain.DicStoreClass;
import com.template.domain.DrugAndStore;
import com.template.service.CommonService;
import com.template.service.DicDrugService;
import com.template.service.DicStoreClassService;
import com.template.service.StoreService;


/**
 * 系统设置：库存药品设置Controller
 * @Description: 管理库存药品
 * @author army.liu
 */
@Controller
@RequestMapping("/storeDrugSetting")
public class StoreDrugSettingController {
	
	@Resource  
	private StoreService storeService;
	
	@Resource  
	private DicDrugService dicDrugService;
	
	@Resource  
	private DicStoreClassService dicStoreClassService;
	
	@Resource  
	private CommonService commonService;
	
	/**
	 * 查询页面
	 * @Description: 查询页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/storeDrugSetting/list");
		return mv;
		
	}
	
	/**
	  * 获取查询页面的列表数据
	  * @Description: 获取查询页面的列表数据
	  * @author army.liu
	  * @param  expenseClass-s收费类别
	  *         itemName-药品名称
	  * @return 
	  * @throws
	  */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("expenseClass") String expenseClass,
			@RequestParam("drugForm") String drugForm,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("expenseClass", expenseClass);
			params.put("drugForm", drugForm);
			params.put("itemName", itemName);
			
			List<DrugAndStore> list = storeService.getByConditionsForQuery(params);
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
	 * 设置页面
	 * @Description: 设置页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView savenew(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/storeDrugSetting/edit");
		return mv;
		
	}
	
	/**
	 * 获取库存分类下拉框中数据
	 * @Description: 从库存分类信息表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getStoreClassList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStoreClassList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			List<DicStoreClass> list = dicStoreClassService.getByConditions(params);
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
	 * 获取药品功能下拉数据
	 * @Description: 从药品功能表中，读取所有数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getDrugFunctionList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugFunctionList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			List<DicDrugFunction> list = commonService.getDrugFunctionList(params);
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
	  * 保存
	  * @Description: 保存
	  * @author army.liu
	  * @param  {
	  * 			storeIds--支持多个
	  * 			drugFunction
	  * 			storeClass
	  * 			place1
	  * 			customOrderCode
	  * 			amountLowLimit
	  * 			validDateWarnDays
	  * 		}
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
			@RequestBody DrugAndStore bean
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			commonService.saveStoreDrugSetting(bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
}
