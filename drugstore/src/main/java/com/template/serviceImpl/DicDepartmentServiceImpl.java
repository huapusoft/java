package com.template.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.DicDepartmentMapper;
import com.template.domain.DicDepartment;
import com.template.service.DicDepartmentService;

/**
 * 部门serviceimpl
 * @Description: 操作部门相关业务方法
 * @author army.liu
 */
@Service("dicDepartmentService")
public class DicDepartmentServiceImpl implements DicDepartmentService {

	@Resource
	private DicDepartmentMapper dicDepartmentMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(DicDepartment bean) throws Exception {
		int departmentId = bean.getDepartmentId();
		if( 0 == departmentId ){
			dicDepartmentMapper.insert(bean);
			
		}else{
			dicDepartmentMapper.update(bean);
			
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int departmentId) throws Exception {
		dicDepartmentMapper.delete(departmentId);
		
	}

	@Override
	public List<DicDepartment> getByConditions(Map<String, Object> params)
			throws Exception {
		return dicDepartmentMapper.getByConditions(params);
		
	}

	@Override
	public List<DicDepartment> getAllDicDepartmentList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return dicDepartmentMapper.getByConditions(params);
	}

}
