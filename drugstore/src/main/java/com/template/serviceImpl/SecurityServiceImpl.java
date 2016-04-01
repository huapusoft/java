package com.template.serviceImpl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.template.service.SecurityService;

/**
 * 安全服务实现
 * 
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	static {
		publicUris.add("/login"); // 登录页面
		publicUris.add("/staticPublic"); // 公用图片 样式 脚本
		publicUris.add("/add"); // test
	}

	@Override
	public boolean isPublicUri(String uri) {
		if (uri.equals("/"))
			return true;
		for (String _uri : publicUris) {
			if (uri.startsWith(_uri)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, Integer> getAllUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyUserUri(String uri, HttpSession session) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean verifyUserData(String uri, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		return true;
	}

}
