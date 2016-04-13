package com.template.controller.query;

import java.io.IOException;
import java.util.Date;
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
import com.template.domain.DrugAndInOutStatistics;
import com.template.domain.DrugAndStoreInOutDetail;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.util.CommonUtil;

/**
 * 查询统计：进出统计Controller
 * @Description: 提供进出统计记录
 * @author army.liu
 */
@Controller
@RequestMapping("/inOutStatistics")
public class InOutStatisticsController {
	
	@Resource  
	private CommonService commonService;
	
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
		ModelAndView mv = new ModelAndView("queryCount/inOutStatistics/list");
		return mv;
		
	}
	
	/**
	 * 获取药品下拉框数据，从出入库明细表中获取
	* @author  fengql 
	* @date 2016年4月12日 下午2:54:21 
	* @parameter  itemName-药品名称
	* @return
	 */
	@RequestMapping(value = "/getDrugListFromInOutDetail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugListFromInOutDetail(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("itemName") String itemName
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			List<DrugAndStoreInOutDetail> list = commonService.getDrugListFromInOutDetail(storeName, itemName);
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
	 * 根据药品id，获取批号
	* @author  fengql 
	* @date 2016年4月12日 下午3:27:06 
	* @parameter  drugId-药品id
	* @return
	 */
	@RequestMapping(value = "/getBatchNoFromInOutDetail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBatchNoFromInOutDetail(HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("drugId") int drugId
			) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "获取失败");
		
		try{
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			List<StoreInOutDetail> list = commonService.getBatchNoFromInOutDetail(storeName, drugId);
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
	* @author  fengql 
	* @date 2016年4月12日 下午3:32:25 
	* @parameter  startTime-开始日期,yyyy-MM-dd，endTime-结束日期,yyyy-MM-dd，itemName-药品名称，batchNo-批号
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
			@RequestParam("batchNo") String batchNo
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
			params.put("batchNo", batchNo);
			String storeName = CommonUtil.getStoreNameFromSession(request);//药库名称
			params.put("storeName", storeName);
			
			List<DrugAndInOutStatistics> list = commonService.getInOutStatisticsDetail(params);
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
