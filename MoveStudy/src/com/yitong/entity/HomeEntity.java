package com.yitong.entity;

import java.io.File;

/**
 * 首页“新闻”列表实体
 * * @author caoligai
 */
public class HomeEntity {
	
	private String id;	// 服务器端 objectId
	
	private byte[] image;	// 新闻一级列表图片
	
	private String title;	// 新闻标题
	
	private String summary;		// 简述
	
	public HomeEntity(String id,byte[] image,String title,String summary){
		this.id = id;
		this.image = image;
		this.title = title;
		this.summary = summary;
	}
	

	public HomeEntity() {
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
