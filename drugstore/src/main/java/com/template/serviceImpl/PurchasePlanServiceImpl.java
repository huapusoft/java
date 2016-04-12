package com.template.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.template.dao.StorePurchasePlanDetailMapper;
import com.template.dao.StorePurchasePlanMapper;
import com.template.domain.DrugAndPurchasePlanDetail;
import com.template.domain.StorePurchasePlan;
import com.template.domain.StorePurchasePlanDetail;
import com.template.service.CommonService;
import com.template.service.PurchasePlanService;
import com.template.util.Constants;

/**
 * 采购计划serviceimpl
* @author  fengql 
* @date 2016年4月11日 上午9:37:16
 */
@Service("purchasePlanService")
public class PurchasePlanServiceImpl implements PurchasePlanService{
	
	@Resource
	private StorePurchasePlanMapper storePurchasePlanMapper;
	
	@Resource
	private StorePurchasePlanDetailMapper storePurchasePlanDetailMapper;

	@Resource
	private CommonService commonService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(StorePurchasePlan purchaseData, List<StorePurchasePlanDetail> detailList, String oper, String storeName) throws Exception {
		
		if( null == purchaseData ){
			throw new RuntimeException("采购计划信息为空");
		}
		
		int purchaseNo = purchaseData.getPurchaseNo();
		if( 0 == purchaseNo ){
			//插入采购计划主表，生成采购号
			purchaseNo = commonService.createPurchaseNo();
			
			purchaseData.setPurchaseNo(purchaseNo);//采购号
			purchaseData.setStoreName(storeName);//药库名：从session获取
			purchaseData.setPurchaseTime(new Date());//制定时间
			purchaseData.setOper(oper);//操作员
			purchaseData.setStatus( Constants.BusinessStatus.NEW );//草稿
			
			storePurchasePlanMapper.insert(purchaseData);
			
			//插入采购计划明细表
			if( null == detailList ){
				throw new RuntimeException("采购计划明细信息为空");
			}
			for(int i=0; i<detailList.size(); i++){
				StorePurchasePlanDetail detail = detailList.get(i);
				detail.setPurchaseNo(purchaseNo);
				storePurchasePlanDetailMapper.insert(detail);			
			}
			
		}else{
			//更新采购计划主表
			StorePurchasePlan oldPurchaseData = storePurchasePlanMapper.getByPurchaseNo(purchaseNo);
			if( null == oldPurchaseData ){
				throw new RuntimeException("采购计划信息不存在");
			}
			
			//检查状态
			if( Constants.BusinessStatus.SUBMIT.equals( purchaseData.getStatus() ) 
					|| Constants.BusinessStatus.VERIFY_SUCCESS.equals( purchaseData.getStatus() )
					|| Constants.BusinessStatus.LEADER_SUCCESS.equals( purchaseData.getStatus() )
				){
				throw new RuntimeException("不是草稿、驳回状态，不允许更新");
				
			}
			
			oldPurchaseData.setRemark(purchaseData.getRemark());
			oldPurchaseData.setInSum(purchaseData.getInSum());
			oldPurchaseData.setRetailSum(purchaseData.getRetailSum());
			oldPurchaseData.setStatus( Constants.BusinessStatus.NEW );//草稿

			storePurchasePlanMapper.update(oldPurchaseData);
			
			//删除原来的采购计划明细表，重新插入
			if( null == detailList ){
				throw new RuntimeException("采购计划明细信息为空");
			}
			
			storePurchasePlanDetailMapper.delete(purchaseNo);
			
			for(int i=0; i<detailList.size(); i++){
				StorePurchasePlanDetail detail = detailList.get(i);
				detail.setPurchaseNo(purchaseNo);
				storePurchasePlanDetailMapper.insert(detail);			
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void submit(int purchaseNo) throws Exception {
		if( 0 == purchaseNo ){
			throw new RuntimeException("采购号为空");
		}
		StorePurchasePlan purchaseData = storePurchasePlanMapper.getByPurchaseNo(purchaseNo);
		if( null == purchaseData ){
			throw new RuntimeException("采购号对应的数据为空");
		}
		//检查状态
		if( Constants.BusinessStatus.SUBMIT.equals( purchaseData.getStatus() ) 
				|| Constants.BusinessStatus.VERIFY_SUCCESS.equals( purchaseData.getStatus() )
				|| Constants.BusinessStatus.LEADER_SUCCESS.equals( purchaseData.getStatus() )
			){
			throw new RuntimeException("不是草稿、驳回状态，不允许提交");
			
		}
		//更新采购计划主表
		purchaseData.setStatus( Constants.BusinessStatus.SUBMIT);//已提交
		purchaseData.setSubmitTime(new Date());//提交时间
		purchaseData.setFinanceOper(null);
		purchaseData.setFinanceTime(null);
		purchaseData.setLeader(null);
		purchaseData.setLeaderTime(null);
		storePurchasePlanMapper.update(purchaseData);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int purchaseNo) throws Exception {
		if( 0 == purchaseNo ){
			throw new RuntimeException("采购号为空");
		}
		
		StorePurchasePlan purchaseData = storePurchasePlanMapper.getByPurchaseNo(purchaseNo);
		if( null != purchaseData ){
			if( Constants.BusinessStatus.SUBMIT.equals( purchaseData.getStatus() ) 
					|| Constants.BusinessStatus.VERIFY_SUCCESS.equals( purchaseData.getStatus() )
					|| Constants.BusinessStatus.LEADER_SUCCESS.equals( purchaseData.getStatus() )
				){
				throw new RuntimeException("不是草稿、驳回状态，不允许废除");
				
			}
		}
		
		//删除采购计划主表
		storePurchasePlanMapper.delete(purchaseNo);
		
		//删除采购计划明细表
		storePurchasePlanDetailMapper.delete(purchaseNo);
	}

	@Override
	public void financeAudit(int purchaseNo, String oper, int auditType)
			throws Exception {
		if( 0 == purchaseNo ){
			throw new RuntimeException("采购号为空");
		}
		StorePurchasePlan purchaseData = storePurchasePlanMapper.getByPurchaseNo(purchaseNo);
		if( null == purchaseData ){
			throw new RuntimeException("采购号对应的数据为空");
		}
		
		//检查状态
		if( !Constants.BusinessStatus.SUBMIT.equals( purchaseData.getStatus()  )){
				throw new RuntimeException("不是已提交状态，财务不可审核");
			}
		//更新采购计划主表
		if(1 == auditType){
			purchaseData.setStatus( Constants.BusinessStatus.VERIFY_SUCCESS);//财务审核通过
		}else{
			purchaseData.setStatus( Constants.BusinessStatus.VERIFY_FAIL);//财务审核驳回
		}
		purchaseData.setFinanceOper(oper);
		purchaseData.setFinanceTime(new Date());//财务审批时间
		storePurchasePlanMapper.update(purchaseData);
		
	}

	@Override
	public void leaderAudit(int purchaseNo, String oper, int auditType)
			throws Exception {
		if( 0 == purchaseNo ){
			throw new RuntimeException("采购号为空");
		}
		StorePurchasePlan purchaseData = storePurchasePlanMapper.getByPurchaseNo(purchaseNo);
		if( null == purchaseData ){
			throw new RuntimeException("采购号对应的数据为空");
		}
		
		//检查状态
		if( !Constants.BusinessStatus.VERIFY_SUCCESS.equals( purchaseData.getStatus()  )){
				throw new RuntimeException("不是财务审核通过状态，领导不可审核");
			}
		//更新采购计划主表
		if(1 == auditType){
			purchaseData.setStatus( Constants.BusinessStatus.LEADER_SUCCESS);//领导审核通过
		}else{
			purchaseData.setStatus( Constants.BusinessStatus.LEADER_FAIL);//领导审核驳回
		}
		purchaseData.setLeader(oper);
		purchaseData.setLeaderTime(new Date());//领导审批时间
		storePurchasePlanMapper.update(purchaseData);
		
	}

	@Override
	public List<StorePurchasePlan> getListData(Map<String, Object> params)
			throws Exception {
		List<StorePurchasePlan> list = storePurchasePlanMapper.getByConditionsForQuery(params);
		if( null != list && list.size() > 0 ){
			for(int i=0; i<list.size(); i++){
				int purchaseNo = list.get(i).getPurchaseNo();
				List<DrugAndPurchasePlanDetail> detailList = storePurchasePlanDetailMapper.getPurchaseDetailList(purchaseNo);
				list.get(i).setDetailAndDrugList(detailList);
			}
			return list;
		}		
		return null;
	}

	@Override
	public StorePurchasePlan getDetailData(int purchaseNo) throws Exception {
		StorePurchasePlan purchaseData = storePurchasePlanMapper.getByPurchaseNo(purchaseNo);
		if( null != purchaseData ){
			List<DrugAndPurchasePlanDetail> detailList = storePurchasePlanDetailMapper.getPurchaseDetailList(purchaseNo);	
			purchaseData.setDetailAndDrugList(detailList);
			return purchaseData;
		}	
		return null;
	}

}
