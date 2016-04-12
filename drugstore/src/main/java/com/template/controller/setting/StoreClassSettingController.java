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

import com.template.domain.DicStoreClass;
import com.template.service.DicStoreClassService;


/**
 * 系统设置：库存分类设置Controller
 * @Description: 管理库存分类
 * @author army.liu
 */
@Controller
@RequestMapping("/storeClassSetting")
public class StoreClassSettingController {
	
	@Resource  
	private DicStoreClassService dicStoreClassService;
	
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
		ModelAndView mv = new ModelAndView("systemSetting/storeClassSetting/list");
		return mv;
		
	}
	
	/**
	  * 获取查询页面的列表数据
	  * @Description: 获取查询页面的列表数据
	  * @author army.liu
	  * @param  
	  * @return 
	  * @throws
	  */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("name") String name
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			
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
	 * 新建或编辑页面
	 * @Description:  新建或编辑
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView savenew(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("systemSetting/storeClassSetting/new");
		return mv;
		
	}
	
	/**
	  * 获取库存分类详细信息
	  * @Description: 获取库存分类详细信息
	  * @author army.liu
	  * @param  id-药品标识
	  * @return 
	  * @throws
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
			DicStoreClass bean = dicStoreClassService.getById( id );
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
	  * 保存
	  * @Description: 保存
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session,
			@RequestBody DicStoreClass bean
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			dicStoreClassService.save(bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 删除
	 * @Description: 删除
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
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
			dicStoreClassService.delete(id);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败："+e.getMessage());
			
		}
		
		return result;
	}
	
}
