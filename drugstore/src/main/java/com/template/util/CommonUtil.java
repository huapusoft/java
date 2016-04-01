package com.template.util;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	
}
