package com.template.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.template.dao.StoreCheckDetailMapper;
import com.template.dao.StoreCheckMapper;
import com.template.dao.StoreMapper;
import com.template.domain.DrugAndCheckDetail;
import com.template.domain.DrugAndStore;
import com.template.domain.Store;
import com.template.domain.StoreCheck;
import com.template.domain.StoreCheckDetail;
import com.template.service.CommonService;
import com.template.service.StoreCheckService;
/**
 * 盘点serviceimpl
* @author  fengql 
* @date 2016年4月7日 下午4:59:13
 */
@Service("StoreCheckService")
public class StoreCheckServiceImpl implements StoreCheckService{
	
	@Resource
	private StoreMapper storeMapper;
	
	@Resource
	private StoreCheckMapper storeCheckMapper;
	
	@Resource
	private StoreCheckDetailMapper storeCheckDetailMapper;
	
	@Resource
	private CommonService commonService;

	@Override
	public List<DrugAndStore> getStoreDrugList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return storeMapper.getDrugAndStoreDataList(params);
	}
	
	@Override
	public List<DrugAndCheckDetail> getCheckDetailList(int checkNo) throws Exception {
		
		//判断盘点号的相关内容
		if( 0 == checkNo ){
			throw new RuntimeException("盘点号为空");
		}
		StoreCheckDetail checkDetail = storeCheckDetailMapper.getByCheckNo(checkNo);
		if( null == checkDetail ){
			throw new RuntimeException("盘点号对应的明细数据为空");
		}	
		String sealOper=storeCheckMapper.getByCheckNo(checkNo).getSealOper();
		if( null != sealOper ){
			throw new RuntimeException("该盘点号已经封存");
		}
		
		//获取当前盘点号的明细数据	
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("checkNo", checkNo);	
		return storeCheckDetailMapper.getCheckDetailList(params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(StoreCheck checkData, List<StoreCheckDetail> detailList, String checkOper, String storeName) throws Exception {
		
		if( null == checkData ){
			throw new RuntimeException("盘点信息为空");
		}
		
		int checkNo = checkData.getCheckNo();
		if( 0 == checkNo ){
			//插入盘点主表，生成盘点号
			checkNo = commonService.createCheckNo();
			
			checkData.setCheckNo(checkNo);//盘点号
			checkData.setStoreName(storeName);//药库名：从session获取
			checkData.setCheckTime(new Date());//创建时间
			checkData.setCheckOper(checkOper);//操作员
			
			storeCheckMapper.insert(checkData);
			
			//插入盘点明细表
			if( null == detailList ){
				throw new RuntimeException("盘点明细信息为空");
			}
			for(int i=0; i<detailList.size(); i++){
				StoreCheckDetail detail = detailList.get(i);
				detail.setCheckNo(checkNo);
				storeCheckDetailMapper.insert(detail);			
			}
			
		}else{
			//更新盘点主表
			StoreCheck oldCheckData = storeCheckMapper.getByCheckNo(checkNo);
			if( null == oldCheckData ){
				throw new RuntimeException("盘点信息不存在");
			}
			
			oldCheckData.setStoreName(storeName);
			oldCheckData.setCheckTime(new Date());//创建时间
			oldCheckData.setInSum(checkData.getInSum());
			oldCheckData.setRetailSum(checkData.getRetailSum());
			oldCheckData.setCheckRetailSum(checkData.getCheckRetailSum());
			oldCheckData.setCheckOper(checkOper);

			storeCheckMapper.update(oldCheckData);
			
			//删除原来的盘点明细信息，重新插入盘点明细表
			if( null == detailList ){
				throw new RuntimeException("盘点明细信息为空");
			}
			
			storeCheckDetailMapper.delete(checkNo);
			
			for(int i=0; i<detailList.size(); i++){
				StoreCheckDetail detail = detailList.get(i);
				detail.setCheckNo(checkNo);
				storeCheckDetailMapper.insert(detail);			
			}
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void submit(StoreCheck checkData, List<StoreCheckDetail> detailList, String checkOper, String storeName) throws Exception {
		
		//先将页面中的内容进行保存
		this.save(checkData, detailList, checkOper, storeName);
		
		//根据明细表中的内容更新库存表中的数量
		for(int i=0; i<detailList.size(); i++){
			StoreCheckDetail detail = detailList.get(i);
			int drugId=detail.getDrugId();
			String batchNo=detail.getBatchNo();
			double realAmount=detail.getRealAmount();
			double inPrice=detail.getInPrice();
			double price=detail.getPrice();
			
			Store store =new Store();
			
			store.setStoreName(storeName);//药库名称
			store.setDrugId(drugId);//药品id
			store.setBatchNo(batchNo);//批号
			store.setInPrice(inPrice);//进价
			store.setPrice(price);//零售价
			store.setAmount(realAmount);//实际数量

			storeMapper.updateCheck(store);		
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int checkNo) throws Exception {
		
		if( 0 == checkNo ){
			throw new RuntimeException("盘点号为空");
		}
		
		String sealOper=storeCheckMapper.getByCheckNo(checkNo).getSealOper();
		if( null != sealOper ){
			throw new RuntimeException("该盘点号已经封存,不可作废");
		}
		
		//删除盘点主表
		storeCheckMapper.delete(checkNo);
		
		//删除盘点明细表
		storeCheckDetailMapper.delete(checkNo);
		
	}
	
}
