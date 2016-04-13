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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.domain.DictEmployee;
import com.template.service.CommonService;
import com.template.service.DicEmployeeService;
import com.template.util.CommonUtil;


/**
 * 系统设置：修改密码Controller
 * @Description: 管理密码
 * @author army.liu
 */
@Controller
@RequestMapping("/passwordSetting")
public class PasswordSettingController {
	
	@Resource  
	private DicEmployeeService dicEmployeeService;
	
	@Resource  
	private CommonService commonService;
	
	/**
	 * 密码修改页面
	 * @Description: 密码修改页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/passwordSetting/edit");
		return mv;
		
	}
	
	/**
	 * 校验原密码是否正确
	 * @Description: 校验原密码是否正确
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/validateOldPassword",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validateOldPassword(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("password")String password
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", CommonUtil.getUserNameFromSession(request));
			params.put("password", password);
			List<DictEmployee> list = dicEmployeeService.getByConditions(params);
			if( null != list && list.size() > 0 ){
				result.put("code", "200");
				result.put("msg", "成功");
				
			}
			
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
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
			@RequestParam("oldPassword")String oldPassword,
			@RequestParam("password")String password
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", CommonUtil.getUserNameFromSession(request));
			params.put("password", oldPassword);
			List<DictEmployee> list = dicEmployeeService.getByConditions(params);
			if( null != list && list.size() > 0 ){
				DictEmployee bean = list.get(0);
				bean.setPassword(password);
				dicEmployeeService.update( bean );
				
				CommonUtil.addUserToSession(request, bean);
				
				result.put("code", "200");
				result.put("msg", "成功");
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
}
