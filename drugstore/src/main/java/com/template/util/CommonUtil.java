package com.template.util;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.template.domain.DictEmployee;


/**
 * 公用工具类
 */
public abstract  class CommonUtil {

	/**
	 * 设置文件下载头信息
	 * 
	 * @param request
	 * @param response
	 * @param fileName 文件名: test
	 * @param fileExtend 文件类型 ：xls
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static void addDownloadHeader(HttpServletRequest request, 
											HttpServletResponse response,
											String fileName, 
											String fileExtend) throws Exception{
		
		response.setContentType("application/octet-stream");
		response.setStatus(response.SC_OK);
		
		String agent = request.getHeader( "USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE")) { // IE
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ URLEncoder.encode(fileName, "UTF-8") + "." + fileExtend);
			
		} else if (null != agent && -1 != agent.indexOf( "Mozilla")) { // FireFox,Chrome,360
			 String codedFileName = new String(fileName.getBytes("UTF-8"),
                             									"ISO8859-1");
			response.setHeader( "Content-Disposition",
					"attachment; filename=" + codedFileName + "." + fileExtend);
		} 
		
	}

	/**
	 * 用户登录时，将用户存入session
	 * @param dictEmployee
	 */
	public static void addUserToSession(HttpServletRequest request, DictEmployee dictEmployee) {
		request.getSession().setAttribute("user", dictEmployee);
		
	}

	/**
	 * 用户退出时，清除session
	 * @param request
	 */
	public static void removeUserFromSession(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}
	
	/**
	  * 获取session中用户名称
	  * 
	  * @Description: 在用户登录时，将用户以user存入session
	  * @author army.liu
	  * @date 2016年4月5日 上午11:05:19
	 */
	public static String getUserNameFromSession(HttpServletRequest request) {
		if( null != request.getSession().getAttribute("user") ){
			DictEmployee user = (DictEmployee)request.getSession().getAttribute("user");
			if( null != user ){
				return user.getName();
			}
			
		}
		return null;
	}
	
	/**
	  * 获取session中药库名称
	  * 
	  * @Description: 在用户登录时，将选择的药库名称，存入用户的roleInfo字段，并将用户以user存入session
	  * @author army.liu
	  * @date 2016年4月5日 上午11:05:19
	 */
	public static String getStoreNameFromSession(HttpServletRequest request) {
		if( null != request.getSession().getAttribute("user") ){
			DictEmployee user = (DictEmployee)request.getSession().getAttribute("user");
			if( null != user ){
				return user.getRoleInfo();
			}
			
		}
		return null;
	}
	

	/**
	  * 获取当前系统时间的年月
	  * 
	  * @Description: 获取当前系统时间的年月，格式yyyyMM
	  * @author army.liu
	  * @date 2016年4月5日 上午11:05:19
	 */
	public static String getCurrYearMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
		
	}

	/**
	  * 转化字符串为日期
	  * 
	  * @Description: 转化字符串为日期
	  * @author army.liu
	  * @date 2016年4月5日 上午11:05:19
	 */
	public static Date parseStringToDate(String formatStr, String startTime) {
		if( StringUtils.isNotEmpty(formatStr) && StringUtils.isNotEmpty(startTime) ){
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			try {
				return sdf.parse(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}

}
