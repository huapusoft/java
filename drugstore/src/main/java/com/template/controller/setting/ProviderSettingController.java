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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.domain.DicProvider;
import com.template.service.CommonService;
import com.template.service.DicProviderService;

/**
 * 系统设置-供应商设置
* @author  fengql 
* @date 2016年4月12日 下午5:30:29
 */
@Controller
@RequestMapping("/providerSetting")
public class ProviderSettingController {
	
	@Resource  
	private DicProviderService dicProviderService;
	
	@Resource  
	private CommonService commonService;
	
	/**
	 * 查询页面
	* @author  fengql 
	* @date 2016年4月12日 下午5:31:41 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/providerSetting/list");
		return mv;
	}
	
	/**
	 * 根据供应商名称获取供应商下拉框列表
	* @author  fengql 
	* @date 2016年4月13日 上午9:07:10 
	* @parameter  providerName-供应商名称
	* @return
	 */
	@RequestMapping(value = "/getProvider",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getProvider(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("providerName") String providerName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("providerName", providerName);
			
			List<DicProvider> list = dicProviderService.getByConditions(params);
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
	 * 获取查询页面的列表数据
	* @author  fengql 
	* @date 2016年4月13日 上午9:15:25 
	* @parameter  providerName-供应商名称，status-状态
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("providerName") String providerName,
			@RequestParam("status") String status
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("providerName", providerName);
			params.put("status", status);
			
			List<DicProvider> list = dicProviderService.getByConditions(params);
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
		ModelAndView mv = new ModelAndView("systemSetting/providerSetting/new");
		return mv;		
	}
	
	/**
	  * 获取中文的拼音码
	  * @Description: 获取中文的拼音码
	  * @author army.liu
	  * @param  name-中文
	  * @return 
	  * @throws
	  */
	@RequestMapping(value = "/getPyCode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPyCode(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("name") String name
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String py = commonService.getPyCode(name);
			result.put("data", py);
			
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 获取中文的五笔码
	 * @Description: 获取中文的五笔码
	 * @author army.liu
	 * @param  name-中文
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/getWbCode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWbCode(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("name") String name
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String wb = commonService.getWbCode(name);
			result.put("data", wb);
			
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
	* @author  fengql 
	* @date 2016年4月13日 上午9:23:31 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
			@RequestBody DicProvider bean, BindingResult bindingResult
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
			dicProviderService.save(bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());		
		}
		return result;
	}
	
	/**
	 * 根据id获取供应商信息-用于修改
	* @author  fengql 
	* @date 2016年4月13日 上午9:51:45 
	* @parameter  供应商id-id
	* @return
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
			DicProvider bean = dicProviderService.getById(id);
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
	* @parameter  供应商id-id
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
			dicProviderService.delete(id);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 更新供应商启用状态
	* @author  fengql 
	* @date 2016年4月13日 上午9:57:17 
	* @parameter  供应商id-id，status-启用还是禁用，1启用0禁用
	* @return
	 */
	@RequestMapping(value = "/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("id")int id,
			@RequestParam("status")int status
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "删除失败");
		
		try{
			dicProviderService.updateStatus(id,status);
			result.put("code", "200");
			result.put("msg", "成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败："+e.getMessage());
		}
		return result;
	}
	
}
