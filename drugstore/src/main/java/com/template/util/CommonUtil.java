package com.template.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.template.domain.DictEmployee;


/**
 * 公用工具类
 */
public abstract  class CommonUtil {

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
