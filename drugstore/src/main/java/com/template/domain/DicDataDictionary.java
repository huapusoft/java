package com.template.domain;

/**
 * 数据字典实体
* @author  fengql 
* @date 2016年4月22日 下午2:28:44
 */
public class DicDataDictionary {

	private int id;//id，自增长
	private String dataType;//数据类型
	private String dataTypeName;//数据类型说明
	private int dataId;//数据类型id
	private String dataIdName;//数据类型id说明
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataTypeName() {
		return dataTypeName;
	}
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataIdName() {
		return dataIdName;
	}
	public void setDataIdName(String dataIdName) {
		this.dataIdName = dataIdName;
	}
	
}
