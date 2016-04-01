package com.template.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统异常处理类
 * 
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	public static final Logger logger = Logger.getLogger(MyExceptionHandler.class);
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        ex.printStackTrace();
        
        //日志文件异常信息
        StringBuffer requestURL = request.getRequestURL();
        String method = request.getMethod();
        String params = request.getParameterMap().toString();
        logger.error("**************************异常信息**************************"
        		+"\r\n******************************"
        		+"\r\n*******session_id:" + request.getSession().getId()
        		+"\r\n*******url:" + requestURL
        		+"\r\n*******method:" + method
        		+"\r\n*******params:" + params
        		+"\r\n******************************", ex);

//        // 根据不同错误转向不同页面  
//        if(ex instanceof BusinessException) {  
//            return new ModelAndView("error-business", model);  
//        }else if(ex instanceof ParameterException) {  
//            return new ModelAndView("error-parameter", model);  
//        } else {  
//            return new ModelAndView("error", model);  
//        } 
       
    	model.put("ex", ex);  
    	return new ModelAndView("fail", model);  
    	
    }  
    
}  