package com.template.domain;

/**
 * 库存分类信息实体
 * @Description: 库存分类信息实体
 * @author army.liu
 */
public class DicStoreClass {

	private int id;//标识（唯一）

    private String name;//分类名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
