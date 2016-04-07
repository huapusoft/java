package com.template.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.template.dao.DicDrugStoreMapper;
import com.template.dao.StoreCheckMapper;
import com.template.dao.StoreInOutDetailMapper;
import com.template.dao.StoreInOutMapper;
import com.template.dao.StoreMapper;
import com.template.dao.StorePurchasePlanMapper;
import com.template.domain.DicDrugStore;
import com.template.domain.Store;
import com.template.domain.StoreCheck;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.domain.StoreInOutForCount;
import com.template.domain.StorePurchasePlanForCount;
import com.template.service.CommonService;
import com.template.util.CommonUtil;
import com.template.util.Constants;

/**
 * 公共serviceimpl
 * @Description: 提供公共业务方法
 * @author army.liu
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreCheckMapper storeCheckMapper;
	
	@Resource
	private StorePurchasePlanMapper storePurchasePlanMapper;
	
	@Resource
	private DicDrugStoreMapper dicDrugStoreMapper;
	
	@Resource
	private StoreMapper storeMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub

	}

	@Override
	public int createBillNo() throws Exception {
		String billNoSuffix = "";
		String currYearMonth = CommonUtil.getCurrYearMonth();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currYearMonth", currYearMonth);
		List<StoreInOut> list = storeInOutMapper.getByConditions(params);
		if( null != list && list.size() > 0 ){
			int oldBillNo = list.get(0).getBillNo();
			billNoSuffix = String.valueOf( Integer.parseInt( String.valueOf(oldBillNo).substring(6) ) + 1 );
			
		}else{
			billNoSuffix = "1000";
		}
		
		return Integer.parseInt(currYearMonth + billNoSuffix);
	}

	@Override
	public List<DicDrugStore> getAllDicDrugStore() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return dicDrugStoreMapper.getByConditions(params);
	}

	@Override
	public String getLatestLoginUserName() throws Exception {
		//TODO
		return null;
	}

	@Override
	public Map<String, Object> getWaitingDataCount(String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("inSum0", 0);//入库草稿
		result.put("inSum1", 0);//入库已提交
		result.put("inSum3", 0);//入库驳回
		result.put("outSum0", 0);//出库草稿
		result.put("outSum1", 0);//出库已提交
		result.put("outSum3", 0);//出库驳回
		result.put("adjustPriceSum0", 0);//调价草稿
		result.put("adjustPriceSum1", 0);//调价已提交
		result.put("adjustPriceSum3", 0);//调价驳回
		result.put("salesRetSum0", 0);//退货草稿
		result.put("salesRetSum1", 0);//退货已提交
		result.put("salesRetSum3", 0);//调价驳回
		result.put("breakageSum0", 0);//报损草稿
		result.put("breakageSum1", 0);//报损已提交
		result.put("breakageSum3", 0);//报损驳回
		result.put("storeCheckSum0", 0);//盘点草稿
		result.put("storePurchasePlanSum0", 0);//采购计划草稿
		result.put("storePurchasePlanSum1", 0);//采购计划已提交
		result.put("storePurchasePlanSum3", 0);//采购计划复核驳回
		result.put("storePurchasePlanSum5", 0);//采购计划领导驳回
		try {
			//查询入库，出库，退货，调价，报损的统计数据
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("billOper", name);
			List<StoreInOutForCount> list = storeInOutMapper.getWaitingDataCount(params);
			if( null != list && list.size() > 0 ){
				for(int i=0; i<list.size(); i++){
					StoreInOutForCount bean = list.get(i);
					String billType = bean.getBillType();
					int sum0 = bean.getSum0();
					int sum1 = bean.getSum1();
					int sum3 = bean.getSum3();
					if( Constants.BusinessType.IN.equals(billType) ){
						result.put("inSum0", sum0);//草稿
						result.put("inSum1", sum1);//已提交
						result.put("inSum3", sum3);//驳回
					}else if( Constants.BusinessType.OUT.equals(billType) ){
						result.put("outSum0", sum0);//出库草稿
						result.put("outSum1", sum1);//出库已提交
						result.put("outSum3", sum3);//出库驳回
					}else if( Constants.BusinessType.ADJUST_PRICE.equals(billType) ){
						result.put("adjustPriceSum0", sum0);//草稿
						result.put("adjustPriceSum1", sum1);//已提交
						result.put("adjustPriceSum3", sum3);//驳回
					}else if( Constants.BusinessType.SALES_RET.equals(billType) ){
						result.put("salesRetSum0", sum0);//草稿
						result.put("salesRetSum1", sum1);//已提交
						result.put("salesRetSum3", sum3);//驳回
					}else if( Constants.BusinessType.BREAKAGE.equals(billType) ){
						result.put("breakageSum0", sum0);//草稿
						result.put("breakageSum1", sum1);//已提交
						result.put("breakageSum3", sum3);//驳回
					}
				}
			}
			
			//查询盘点的统计数据
			params = new HashMap<String, Object>();
			params.put("checkOper", name);
			params.put("statusFlag", "0");
			List<StoreCheck> pdList = storeCheckMapper.getByConditions(params);
			if( null != pdList && pdList.size() > 0 ){
				result.put("storeCheckSum0", pdList.size());//草稿
			}
			
			//查询采购计划的统计数据
			params = new HashMap<String, Object>();
			params.put("oper", name);
			List<StorePurchasePlanForCount> planList = storePurchasePlanMapper.getWaitingDataCount(params);
			if( null != planList && planList.size() > 0 ){
				for(int i=0; i<planList.size(); i++){
					StorePurchasePlanForCount bean = planList.get(i);
					String status = bean.getStatus();
					int total = bean.getTotal();
					
					if( Constants.BusinessStatus.NEW.equals(status) ){
						result.put("storePurchasePlanSum0", total);//采购计划草稿
					}else if( Constants.BusinessStatus.SUBMIT.equals(status) ){
						result.put("storePurchasePlanSum1", total);//采购计划已提交
					}else if( Constants.BusinessStatus.VERIFY_FAIL.equals(status) ){
						result.put("storePurchasePlanSum3", total);//采购计划复核驳回
					}else if( Constants.BusinessStatus.LEADER_FAIL.equals(status) ){
						result.put("storePurchasePlanSum5", total);//采购计划领导驳回
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Store> getDrugsForStockout() {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("stockOutFlag", "Y");
			return storeMapper.getByConditions(params);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Store> getDrugsForDeadline() {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deadlineFlag", "Y");
			return storeMapper.getByConditions(params);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean validateStoreName(String storeName) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeName", storeName);
			List<DicDrugStore> list = dicDrugStoreMapper.getByConditions(params);
			if( null == list || list.size() == 0 ){
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public void saveStoreInOut(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception {
		if( null == inOut ){
			throw new RuntimeException("出入库信息为空");
		}
		
		int billNo = inOut.getBillNo();
		if( 0 == billNo ){
			//1.插入出入库主表，生成票据号
			billNo = this.createBillNo();
			inOut.setBillNo(billNo);//票据号
			inOut.setStoreName(storeName);//药库名：从session获取
			inOut.setBillTime(new Date());//创建时间
			inOut.setBillOper(billOper);//操作员
			inOut.setStatus( Constants.BusinessStatus.NEW );//草稿
			storeInOutMapper.insert(inOut);
			
			//2.获取票据号，插入出入库明细表
			if( null == detailList ){
				throw new RuntimeException("出入库明细信息为空");
			}
			for(int i=0; i<detailList.size(); i++){
				StoreInOutDetail detail = detailList.get(i);
				detail.setBillNo(billNo);
				storeInOutDetailMapper.insert(detail);
				
			}
			
		}else{
			//1.更新出入库主表
			StoreInOut oldInOut = storeInOutMapper.getById(billNo);
			if( null == oldInOut ){
				throw new RuntimeException("出入库信息不存在");
			}
			//检查状态
			if( Constants.BusinessStatus.SUBMIT.equals( inOut.getStatus() ) 
					|| Constants.BusinessStatus.VERIFY_SUCCESS.equals( inOut.getStatus() )
					|| Constants.BusinessStatus.LEADER_SUCCESS.equals( inOut.getStatus() )
				){
				throw new RuntimeException("不是草稿状态，不可更新");
				
			}
			
			oldInOut.setTypeData( inOut.getTypeData() );
			oldInOut.setSum1( inOut.getSum1() );
			oldInOut.setSum2( inOut.getSum2() );
			oldInOut.setStatus(Constants.BusinessStatus.NEW);
			oldInOut.setStoreName(storeName);
			oldInOut.setBillOper(billOper);
			storeInOutMapper.update(oldInOut);
			
			//2.删除原来的入库明细信息，重新插入出入库明细表
			if( null == detailList ){
				throw new RuntimeException("出入库明细信息为空");
			}
			
			//2.1删除出入库明细表
			storeInOutDetailMapper.delete(billNo);
			
			//2.2重新插出入库明细表
			for(int i=0; i<detailList.size(); i++){
				StoreInOutDetail detail = detailList.get(i);
				detail.setBillNo(billNo);
				storeInOutDetailMapper.insert(detail);
				
			}
			
			
		}
		
	}
	
	@Override
	public void submitStoreInOut(int billNo) throws Exception {
		if( 0 == billNo ){
			throw new RuntimeException("票据号为空");
		}
		StoreInOut inOut = storeInOutMapper.getById(billNo);
		if( null == inOut ){
			throw new RuntimeException("票据号对应的数据为空");
		}
		//更新出入库主表
		inOut.setStatus( Constants.BusinessStatus.SUBMIT);//已提交
		inOut.setSubmitTime(new Date());//提交时间
		storeInOutMapper.update(inOut);
		
	}

	@Override
	public void deleteStoreInOut(int billNo) throws Exception {
		if( 0 == billNo ){
			throw new RuntimeException("票据号为空");
		}
		
		StoreInOut inOut = storeInOutMapper.getById(billNo);
		if( null != inOut ){
			if( Constants.BusinessStatus.SUBMIT.equals( inOut.getStatus() ) 
					|| Constants.BusinessStatus.VERIFY_SUCCESS.equals( inOut.getStatus() )
					|| Constants.BusinessStatus.LEADER_SUCCESS.equals( inOut.getStatus() )
				){
				throw new RuntimeException("不是草稿状态，不可删除");
				
			}
		}
		
		//删除出入库主表
		storeInOutMapper.delete(billNo);
		
		//删除出入库详细表
		storeInOutDetailMapper.delete(billNo);
		
	}
	
}
