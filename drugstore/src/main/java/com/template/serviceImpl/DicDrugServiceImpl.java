package com.template.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.DicDrugMapper;
import com.template.domain.DicDrug;
import com.template.service.DicDrugService;

/**
 * 部门serviceimpl
 * @Description: 操作部门相关业务方法
 * @author army.liu
 */
@Service("dicDrugService")
public class DicDrugServiceImpl implements DicDrugService {

	@Resource
	private DicDrugMapper dicDrugMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(DicDrug bean) throws Exception {
		int id = bean.getId();
		if( 0 == id ){
			dicDrugMapper.insert(bean);
			
		}else{
			dicDrugMapper.update(bean);
			
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int departmentId) throws Exception {
		dicDrugMapper.delete(departmentId);
		
	}

	@Override
	public List<DicDrug> getByConditions(Map<String, Object> params)
			throws Exception {
		return dicDrugMapper.getByConditions(params);
		
	}

	@Override
	public List<DicDrug> getEnabledDrugList(String itemName) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemName", itemName);
		return dicDrugMapper.getByConditions(params);
	}

}
