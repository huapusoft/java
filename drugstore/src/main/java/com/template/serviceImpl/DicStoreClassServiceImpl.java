package com.template.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.DicStoreClassMapper;
import com.template.domain.DicStoreClass;
import com.template.service.DicStoreClassService;

/**
 * 库存分类serviceimpl
 * @Description: 操作库存分类相关业务方法
 * @author army.liu
 */
@Service("dicStoreClassService")
public class DicStoreClassServiceImpl implements DicStoreClassService {

	@Resource
	private DicStoreClassMapper dicStoreClassMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(DicStoreClass bean) throws Exception {
		int id = bean.getId();
		if( 0 == id ){
			dicStoreClassMapper.insert(bean);
			
		}else{
			dicStoreClassMapper.update(bean);
			
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int departmentId) throws Exception {
		dicStoreClassMapper.delete(departmentId);
		
	}

	@Override
	public List<DicStoreClass> getByConditions(Map<String, Object> params)
			throws Exception {
		return dicStoreClassMapper.getByConditions(params);
		
	}

	@Override
	public DicStoreClass getById(int id) throws Exception {
		return dicStoreClassMapper.getById(id);
	}

}
