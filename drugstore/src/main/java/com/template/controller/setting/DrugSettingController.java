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

import com.template.domain.DicDrug;
import com.template.domain.DicStoreClass;
import com.template.service.DicDrugService;
import com.template.service.DicStoreClassService;


/**
 * 系统设置：药品设置Controller
 * @Description: 管理药品
 * @author army.liu
 */
@Controller
@RequestMapping("/drugSetting")
public class DrugSettingController {
	
	@Resource  
	private DicDrugService dicDrugService;
	
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
		ModelAndView mv = new ModelAndView("systemSetting/drugSetting/list");
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
	@RequestMapping(value = "/getAllStoreClass",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllStoreClass(HttpServletRequest request, 
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
	  * 获取查询页面的列表数据
	  * @Description: 获取查询页面的列表数据
	  * @author army.liu
	  * @param  storeClass-库存分类
	  *         itemName-药品名称
	  * @return 
	  * @throws
	  */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("storeClass") String storeClass,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeClass", storeClass);
			params.put("itemName", itemName);
			
			List<DicDrug> list = dicDrugService.getByConditions(params);
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
		ModelAndView mv = new ModelAndView("systemSetting/drugSetting/new");
		return mv;
		
	}
	
	/**
	  * 获取药品详细信息
	  * @Description: 获取药品详细信息
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
			DicDrug bean = dicDrugService.getById( id );
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
			@RequestBody DicDrug bean
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "保存失败");
		
		try{
			dicDrugService.save(bean);
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
			dicDrugService.delete(id);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败："+e.getMessage());
			
		}
		
		return result;
	}
	
}
