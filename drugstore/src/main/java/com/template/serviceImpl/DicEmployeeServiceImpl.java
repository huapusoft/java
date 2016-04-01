package com.template.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.DicEmployeeMapper;
import com.template.domain.DictEmployee;
import com.template.service.DicEmployeeService;

/**
 * 
 */
@Service("dicEmployeeService")
public class DicEmployeeServiceImpl implements DicEmployeeService {
	@Resource
	private DicEmployeeMapper dicEmployeeMapper;
	
	@Override
	public DictEmployee getById(String id) {
		try {
			return dicEmployeeMapper.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DictEmployee> getByConditions(Map<String, Object> params) {
		try {
			return dicEmployeeMapper.getByConditions(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void test(String name1, String name2) {
		try {
			//Test transactional
			DictEmployee bean = new DictEmployee();
			bean.setName(name1);
			bean.setJobNumber("1007");
			dicEmployeeMapper.add(bean);
			
			int i = 1 / 0;
			System.out.println(i);
			
			bean.setName(name2);
			bean.setJobNumber("1008");
			dicEmployeeMapper.add(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		
	}

}
