package com.template.serviceImpl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.template.dao.DicCompatibilityDetailMapper;
import com.template.dao.DicCompatibilityMapper;
import com.template.dao.DicDrugClassMapper;
import com.template.dao.DicDrugFunctionMapper;
import com.template.domain.DicCompatibility;
import com.template.domain.DicCompatibilityDetail;
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
	
	@Resource
	private DicCompatibilityDetailMapper dicCompatibilityDetailMapper;

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
		
		if( null == bean ){
			throw new RuntimeException("配伍信息为空");
		}
		
		int id=bean.getId();
		if(0 == id){//新增
			
			//插入主表
			dicCompatibilityMapper.insert(bean);
			int comId=bean.getId();
			
			//插入明细
			List<DicCompatibilityDetail> detailList=bean.getDetailList();
			if( null == detailList ){
				throw new RuntimeException("配伍明细信息为空");
			}
			for(int i=0; i<detailList.size(); i++){
				DicCompatibilityDetail detail = detailList.get(i);
				detail.setComId(comId);
				dicCompatibilityDetailMapper.insert(detail);
			}
			
		}else{//更新
			
			//更新配伍主表
			dicCompatibilityMapper.update(bean);
			
			int comId=bean.getId();
			//删除配伍明细表
			dicCompatibilityDetailMapper.delete(comId);
			
			//重新插入配伍明细表
			List<DicCompatibilityDetail> detailList=bean.getDetailList();
			if( null == detailList ){
				throw new RuntimeException("配伍明细信息为空");
			}
			for(int i=0; i<detailList.size(); i++){
				DicCompatibilityDetail detail = detailList.get(i);
				detail.setComId(comId);
				dicCompatibilityDetailMapper.insert(detail);
			}
		}
	}

	@Override
	public void delete(int id) throws Exception {
		//删除配伍主表
		dicCompatibilityMapper.delete(id);
		//删除配伍明细表
		dicCompatibilityDetailMapper.delete(id);
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
