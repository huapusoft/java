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

import com.template.domain.DicCompatibility;
import com.template.domain.DicDrugClass;
import com.template.domain.DicDrugFunction;
import com.template.service.DicCompatibilityService;

/**
 * 系统设置-配伍禁忌
* @author  fengql 
* @date 2016年4月14日 上午10:39:17
 */
@Controller
@RequestMapping("/compatibilitySetting")
public class CompatibilitySettingController {
	
	@Resource  
	private DicCompatibilityService dicCompatibilityService;
	
	/**
	 * 查询页面
	* @author  fengql 
	* @date 2016年4月14日 上午10:40:39 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/compatibilitySetting/list");
		return mv;
	}

	/**
	 * 获取配伍信息
	* @author  fengql 
	* @date 2016年4月14日 上午10:46:35 
	* @parameter  结果-result
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("resultName") String resultName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", resultName);
			List<DicCompatibility> list = dicCompatibilityService.getByConditions(params);
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
	 * 获取功能代码
	* @author  fengql 
	* @date 2016年4月14日 上午10:41:23 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/getfunctionCodeList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getfunctionCodeList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			
			List<DicDrugFunction> list = dicCompatibilityService.getfunctionCode();
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
	 * 获取药品类别id
	* @author  fengql 
	* @date 2016年4月14日 上午10:42:46 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/getdrugClassIdList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getdrugClassIdList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			
			List<DicDrugClass> list = dicCompatibilityService.getdrugClassId();
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
	 * 新建或编辑页面
	* @author  fengql 
	* @date 2016年4月14日 上午10:47:18 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView savenew(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/compatibilitySetting/new");
		return mv;		
	}
	
	/**
	 * 保存
	* @author  fengql 
	* @date 2016年4月14日 上午10:47:35 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
			@RequestBody DicCompatibility bean
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			dicCompatibilityService.save(bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());		
		}
		return result;
	}
	
	/**
	 * 根据id获取配伍信息-用于修改
	* @author  fengql 
	* @date 2016年4月14日 上午10:48:36 
	* @parameter  id
	* @return DicCompatibility
	 */
	@RequestMapping(value = "/getDetailData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("id") int id
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			DicCompatibility bean = dicCompatibilityService.getById(id);
			result.put("data", bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());			
		}	 
		return result;
	}
	
	/**
	 * 删除配伍信息
	* @author  fengql 
	* @date 2016年4月14日 上午10:51:20 
	* @parameter  id
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("id")int id
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "删除失败");
		
		try{
			dicCompatibilityService.delete(id);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败："+e.getMessage());
		}
		return result;
	}
	
}
