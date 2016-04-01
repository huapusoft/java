package com.template.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.template.service.SecurityService;

/**
 * 验证权限拦截器
 * 
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Resource
	private SecurityService securityService;

	// preHandle()方法在业务处理器处理请求之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 1.检查是否需要登录，跳转到登录页. "/login"是不需要登录的
		String uri = request.getRequestURI();
		if (securityService.isPublicUri(uri)) {
			return true;
		}
		
		// 2.虽然登录了，但是uri可能不给访问
		String url = "/login";
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			if (!securityService.verifyUserUri(uri, session)) {
				response.sendRedirect(url);
				return false;
				
			}
		} else {
			response.sendRedirect(url);
			return false;
			
		}

		// 3.虽然uri能访问但是检查参数后发现请求的数据不给访问
		if (!securityService.verifyUserData(uri, request, session)) {
			response.sendRedirect(url);
			return false;
		}

		return true;
	}

	// postHandle()方法在业务处理器处理请求之后被调用
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	// afterCompletion()方法在DispatcherServlet完全处理完请求后被调用
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}