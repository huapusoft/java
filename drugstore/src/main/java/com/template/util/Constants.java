package com.template.util;

/**
 * 公共常量类
 */
public abstract  class Constants {

	// 默认时间格式
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// 默认时间简写格式
	public static final String DATE_TIME_FORMAT_SHORT = "yyyy-MM-dd";

	//业务类型
	public class BusinessType{
		public static final String IN = "入库";//入库
		public static final String OUT = "出库";//出库
		public static final String ADJUST_PRICE = "调价";//调价
		public static final String SALES_RETURN = "退货";//退货
		public static final String BREAKAGE = "报损";//报损
		
	}
	
	//业务状态
	public class BusinessStatus{
		public static final String NEW = "0";//草稿
		public static final String SUBMIT = "1";//已提交
		public static final String VERIFY_SUCCESS = "2";//复核通过
		public static final String VERIFY_FAIL = "3";//复核驳回
		public static final String LEADER_SUCCESS = "4";//领导审批通过
		public static final String LEADER_FAIL = "5";//领导审批驳回
		
	}
	
}
