package com.template.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.NoticesMapper;
import com.template.domain.Notices;
import com.template.service.NoticesService;

/**
 * 系统公告serviceimpl
 * @Description: 操作系统公告相关业务方法
 * @author army.liu
 */
@Service("noticesService")
public class NoticesServiceImpl implements NoticesService {

	@Resource
	private NoticesMapper noticesMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(Notices bean) throws Exception {
		noticesMapper.insert(bean);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(Notices bean) throws Exception {
		noticesMapper.update(bean);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) throws Exception {
		noticesMapper.delete(id);
		
	}

	@Override
	public List<Notices> getByConditions(Map<String, Object> params)
			throws Exception {
		return noticesMapper.getByConditions(params);
		
	}

	@Override
	public List<Notices> getEnabledNotices() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deadline", new Date());
		return noticesMapper.getByConditions(params);
	}

}
