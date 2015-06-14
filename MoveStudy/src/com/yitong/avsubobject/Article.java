package com.yitong.avsubobject;

import java.util.Date;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * 
 * 鏈嶅姟鍣ㄦ枃绔犺〃瀛愮被
 * 
 * * @author caoligai
 */
@AVClassName("Article")
public class Article extends AVObject {

	
	// 鏍囬
	public final String TITLE = "title";
	public String getTitle(){
		return getString(TITLE);
	}
	public void setTitle(String title){
		put(TITLE, title);
	}
	
	
	// 绠�堪
	public final String SUMMARY = "summary";
	public String getSummary(){
		return getString(SUMMARY);
	}
	public void setSummary(String summary){
		put(SUMMARY, summary);
	}
	
	
	// 鍙戣〃鏃堕棿
	public final String DATE = "date";
	public Date getDate(){
		return getDate(DATE);
	}
	public void setDate(Date date){
		put(DATE, date);
	}
	
	
	// 鏉ユ簮
	public final String SOURCE = "source";
	public String getSource(){
		return getString(SOURCE);
	}
	public void setSource(String source){
		put(SOURCE, source);
	}
	
	
	
	// 骞垮憡浣嶅浘鐗�	
	public final String HEADERIMAGEFILE = "headerImageFile";
	public AVFile getHeaderImageFile(){
		return getAVFile(HEADERIMAGEFILE);
	}
	public void setHeaderImageFile(AVFile file){
		put(HEADERIMAGEFILE, file);
	}
	
	
	// 鍒楄〃浣嶅浘鐗�	
	public final String LISTIMAGEFILE = "listImageFile";
	public AVFile getListImageFile(){
		return getAVFile(LISTIMAGEFILE);
	}
	public void setListImageFile(AVFile file){
		put(LISTIMAGEFILE, file);
	}
	
	
	// 姝ｆ枃鍥剧墖闆�	
	public final String IMAGETEXT = "imagetext";
	public AVRelation<AVObject> getImagetext(){
		return getRelation(IMAGETEXT);
	}
	public void setImagetext(AVRelation<AVObject> relation){
		put(IMAGETEXT, relation);
	}
	
	
	// 鏍囩
	public final String TAG = "tag";
	public String getTag(){
		return getString(TAG);
	}
	public void setTag(String tag){
		put(TAG, tag);
	}
	
	
	
}
