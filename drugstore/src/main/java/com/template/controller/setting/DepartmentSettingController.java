package com.template.controller.setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.domain.DicDepartment;
import com.template.service.DicDepartmentService;

/**
 * 系统设置-领药部门设置
* @author  fengql 
* @date 2016年4月13日 上午10:31:31
 */
@Controller
@RequestMapping("/departmentSetting")
public class DepartmentSettingController {
	
	@Resource  
	private DicDepartmentService dicDepartmentService;
	
	/**
	 * 查询页面
	* @author  fengql 
	* @date 2016年4月12日 下午5:31:41 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/departmentSetting/list");
		return mv;
	}

	/**
	 * 获取所有的列表数据--新增时获取上级部门
	* @author  fengql 
	* @date 2016年4月13日 上午9:15:25 
	* @parameter 
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			
			List<DicDepartment> list = dicDepartmentService.getAllDicDepartmentList();
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
	* @date 2016年4月13日 上午9:19:14 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView savenew(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/departmentSetting/new");
		return mv;		
	}
	
	/**
	 * 保存
	* @author  fengql 
	* @date 2016年4月13日 上午9:23:31 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
			@Valid @RequestBody DicDepartment bean, BindingResult bindingResult
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		//校验前台数据
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		if( null != allErrors && allErrors.size() > 0 ){
			FieldError error = (FieldError)allErrors.get(0);
			result.put("msg", error.getField()+error.getDefaultMessage());
			return result;
		}
		
		try{
			dicDepartmentService.save(bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());		
		}
		return result;
	}
	
	/**
	 * 根据departmentId获取领药部门信息-用于修改
	* @author  fengql 
	* @date 2016年4月13日 上午9:51:45 
	* @parameter  领药部门id-departmentId
	* @return
	 */
	@RequestMapping(value = "/getDetailData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("departmentId") int departmentId
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			DicDepartment bean = dicDepartmentService.getByDepartmentId(departmentId);
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
	 * 删除
	* @author  fengql 
	* @date 2016年4月13日 上午9:46:52 
	* @parameter  领药部门Code-departmentCode
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("departmentCode")String departmentCode
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "删除失败");
		
		try{
			dicDepartmentService.delete(departmentCode);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败："+e.getMessage());
		}
		return result;
	}
	
}
