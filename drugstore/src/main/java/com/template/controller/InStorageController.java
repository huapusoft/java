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

import com.template.domain.DicDrug;
import com.template.domain.DicProvider;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.DicDrugService;
import com.template.service.DicProviderService;
import com.template.service.InStorageService;
import com.template.util.CommonUtil;
import com.template.util.Constants;

/**
 * 入库Controller
* @author  fengql 
* @date 2016年4月5日 上午10:44:39
 */
@Controller
@RequestMapping("/inStorage")
public class InStorageController {
	
	@Resource  
	private InStorageService inStorageService;
	
	@Resource  
	private DicProviderService dicProviderService;
	
	@Resource  
	private DicDrugService dicDrugService;
	
	/**
	 * 入库登记页面
	 * @Description: 入库登记页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/inStorage/new");
		return mv;
		
	}
	
	/**
	  * 获取供应商下拉数据
	  * @Description: 从供应商表中，获取供应商下拉数据
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/getEnabledDicProviderList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEnabledDicProviderList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("providerName") String providerName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DicProvider> list = dicProviderService.getEnabledDicProviderList(providerName);
			
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
	 * 获取药品下拉框中数据
	 * @Description: 从药品基础信息表中，读取已启用的药品数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getEnabledDrugList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDicProviderList(HttpServletRequest request, 
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
	
//	/**暂时不用，直接从在在库表中获取
//	 * 获取选中药品的最近一次进价，零售价
//	 * @Description: 获取选中药品的最近一次进价，零售价，用于添加某个药品后，自动回显进价和零售价
//	 * @author army.liu
//	 * @param  
//	 * @return
//	 * @throws
//	 */
//	@RequestMapping(value = "/getDrugLatestPrice",method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getDrugLatestPrice(HttpServletRequest request, 
//			HttpServletResponse response,
//			HttpSession session,
//			@RequestParam("id") int id
//			) throws Exception {
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("code", "300");
//		result.put("msg", "获取失败");
//		
//		try{
//			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
//			Map<String, Object> data = inStorageService.getDrugLatestPrice( storeName, id);
//			result.put("data", data);
//			result.put("code", "200");
//			result.put("msg", "成功");
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
	  * 保存入库草稿
	  * @Description: 保存入库草稿
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(
			@Valid @RequestBody StoreInOut inOut,BindingResult bindingResult,
//			@Valid @RequestBody StoreInOut inOut,Errors errors,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			
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
			int billNo = inStorageService.save(inOut, detailList, billOper, storeName);
			
			result.put("data", billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
//	/**
//	  * 数据校验不符合时，返回前台ajax的错误信息 
//	  * 
//	  * @Description: 方法功能描述
//	  * @author army.liu
//	  * @date 2016年4月20日 下午4:30:13
//	 */
//	@ExceptionHandler  
//    @ResponseBody  
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
//    public String handleException(MethodArgumentNotValidException exception) { 
//		BindingResult bindingResult = exception.getBindingResult();
//		List<ObjectError> allErrors = bindingResult.getAllErrors();
//		if( null != allErrors && allErrors.size() > 0 ){
//			FieldError error = (FieldError)allErrors.get(0);
//			return error.getField()+error.getDefaultMessage();
//		}
//		return "数据校验错误";
//    } 

	/**
	 * 提交入库草稿
	 * @Description: 提交入库草稿
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestBody StoreInOut inOut
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "提交失败");
		
		try{
			List<StoreInOutDetail> detailList = inOut.getDetailList();
			String billOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			int billNo = inStorageService.save(inOut, detailList, billOper, storeName);
			
			inStorageService.submit(billNo);
			
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
	 * 作废入库草稿
	 * @Description: 作废入库草稿
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam("billNo")int billNo
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "作废失败");
		
		try{
			inStorageService.delete(billNo);
			result.put("code", "200");
			result.put("msg", "成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "作废失败："+e.getMessage());
			
		}
		
		return result;
	}
	
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
		ModelAndView mv = new ModelAndView("queryCount/inStorage/list");
		return mv;
		
	}
	
	/**
	  * 获取查询页面的列表数据
	  * @Description: 获取查询页面的列表数据
	  * @author army.liu
	  * @param  startTime-开始日期,yyyy-MM-dd
	  * 		endTime-结束日期,yyyy-MM-dd
	  *         providerName-供应商名称
	  *         itemName-药品名称
	  *         status-状态
	  * @return 
	  * @throws
	  */
	@RequestMapping(value = "/getListData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("providerName") String providerName,
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
			params.put("typeData", providerName);
			params.put("itemName", itemName);
			params.put("status", status);
			
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<StoreInOut> list = inStorageService.getListData(params);
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
	 * 编辑入库登记的页面
	 * @Description: 编辑入库登记的页面
	 * @author army.liu
	 * @param  billNo-票据号
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/inStorage/edit");
		String billNo = request.getParameter("billNo");
		mv.addObject("billNo", billNo);
		
		return mv;
	}
	
	/**
	  * 获取入库草稿的详细信息
	  * @Description: 获取入库草稿的详细信息
	  * @author army.liu
	  * @param  billNo-票据号
	  * @return {
	  * 			
	  * 			detailAndDrugList : [ ] --详细信息
	  * 		}
	  * @throws
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
			StoreInOut bean = inStorageService.getDetailData( billNo );
			if( null != bean ){
				String status = bean.getStatus();
				if( Constants.BusinessStatus.NEW.equals(status.trim())
						|| Constants.BusinessStatus.VERIFY_FAIL.equals(status.trim()) ){
					result.put("data", bean);
					result.put("code", "200");
					result.put("msg", "成功");
					
				}else{
					result.put("msg", "当前数据已提交或已复核！");
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "获取失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
}
