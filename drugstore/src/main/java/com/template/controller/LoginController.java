package com.template.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.domain.DicDrugStore;
import com.template.domain.DictEmployee;
import com.template.service.CommonService;
import com.template.service.DicEmployeeService;
import com.template.util.CommonUtil;

/**
 * 登录controller
 * @Description: 提供登录，退出等功能
 * @author army.liu
 */
@Controller
public class LoginController {

	@Resource 
	private DicEmployeeService dicEmployeeService;
	
	@Resource 
	private CommonService commonService;
	
	/**
	  * 登录页面
	  * @Description: 打开登录页面
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/",method=RequestMethod.GET)		
	public ModelAndView rootlogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
		
	}
	
	/**
	 * 登录页面
	 * @Description: 打开登录页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
		
	}
	
	/**
	  * 获取药库下拉数据
	  * @Description: 读取药库表，获取所有药库名称
	  * @author army.liu
	  * @param  
	  * @return {
	  * 			code : 200 --成功
	  * 				   300 --失败
	  * 			data --成功时的数据，药库List
	  * 			msg  --失败时的原因
	  * 		}
	  * @throws
	  */
	@RequestMapping(value = "/getAllDicDrugStore",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllDicDrugStore(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DicDrugStore> list = commonService.getAllDicDrugStore();
			result.put("data", list);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 获取药库的用户数据
	 * @Description: 读取用户表，获取当前药库下的用户名称
	 * @author army.liu
	 * @param  
	 * @return {
	 * 			code : 200 --成功
	 * 				   300 --失败
	 * 			data --成功时的数据，List<String>
	 * 			msg  --失败时的原因
	 * 		}
	 * @throws
	 */
	@RequestMapping(value = "/getDicEmployeeBySotreName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDicEmployeeBySotreName(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("storeName")String storeName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<String> list = commonService.getDicEmployeeBySotreName(storeName);
			result.put("data", list);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		
		return result;
	}
	
//	/**
//	  * 获取最近一次登录的用户名称
//	  * @Description: 获取最近一次登录的用户名称，回显在页面
//	  * @author army.liu
//	  * @param  
//	  * @return {
//	  * 			code : 200 --成功
//	  * 				   300 --失败
//	  * 			data --成功时的数据，最近一次登录的用户名称
//	  * 			msg  --失败时的原因
//	  * 		}
//	  * @throws
//	  */
//	@RequestMapping(value = "/getLatestLoginUserName",method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getLatestLoginUserName(HttpServletRequest request, 
//			HttpServletResponse response,
//			HttpSession session
//			) throws Exception {
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("code", "300");
//		result.put("msg", "获取失败");
//		
//		try{
//			String name = commonService.getLatestLoginUserName();
//			result.put("data", name);
//			result.put("code", "200");
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			result.put("msg", "获取失败："+e.getMessage());
//			
//		}
//		 
//		return result;
//	}
	
	
	/**
	  * 登录校验
	  * @Description: 登录校验检查
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/validate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ttt(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("name")String name,
			@RequestParam("password")String password,
			@RequestParam("storeName")String storeName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "用户名或密码错误");
		
		try{
			if( StringUtils.isEmpty(storeName) ){
				result.put("msg", "药库名称不能为空");
				return result;
			}
			
			if( StringUtils.isEmpty(name) ){
				result.put("msg", "用户名不能为空");
				return result;
			}
			
			if( StringUtils.isEmpty(password) ){
				result.put("msg", "密码不能为空");
				return result;
			}
			
			name = name.trim();
			password = password.trim();
			storeName = storeName.trim();
			
			boolean storeFlag = commonService.validateStoreName(storeName);
			if( !storeFlag ){
				result.put("msg", "药库名称不存在");
				return result;
			}
			
			//校验用户名密码
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("password", password);
			List<DictEmployee> employee = dicEmployeeService.getByConditions(params);
			if( null != employee && employee.size() > 0 ){
				DictEmployee user = employee.get(0);
				user.setRoleInfo(storeName);
				CommonUtil.addUserToSession(request, user);
				result.put("code", "200");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "登录失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	  * 退出
	  * @Description: 退出
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/logout",method=RequestMethod.GET)		
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("login");
		
		CommonUtil.removeUserFromSession( request );
		
		return mv;
		
	}
	
	/**
	  * 显示错误提示页面
	  * @Description: 显示错误提示页面
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/fail",method=RequestMethod.GET)		
	public ModelAndView fail(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("fail");
		
		return mv;
		
	}
	
}
