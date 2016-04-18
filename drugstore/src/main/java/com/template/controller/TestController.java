package com.template.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.template.util.POIUtil;

/**
 * 测试controller
 * 
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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mv = new ModelAndView("/test/login");

		return mv;

	}

	/**
	 * 测试主页:
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mv = new ModelAndView("/test/index");

		return mv;

	}

	/**
	 * 测试登录校验：
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ttt(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("name") String name,
			@RequestParam("password") String password) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "用户名或密码错误");

		try {
			if (StringUtils.isEmpty(name)) {
				result.put("msg", "用户名不能为空");
				return result;
			}

			if (StringUtils.isEmpty(password)) {
				result.put("msg", "密码不能为空");
				return result;
			}

			// 校验用户名密码
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("password", password);
			List<DictEmployee> employee = dicEmployeeService
					.getByConditions(params);
			if (null != employee && employee.size() > 0) {
				CommonUtil.addUserToSession(request, employee.get(0));
				result.put("code", "200");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "登录失败：" + e.getMessage());

		}

		return result;
	}

	/**
	 * 测试事务：
	 * 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> name(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("name1") String name1,
			@RequestParam("name2") String name2) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");
		result.put("msg", "用户名或密码错误");

		try {

			dicEmployeeService.test(name1, name2);
			result.put("code", "200");

		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "登录失败：" + e.getMessage());

		}

		return result;
	}

	/**
	 * 测试前后台交互-出库保存
	 * 
	 * @Description: 测试使用
	 * @author army.liu
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/transferParams", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mv = new ModelAndView("test/transferParams");

		return mv;

	}

	/**
	 * 测试前后台交互-入库保存
	 * 
	 * @Description: 测试使用
	 * @author army.liu
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/saveInStorage", method = RequestMethod.GET)
	public ModelAndView saveInStorage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mv = new ModelAndView("test/saveInStorage");

		return mv;

	}

	/**
	 * 测试导出
	 * 
	 * @Description:
	 * @author army.liu
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public void export(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> formatData = new HashMap<String, Object>();
		// sheet
		List<String> sheetList = new ArrayList<String>();
		sheetList.add("第一个sheet");
		sheetList.add("第二个sheet");
		formatData.put("sheetList", sheetList);//

		// 标题
		Map<String, Object> sheetData = new HashMap<String, Object>();
		sheetData.put("title", "标题1");//
		sheetData.put("titleMergeSize", 5);//

		// 表头
		List<String> tableHeadList = new ArrayList<String>();
		tableHeadList.add("序号");
		tableHeadList.add("药品名称");
		tableHeadList.add("批号");
		tableHeadList.add("单位");
		tableHeadList.add("厂商");
		sheetData.put("tableHeader", tableHeadList);//

		// 表数据
		List<List<Object>> tableData = new ArrayList<List<Object>>();
		List<Object> rowData = new ArrayList<Object>();
		rowData.add("1");
		rowData.add("阿莫西林胶囊");
		rowData.add("001232434");
		rowData.add("盒");
		rowData.add("国药控股药业公司");
		tableData.add(rowData);

		rowData = new ArrayList<Object>();
		rowData.add("2");
		rowData.add("阿莫西林颗粒");
		rowData.add("066778890");
		rowData.add("盒");
		rowData.add("国药控股药业公司");
		tableData.add(rowData);
		sheetData.put("tableData", tableData);//

		// 表尾
		List<String> tableFootList = new ArrayList<String>();
		tableFootList.add("");
		tableFootList.add("");
		tableFootList.add("合计：");
		tableFootList.add("2");
		tableFootList.add("");
		sheetData.put("tableFooter", tableFootList);//
		formatData.put("sheetData", sheetData);//

		String fileName = "测试导出excel";
		String fileExtend = "xls";
		POIUtil.exportToExcel(request, response, formatData, fileName,
				fileExtend);

	}

	/**
	 * 测试excel导入
	 * 
	 * @Description: 测试
	 * @author army.liu
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/excelImport", method = RequestMethod.GET)
	public ModelAndView excelImportPage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mv = new ModelAndView("test/excelImport");

		return mv;

	}

	/**
	 * 测试excel导入
	 * 
	 * @Description: 测试
	 * @author army.liu
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/excelImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> excelImport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "300");

		Map<String, Object> importResult = POIUtil.importByExcel(request, 2, 5);

		String code = (String) importResult.get("code");
		result = importResult;
		if ("200".equals(code)) {
			// TODO 数据处理
			Map<String, Object> sheetDatas=	(Map<String, Object>) importResult.get("data");
			List<List<String>> importData=(List<List<String>>) sheetDatas.get("sheetIndex");
			for(int i=0;i<importData.size();i++){
				List<String> data=importData.get(i);
			}
		}

		return result;
	}
	
	/**
	 * 测试导出药品基础信息表模板
	 * 
	 * @Description:
	 * @author army.liu
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/exportDrugBaseInfoTemplate", method = RequestMethod.GET)
	@ResponseBody
	public void exportDrugBaseInfoTemplate(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> formatData = new HashMap<String, Object>();
		// sheet
		List<String> sheetList = new ArrayList<String>();
		sheetList.add("药品基础信息数据");
		formatData.put("sheetList", sheetList);//

		// 标题
		Map<String, Object> sheetData = new HashMap<String, Object>();
		sheetData.put("title", "药品基础信息数据模板");//
		sheetData.put("titleMergeSize", 39);//

		// 表头
		List<String> tableHeadList = new ArrayList<String>();
		tableHeadList.add("序号");
		tableHeadList.add("收费类别");
		tableHeadList.add("库存类别");
		tableHeadList.add("名称");
		tableHeadList.add("规格");
		tableHeadList.add("生产商");
		tableHeadList.add("进价");
		tableHeadList.add("零售价");
		tableHeadList.add("单位");
		tableHeadList.add("门诊进价");
		tableHeadList.add("门诊零售价");
		tableHeadList.add("门诊零售单位");
		tableHeadList.add("门诊零售单位比率");
		tableHeadList.add("住院进价");
		tableHeadList.add("住院零售价");
		tableHeadList.add("住院零售单位");
		tableHeadList.add("住院零售单位比率");
		tableHeadList.add("医嘱单位");
		tableHeadList.add("医嘱单位数值");
		tableHeadList.add("医嘱单位数值单位");
		tableHeadList.add("医嘱单位门诊比率");
		tableHeadList.add("医嘱单位住院比率");
		tableHeadList.add("五笔码");
		tableHeadList.add("拼音码");
		tableHeadList.add("药品功能代码");
		tableHeadList.add("药品类别");
		tableHeadList.add("药品剂型");
		tableHeadList.add("是否为复合项目");
		tableHeadList.add("是否有自选子项目");
		tableHeadList.add("是否自选部位（专指检查项目）");
		tableHeadList.add("是否可以更改价格");
		tableHeadList.add("是否在医嘱中显示");
		tableHeadList.add("是否启用");
		tableHeadList.add("合作医疗对应码");
		tableHeadList.add("合作医疗审批标志");
		tableHeadList.add("合作医疗是否报销");
		tableHeadList.add("医疗保险对应码");
		tableHeadList.add("医疗保险门诊自理比例");
		tableHeadList.add("医疗保险住院自理比例");
		sheetData.put("tableHeader", tableHeadList);//
	
		// 表数据
		List<List<Object>> tableData = new ArrayList<List<Object>>();
		List<Object> rowData = new ArrayList<Object>();
		rowData.add("10001");
		rowData.add("1");
		rowData.add("1");
		rowData.add("红花注射液");
		rowData.add("20ml");
		rowData.add(".");
		rowData.add("10");
		rowData.add("15");
		rowData.add("支");
		rowData.add("10");
		rowData.add("15");
		rowData.add("支");
		rowData.add("1");
		rowData.add("10");
		rowData.add("15");
		rowData.add("支");
		rowData.add("1");
		rowData.add("支");
		rowData.add("20");
		rowData.add("ml");
		rowData.add("1");
		rowData.add("1");
		rowData.add("xaiti");
		rowData.add("hhzsy");
		rowData.add("1");
		rowData.add("1");
		rowData.add("1");
		rowData.add("0");
		rowData.add("0");
		rowData.add("0");
		rowData.add("0");
		rowData.add("0");
		rowData.add("1");
		rowData.add("");
		rowData.add("0");
		rowData.add("0");
		rowData.add("");
		rowData.add("0");
		rowData.add("0");
		
		tableData.add(rowData);
		sheetData.put("tableData", tableData);
		formatData.put("sheetData", sheetData);//

		String fileName = "药品基础信息维护模板";
		String fileExtend = "xls";
		POIUtil.exportToExcel(request, response, formatData, fileName,
				fileExtend);

	}


}
