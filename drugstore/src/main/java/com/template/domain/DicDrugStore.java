package com.template.domain;

/**
 * 药库实体
 * @Description: 药库实体
 * @author army.liu
 */
public class DicDrugStore {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int id;//主键
	private String storeName;//药库名称
	private String tableName;//对应数据库表名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
