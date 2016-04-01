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
 * 登录controller
 *
 */
@Controller
public class LoginController {

	@Resource 
	private DicEmployeeService dicEmployeeService;
	
	/**
	 * 登录页面: 
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("login");
		
		String id = request.getParameter("id");
		DictEmployee employee = dicEmployeeService.getById(id);
		
		mv.addObject("employee", employee);
		
		return mv;
		
	}
	
	/**
	 * 登录校验：
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
	 * 新增：
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
	 * 登陆后首页: 
	 */
	@RequestMapping(value = "/index",method=RequestMethod.GET)		
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("index");
		
		return mv;
		
	}
	
	/**
	 * 退出: 
	 */
	@RequestMapping(value = "/logout",method=RequestMethod.GET)		
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("login");
		
		CommonUtil.removeUserFromSession( request );
		
		return mv;
		
	}
	
	/**
	 * 报错页面: 
	 */
	@RequestMapping(value = "/fail",method=RequestMethod.GET)		
	public ModelAndView fail(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("fail");
		
		return mv;
		
	}
	
}
