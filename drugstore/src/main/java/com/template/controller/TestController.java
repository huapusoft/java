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
import com.template.service.DicDrugService;
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
	
	@Resource
	private DicDrugService dicDrugService;

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

		Map<String, Object> importResult = POIUtil.importByExcel(request, 2, 39);

		String code = (String) importResult.get("code");
		//result = importResult;
		if ("200".equals(code)) {
			
			// TODO 数据处理
			try {
				result=dicDrugService.excelImport(importResult);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}

		return result;
	}
	
	/**
	 * 测试导出药品基础信息表模板-git我修改过了哦
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
		
		Map<String, Object> formatData=dicDrugService.exportDrugBaseInfoTemplate();
		String fileName = "药品基础信息维护模板";
		String fileExtend = "xls";
		POIUtil.exportToExcel(request, response, formatData, fileName,
				fileExtend);

	}

	/**
	 * 测试打印:
	 */
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ModelAndView print(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mv = new ModelAndView("/test/print");

		return mv;

	}
}
