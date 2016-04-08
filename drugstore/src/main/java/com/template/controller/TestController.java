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

import com.template.domain.DictEmployee;
import com.template.service.DicEmployeeService;
import com.template.util.CommonUtil;

/**
 * 测试controller
 * @Description: 测试使用，待删除
 * @author army.liu
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@Resource 
	private DicEmployeeService dicEmployeeService;
	
	/**
	 * 测试主页: 
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("/test/login");
		
		return mv;
		
	}
	
	/**
	 * 测试主页: 
	 */
	@RequestMapping(value = "/index",method=RequestMethod.GET)		
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("/test/index");
		
		return mv;
		
	}
	
	/**
	 * 测试登录校验：
	 * 
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ttt(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("name")String name,
			@RequestParam("password")String password
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "用户名或密码错误");
		
		try{
			if( StringUtils.isEmpty(name) ){
				result.put("msg", "用户名不能为空");
				return result;
			}
			
			if( StringUtils.isEmpty(password) ){
				result.put("msg", "密码不能为空");
				return result;
			}
			
			//校验用户名密码
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("password", password);
			List<DictEmployee> employee = dicEmployeeService.getByConditions(params);
			if( null != employee && employee.size() > 0 ){
				CommonUtil.addUserToSession(request, employee.get(0));
				result.put("code", "200");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "登录失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 测试事务：
	 * 
	 */
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("name1")String name1,
			@RequestParam("name2")String name2
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "用户名或密码错误");
		
		try{
			
			dicEmployeeService.test(name1, name2);
			result.put("code", "200");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "登录失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	  * 测试前后台交互-出库保存
	  * @Description: 测试使用
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/transferParams",method=RequestMethod.GET)		
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("test/transferParams");
		
		return mv;
		
	}
	
	/**
	 * 测试前后台交互-入库保存
	 * @Description: 测试使用
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/saveInStorage",method=RequestMethod.GET)		
	public ModelAndView saveInStorage(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("test/saveInStorage");
		
		return mv;
		
	}
}
