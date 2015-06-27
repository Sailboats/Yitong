package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * 服务器端 Gift 表(礼品表)子类
 * * @author caoligai
 */
@AVClassName("Gift")
public class Gift extends AVObject {
	
	
	// 名称
	public final String GIFTNAME = "giftName";
	public String getGiftName(){
		return getString(GIFTNAME);
	} 
	public void setGiftName(String name){
		put(GIFTNAME, name);
	}
	
	
	// 缩略图
	public final String IMAGE = "image";
	public AVFile getImage(){
		return getAVFile(IMAGE);
	}
	public void setImage(AVFile file){
		put(IMAGE, file);
	}
	
	
	// 简介
	public final String SUMMARY = "summary";
	public String getSummary(){
		return getString(SUMMARY);
	}
	public void setSummary(String summary){
		put(SUMMARY, summary);
	}
	
	
	// 兑换分值
	public final String POINT = "point";
	public int getPoint(){
		return getInt(POINT);
	}
	public void setPoint(int point){
		put(POINT, point);
	}
	
	
	// 详情
	public final String DETAIL = "detail";
	public AVRelation<ImageText> getDetail(){
		return getRelation(DETAIL);
	}
	public void setDetail(AVRelation<ImageText> relation){
		put(DETAIL, relation);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
