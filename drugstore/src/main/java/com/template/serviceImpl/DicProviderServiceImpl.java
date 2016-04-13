package com.template.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.template.dao.DicProviderMapper;
import com.template.domain.DicProvider;
import com.template.service.DicProviderService;

/**
 * 供应商serviceimpl
 * @Description: 操作供应商相关业务方法
 * @author army.liu
 */
@Service("dicProviderService")
public class DicProviderServiceImpl implements DicProviderService {

	@Resource
	private DicProviderMapper dicProviderMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(DicProvider bean) throws Exception {
		dicProviderMapper.insert(bean);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(DicProvider bean) throws Exception {
		dicProviderMapper.update(bean);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(DicProvider bean) throws Exception {
		int id = bean.getId();
		if( 0 == id ){
			bean.setEnabled(1);
			dicProviderMapper.insert(bean);
		}else{
			dicProviderMapper.update(bean);		
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) throws Exception {
		dicProviderMapper.delete(id);
	}

	@Override
	public List<DicProvider> getByConditions(Map<String, Object> params)
			throws Exception {
		return dicProviderMapper.getByConditions(params);
		
	}

	@Override
	public List<DicProvider> getEnabledDicProviderList(String providerName) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("providerName", providerName);//模糊查询
		params.put("enabled", 1);//启用
		return dicProviderMapper.getByConditions(params);
	}

	@Override
	public DicProvider getById(int id) throws Exception {
		return dicProviderMapper.getById(id);
	}

	@Override
	public void updateStatus(int id, int status) throws Exception {
		dicProviderMapper.updateStatus(id,status);
	}

}
