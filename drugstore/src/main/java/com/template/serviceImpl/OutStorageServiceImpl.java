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
import com.template.service.OutStorageService;
import com.template.util.Constants;

/**
 * 出库serviceimpl
 * @Description: 提供出库业务方法
 * @author army.liu
 */
@Service("outStorageService")
public class OutStorageServiceImpl implements OutStorageService {
	
	@Resource
	private StoreInOutMapper storeInOutMapper;
	
	@Resource
	private StoreInOutDetailMapper storeInOutDetailMapper;
	
	@Resource
	private CommonService commonService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(StoreInOut inOut, List<StoreInOutDetail> detailList, String billOper, String storeName) throws Exception {
		if( null == inOut ){
			throw new RuntimeException("出入库信息为空");
		}
		
		int billNo = inOut.getBillNo();
		if( 0 == billNo ){
			//1.插入出入库主表，生成票据号
			billNo = commonService.createBillNo();
			inOut.setBillNo(billNo);//票据号
			inOut.setStoreName(storeName);//药库名：从session获取
			inOut.setBillType( Constants.BusinessType.OUT );//出库
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
			
			oldInOut.setBillType( Constants.BusinessType.OUT );
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void submit(int billNo) throws Exception {
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int billNo) throws Exception {
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

//	@Override
//	public void verify(Integer billNo, String verifyOper) {
//		// TODO Auto-generated method stub
//		
//	}

	
}
