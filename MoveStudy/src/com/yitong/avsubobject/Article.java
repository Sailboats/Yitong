package com.yitong.avsubobject;

import java.util.Date;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * 
 * 服务器文章表子类
 * 
 * * @author caoligai
 */
@AVClassName("Article")
public class Article extends AVObject {

	
	// 标题
	public final String TITLE = "title";
	public String getTitle(){
		return getString(TITLE);
	}
	public void setTitle(String title){
		put(TITLE, title);
	}
	
	
	// 简述
	public final String SUMMARY = "summary";
	public String getSummary(){
		return getString(SUMMARY);
	}
	public void setSummary(String summary){
		put(SUMMARY, summary);
	}
	
	
	// 发表时间
	public final String DATE = "date";
	public Date getDate(){
		return getDate(DATE);
	}
	public void setDate(Date date){
		put(DATE, date);
	}
	
	
	// 来源
	public final String SOURCE = "source";
	public String getSource(){
		return getString(SOURCE);
	}
	public void setSource(String source){
		put(SOURCE, source);
	}
	
	
	
	// 广告位图片
	public final String HEADERIMAGEFILE = "headerImageFile";
	public AVFile getHeaderImageFile(){
		return getAVFile(HEADERIMAGEFILE);
	}
	public void setHeaderImageFile(AVFile file){
		put(HEADERIMAGEFILE, file);
	}
	
	
	// 列表位图片
	public final String LISTIMAGEFILE = "listImageFile";
	public AVFile getListImageFile(){
		return getAVFile(LISTIMAGEFILE);
	}
	public void setListImageFile(AVFile file){
		put(LISTIMAGEFILE, file);
	}
	
	
	// 正文图片集
	public final String IMAGETEXT = "imagetext";
	public AVRelation<AVObject> getImagetext(){
		return getRelation(IMAGETEXT);
	}
	public void setImagetext(AVRelation<AVObject> relation){
		put(IMAGETEXT, relation);
	}
	
	
	// 标签
	public final String TAG = "tag";
	public String getTag(){
		return getString(TAG);
	}
	public void setTag(String tag){
		put(TAG, tag);
	}
	
	
	
}
