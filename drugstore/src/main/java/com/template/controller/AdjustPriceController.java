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
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.AdjustPriceService;
import com.template.service.CommonService;
import com.template.service.DicDrugService;
import com.template.util.CommonUtil;
import com.template.util.Constants;

/**
 * 调价Controller
* @author  fengql 
* @date 2016年4月7日 下午1:39:48
 */
@Controller
@RequestMapping("/adjustPrice")
public class AdjustPriceController {
	
	@Resource  
	private AdjustPriceService adjustPriceService;
	
	@Resource  
	private DicDrugService dicDrugService;
	
	@Resource  
	private CommonService commonService;
	
	/**
	 * 调价登记页面
	* @author  fengql 
	* @date 2016年4月7日 下午1:40:51 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/adjustPrice/new");
		return mv;
		
	}
	
	/**
	 * 获取药品名称下拉框内容，从库存表中读取-用于药品调价页面药品下拉
	* @author  fengql 
	* @date 2016年4月7日 下午1:49:31 
	* @parameter  itemName-页面输入的名称代码
	* @return
	 */
	@RequestMapping(value = "/getStoreDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStoreDrugList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DrugAndStore>  drugAndStore= adjustPriceService.getStoreDrugList(itemName);
			result.put("code", "200");
			result.put("data", drugAndStore);
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());		
		}
		
		return result;
	}

	/**
	 * 保存调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午3:09:30 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@Valid @RequestBody StoreInOut inOut,BindingResult bindingResult
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
			List<StoreInOutDetail> detailList = inOut.getDetailList();
			
			String billOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			int billNo = adjustPriceService.save(inOut, detailList, billOper, storeName);
			
			result.put("data", billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());		
		}
		 
		return result;
	}
	
	/**
	 * 提交调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午3:14:49 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@Valid @RequestBody StoreInOut inOut,BindingResult bindingResult
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "提交失败");
		
		//校验前台数据
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		if( null != allErrors && allErrors.size() > 0 ){
			FieldError error = (FieldError)allErrors.get(0);
			result.put("msg", error.getField()+error.getDefaultMessage());
			return result;
		}
		
		try{
			//详细信息
			List<StoreInOutDetail> detailList = inOut.getDetailList();
			
			String billOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			int billNo = adjustPriceService.save(inOut, detailList, billOper, storeName);
			
			adjustPriceService.submit(billNo);
			
			result.put("data", billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "提交失败："+e.getMessage());	
		}
		
		return result;
	}
	
	/**
	 * 作废调价草稿
	* @author  fengql 
	* @date 2016年4月7日 下午3:15:36 
	* @parameter  billNo-票据号
	* @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("billNo") int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			adjustPriceService.delete(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());	
		}
		
		return result;
	}
	
	/**
	 * 调价查询页面
	* @author  fengql 
	* @date 2016年4月11日 下午2:46:31 
	* @parameter  
	* @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)		
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/adjustPrice/list");
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
			params.put("dataType", "inOutStatus");
			
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
	 *  获取已启用的药品信息--用于调价查询页面的名称下拉
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
	 * 获取查询数据
	* @author  fengql 
	* @date 2016年4月11日 下午2:48:58 
	* @parameter  startTime-开始日期,yyyy-MM-dd
	     		  endTime-结束日期,yyyy-MM-dd
	              itemName-药品名称
	              status-状态
	* @return
	 */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("itemName") String itemName,
			@RequestParam("status") String status
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
			params.put("itemName", itemName);
			params.put("status", status);
			
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<StoreInOut> list = adjustPriceService.getListData(params);
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
	 * 编辑调价页面
	* @author  fengql 
	* @date 2016年4月11日 下午3:01:33 
	* @parameter  billNo-票据号
	* @return
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/adjustPrice/edit");
		String billNo = request.getParameter("billNo");
		mv.addObject("billNo", billNo);
		return mv;
	}
	
	/**
	 * 获取调价草稿的详细信息
	* @author  fengql 
	* @date 2016年4月11日 下午3:03:34 
	* @parameter   billNo-票据号
	* @return detailAndDrugList[] 详细信息
	 */
	@RequestMapping(value = "/getDetailData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("billNo") int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			StoreInOut bean = adjustPriceService.getDetailData( billNo );
			if( null != bean ){
				int status = bean.getStatus();
				if( Constants.BusinessStatus.NEW == status
						|| Constants.BusinessStatus.VERIFY_FAIL == status ){
					result.put("data", bean);
					result.put("code", "200");
					result.put("msg", "成功");
					
				}else{
					result.put("msg", "当前数据已提交或已复核！");
				}
				
			}
			
			result.put("data", bean);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
		}
		 
		return result;
	}
	
}
