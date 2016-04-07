package com.template.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.StoreInOutDetailMapper;
import com.template.dao.StoreInOutMapper;
import com.template.domain.StoreInOut;
import com.template.domain.StoreInOutDetail;
import com.template.service.CommonService;
import com.template.service.InStorageService;
import com.template.util.Constants;

/**
 * 入库serviceimpl
* @author  fengql 
* @date 2016年4月5日 上午10:59:41
 */
@Service("InStorageService")
public class InStorageServiceImpl implements InStorageService{
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Resource
	private CommonService commonService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception{
		// TODO Auto-generated method stub
		if(null == inOut){
			throw new RuntimeException("出入库信息为空");
		}
		int billNo = inOut.getBillNo();
		if( 0 == billNo ){
			//得到票据号
			billNo = commonService.createBillNo();
			
			//set参数，插入数据到出入库主表
			inOut.setBillNo(billNo);//票据号
			inOut.setStoreName(storeName);//药库名：从session获取
			inOut.setBillType( Constants.BusinessType.IN);//入库
			inOut.setBillTime(new Date());//入库时间
			inOut.setBillOper(billOper);//操作员
			inOut.setStatus( Constants.BusinessStatus.NEW);//草稿
			storeInOutMapper.insert(inOut);
			
			if(null == detailList){
				throw new RuntimeException("出入库明细信息为空");
			}
			
			//插入出入库明细表
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
			
			oldInOut.setBillType( Constants.BusinessType.IN );
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
	public void submit(Integer billNo) throws Exception {
		// TODO Auto-generated method stub
		if(null == billNo){
			throw new RuntimeException("票据号为空");
		}
		
		//根据票据号得到出入库主表信息
		StoreInOut inOut=storeInOutMapper.getById(billNo.intValue());
		if(null == inOut){
			throw new RuntimeException("票据号对应的主表信息为空");
		}
		
		//更新出入库主表状态-已提交
		inOut.setStatus( Constants.BusinessStatus.SUBMIT);//已提交
		inOut.setSubmitTime(new Date());//提交时间
		storeInOutMapper.update(inOut);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(Integer billNo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
