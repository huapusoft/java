package com.template.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.template.dao.DicCompatibilityMapper;
import com.template.dao.DicDrugClassMapper;
import com.template.dao.DicDrugFunctionMapper;
import com.template.domain.DicCompatibility;
import com.template.domain.DicDrugClass;
import com.template.domain.DicDrugFunction;
import com.template.service.DicCompatibilityService;

/**
 * 配伍serviceimpl
* @author  fengql 
* @date 2016年4月14日 上午10:17:12
 */
@Service("dicCompatibilityService")
public class DicCompatibilityServiceImpl implements DicCompatibilityService {

	@Resource
	private DicDrugFunctionMapper dicDrugFunctionMapper;
	
	@Resource
	private DicDrugClassMapper dicDrugClassMapper;
	
	@Resource
	private DicCompatibilityMapper dicCompatibilityMapper;

	@Override
	public List<DicDrugFunction> getfunctionCode() throws Exception {
		return dicDrugFunctionMapper.getByConditions(null);
	}

	@Override
	public List<DicDrugClass> getdrugClassId() throws Exception {
		return dicDrugClassMapper.getByConditions(null);
	}

	@Override
	public void save(DicCompatibility bean) throws Exception {
		int id=bean.getId();
		if(0 == id){
			//新增
			dicCompatibilityMapper.insert(bean);
		}else{
			//更新
			dicCompatibilityMapper.update(bean);
		}
	}

	@Override
	public void delete(int id) throws Exception {
		dicCompatibilityMapper.delete(id);
	}

	@Override
	public List<DicCompatibility> getByConditions(Map<String, Object> params)
			throws Exception {
		return dicCompatibilityMapper.getByConditions(params);
	}

	@Override
	public DicCompatibility getById(int id) throws Exception {
		return dicCompatibilityMapper.getById(id);
	}
	

}
