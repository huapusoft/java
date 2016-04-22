package com.template.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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

import com.template.domain.DicDataDictionary;
import com.template.domain.DicDrug;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;
import com.template.service.CommonService;
import com.template.service.DicDrugService;
import com.template.service.StoreCheckService;
import com.template.service.StoreService;
import com.template.util.CommonUtil;

/**
 * 药库盘点Controller
* @author  fengql 
* @date 2016年4月7日 下午4:11:13
 */
@Controller
@RequestMapping("/storeCheck")
public class StoreCheckController {
	
	@Resource  
	private StoreService storeService;
	
	@Resource  
	private StoreCheckService storeCheckService;
	
	@Resource  
	private CommonService commonService;
	
	@Resource  
	private DicDrugService dicDrugService;

	/**
	 * 盘点新建页面
	* @author  fengql 
	* @date 2016年4月7日 下午4:35:32 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/storeCheck/new");
		return mv;
		
	}
	
	/**
	 *  获取已启用的药品信息--用于名称下拉
	* @author  fengql 
	* @date 2016年4月13日 下午2:29:46 
	* @parameter  itemName-药品名称简写
	* @return	List<DicDrug>
	 */
	@RequestMapping(value = "/getEnabledDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEnabledDrugList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			
			List<DicDrug> list = dicDrugService.getEnabledDrugList(itemName);
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
	 * 新建盘点
	* @author  fengql 
	* @date 2016年4月7日 下午4:55:20 
	* @parameter  
	* @return 库存表中的信息
	 */
	@RequestMapping(value = "/getStoreDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStoreDrugList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "新建失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			List<DrugAndStore>  drugAndStore= storeService.getByConditionsForQuery(params);
			result.put("code", "200");
			result.put("data", drugAndStore);
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "新建失败："+e.getMessage());		
		}
		 
		return result;
	}
	
	/**
	 * 根据盘点号 打开盘点信息
	* @author  fengql 
	* @date 2016年4月7日 下午4:55:20 
	* @parameter  checkNo-盘点号
	* @return 盘点明细
	 */
	@RequestMapping(value = "/getCheckDataList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCheckDataList(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestParam("checkNo") int checkNo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			StoreCheck bean =storeCheckService.getDetailData( checkNo );
			if( null != bean ){
				String sealOper = bean.getSealOper();
				if( StringUtils.isEmpty(sealOper) ){
					result.put("data", bean);
					result.put("code", "200");
					result.put("msg", "成功");
					
				}else{
					result.put("msg", "当前数据已提交或已复核！");
				}
				
			}
			result.put("code", "200");
			result.put("data", bean);
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		 
		return result;
	}

	/**
	 * 保存盘点内容-未封存
	* @author  fengql 
	* @date 2016年4月8日 上午10:52:43 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session,
			@Valid @RequestBody StoreCheck checkData, BindingResult bindingResult
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
			//详细信息
			List<StoreCheckDetail> detailList = checkData.getDetailList();
			
			String checkOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			int checkNo = storeCheckService.save(checkData, detailList, checkOper, storeName);
			
			result.put("data", checkNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());	
		}
		 
		return result;
	}
	
	/**
	 * 作废盘点单-未封存的
	* @author  fengql 
	* @date 2016年4月8日 下午2:20:06 
	* @parameter  checkNo-盘点号
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("checkNo") int checkNo ) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			storeCheckService.delete(checkNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());		
		}
		
		return result;
	}

	/**
	 * 封存盘点单
	* @author  fengql 
	* @date 2016年4月8日 下午2:35:42 
	* @parameter  checkNo-盘点号
	* @return
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session,
			@Valid @RequestBody StoreCheck checkData, BindingResult bindingResult 
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "封存失败");
		
		//校验前台数据
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		if( null != allErrors && allErrors.size() > 0 ){
			FieldError error = (FieldError)allErrors.get(0);
			result.put("msg", error.getField()+error.getDefaultMessage());
			return result;
		}
		
		try{
			//详细信息
			List<StoreCheckDetail> detailList = checkData.getDetailList();
			
			String checkOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			int checkNo = storeCheckService.submit(checkData, detailList, checkOper, storeName);
			
			result.put("data", checkNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "封存失败："+e.getMessage());	
		}
		 
		return result;
	}
	
	/**
	 * 盘点查询页面
	* @author  fengql 
	* @date 2016年4月11日 下午3:54:28 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/storeCheck/list");
		return mv;
		
	}
	
	/**
	 * 得到状态的下拉框
	* @author  fengql 
	* @date 2016年4月22日 下午3:18:29 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/getStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStatus(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dataType", "checkStatus");
			
			List<DicDataDictionary> list = commonService.getStatus(params);
			
			result.put("data", list);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());		
		}
		
		return result;
	}
	
	/**
	 * 获取盘点查询数据
	* @author  fengql 
	* @date 2016年4月11日 下午3:55:16 
	* @parameter  startTime-开始日期,yyyy-MM-dd ,endTime-结束日期,yyyy-MM-dd
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			
			if( StringUtils.isNotEmpty(startTime) ){
				Date startTimeObj = CommonUtil.parseStringToDate("yyyy-MM-dd", startTime);
				params.put("startTime", startTimeObj);
				
			}
			if( StringUtils.isNotEmpty(endTime) ){
				Date endTimeObj = CommonUtil.parseStringToDate("yyyy-MM-dd", endTime);
				params.put("endTime", endTimeObj);
				
			}
		
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<StoreCheck> list = storeCheckService.getListData(params);
			result.put("data", list);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		 
		return result;
	}
	
}
