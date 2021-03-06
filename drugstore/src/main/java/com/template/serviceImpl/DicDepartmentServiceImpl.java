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
			//获取当前部门code
			String departmentCode="";
			if(null == bean.getParentCode()){
				bean.setParentCode("0");
			}
			DicDepartment dicDepartment=dicDepartmentMapper.getByParentCode(bean.getParentCode());
			if(null == dicDepartment){
				departmentCode=bean.getParentCode()+"10";
			}else{
				departmentCode = String.valueOf( Integer.parseInt( dicDepartment.getDepartmentCode().trim()) + 1 );
			}
			bean.setDepartmentCode(departmentCode);
			dicDepartmentMapper.insert(bean);
		}else{
			dicDepartmentMapper.update(bean);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(String departmentCode) throws Exception {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("departmentCode", departmentCode);
		List<DicDepartment> department = dicDepartmentMapper.getByConditions(params);
		if(department.size()>1){
			throw new RuntimeException("当前部门存在子部门，不可以删除！");
		}
		dicDepartmentMapper.delete(departmentCode);
		
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

	@Override
	public DicDepartment getByDepartmentId(int departmentId) throws Exception {
		return dicDepartmentMapper.getById(departmentId);
	}

}
