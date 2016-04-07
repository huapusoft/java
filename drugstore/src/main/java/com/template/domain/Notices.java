package com.template.domain;

import java.util.Date;

/**
 * 公告实体
 * @Description: 公告实体
 * @author army.liu
 */
public class Notices {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int id;//主键,自增
	private String title;//标题
	private String message;//内容
	private Date deadline;//有效期
	private String releaseMan;//发布人
	private Date releaseTime;//发布日期
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getReleaseMan() {
		return releaseMan;
	}
	public void setReleaseMan(String releaseMan) {
		this.releaseMan = releaseMan;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
}
