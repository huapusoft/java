package com.template.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.DicDrugMapper;
import com.template.dao.StoreMapper;
import com.template.domain.DicDrug;
import com.template.domain.Store;
import com.template.service.DicDrugService;

/**
 * 库存分类serviceimpl
 * @Description: 操作库存分类相关业务方法
 * @author army.liu
 */
@Service("dicDrugService")
public class DicDrugServiceImpl implements DicDrugService {

	@Resource
	private DicDrugMapper dicDrugMapper;
	
	@Resource
	private StoreMapper storeMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(DicDrug bean) throws Exception {
		int id = bean.getId();
		if( 0 == id ){
			dicDrugMapper.insert(bean);
			
		}else{
			int enabled = bean.getEnabled();
			if( 0 == enabled ){
				//检查库存表，若其中存在，则不可以删除
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("drugId", id);
				List<Store> store = storeMapper.getByConditions(params);
				if( null != store && store.size() > 0 ){
					throw new RuntimeException("库存中存在此药品，不可停用");
				}
				
			}
			dicDrugMapper.update(bean);
			
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) throws Exception {
		//检查库存表，若其中存在，则不可以删除
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("drugId", id);
		List<Store> store = storeMapper.getByConditions(params);
		if( null != store && store.size() > 0 ){
			throw new RuntimeException("库存中存在此药品，不可删除");
		}
		
		dicDrugMapper.delete(id);
		
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
		params.put("enabled", 1);
		return dicDrugMapper.getByConditions(params);
	}

	@Override
	public DicDrug getById(int id) throws Exception {
		return dicDrugMapper.getById(id);
	}

}
