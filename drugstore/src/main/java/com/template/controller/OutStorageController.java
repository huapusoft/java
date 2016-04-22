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
import com.template.domain.DicDepartment;
import com.template.domain.DicDrug;
import com.template.domain.DrugAndStore;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.DicDepartmentService;
import com.template.service.DicDrugService;
import com.template.service.OutStorageService;
import com.template.util.CommonUtil;
import com.template.util.Constants;

/**
 * 出库controller
 * @Description: 提供方法与前台页面交互:出库登记，修改，提交，作废等
 * @author army.liu
 */
@Controller
@RequestMapping("/outStorage")
public class OutStorageController {

	@Resource 
	private OutStorageService outStorageService;
	
	@Resource 
	private DicDepartmentService dicDepartmentService;
	
	@Resource 
	private CommonService commonService;
	
	@Resource  
	private DicDrugService dicDrugService;
	
	/**
	 * 出库登记页面
	 * @Description: 出库登记页面
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)		
	public ModelAndView news(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("dailyWork/outStorage/new");
		
		return mv;
		
	}
	
	/**
	  * 获取部门数据
	  * @Description: 从部门表中，获取所有部门数据
	  * @author army.liu
	  * @param  
	  * @return
	  * @throws
	  */
	@RequestMapping(value = "/getAllDicDepartmentList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllDicDepartmentList(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			List<DicDepartment> list = dicDepartmentService.getAllDicDepartmentList();
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
	 * @Description: 从药品库存表中，读取药品数据
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getDrugListFromStore",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugListFromStore(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			List<DrugAndStore> list = commonService.getDrugListForOutStorage(storeName, itemName);
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
	  * 保存出库草稿
	  * @Description: 保存出库草稿
	  * @author army.liu
	  * @param  
	  * @return 
	  * @throws
	  */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(
			@Valid @RequestBody StoreInOut inOut,BindingResult bindingResult,
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
			int billNo = outStorageService.save(inOut, detailList, billOper, storeName);
			result.put("code", "200");
			result.put("msg", "成功");
			result.put("data", billNo);
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "保存失败："+e.getMessage());
			
		}
		 
		return result;
	}
	
	/**
	 * 提交出库草稿
	 * @Description: 提交出库草稿
	 * @author army.liu
	 * @param  
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/submit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submit(
			@Valid @RequestBody StoreInOut inOut,BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response,HttpSession session
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
			//先保存
			List<StoreInOutDetail> detailList = inOut.getDetailList();
			String billOper = CommonUtil.getUserNameFromSession(request);//操作员
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			int billNo = outStorageService.save(inOut, detailList, billOper, storeName);
			
			//再提交
			outStorageService.submit(billNo);
			
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
	 * 作废出库草稿
	 * @Description: 作废出库草稿
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
			outStorageService.delete(billNo);
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
		ModelAndView mv = new ModelAndView("queryCount/outStorage/list");
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
	 *  获取已启用的药品信息--用于出库查询页面的名称下拉
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
	  * 获取查询页面的列表数据
	  * @Description: 获取查询页面的列表数据
	  * @author army.liu
	  * @param  startTime-开始日期,yyyy-MM-dd
	  * 		endTime-结束日期,yyyy-MM-dd
	  *         departmentName-部门名称
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
			@RequestParam("departmentName") String departmentName,
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
			params.put("typeData", departmentName);
			params.put("itemName", itemName);
			params.put("status", status);
			
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<StoreInOut> list = outStorageService.getListData(params);
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
	 * 编辑出库登记的页面
	 * @Description: 编辑入库登记的页面
	 * @author army.liu
	 * @param  billNo-票据号
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.GET)		
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("queryCount/outStorage/edit");
		String billNo = request.getParameter("billNo");
		mv.addObject("billNo", billNo);
		
		return mv;
	}
	
	/**
	  * 获取出库草稿的详细信息
	  * @Description: 获取出库草稿的详细信息
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
			StoreInOut bean = outStorageService.getDetailData( billNo );
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
