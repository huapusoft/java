package com.template.util;

/**
 * 公共常量类。
 */
public abstract  class Constants {

	// 默认时间格式
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// 默认时间简写格式
	public static final String DATE_TIME_FORMAT_SHORT = "yyyy-MM-dd";

	//申报状态
	public class AuditStatus{
		public static final int NEW = 0;//待审核
		
		public static final int SUCCESS= 1;//审核通过
		
		public static final int FAIL = -1;//审核不通过
		
		public static final int RETURN = -2;//退回
		
	}
	

	
}
