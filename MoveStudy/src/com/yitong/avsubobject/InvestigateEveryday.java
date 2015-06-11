package com.yitong.avsubobject;

import java.util.Date;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * 
 * 服务器每日一评的子类
 * 
 * * @author caoligai
 */
@AVClassName("InvestigateEveryday")
public class InvestigateEveryday extends AVObject {

	
	// 终端店
	public final String STORE = "store";
	public AVObject getStore(){
		return getAVObject(STORE);
	}
	public void setStore(AVObject store){
		put(STORE, store);
	}
	
	
	// 品牌
	public final String BRAND = "brand";
	public AVObject getBrand(){
		return getAVObject(BRAND);
	}
	public void setBrand(AVObject brand){
		put(BRAND, brand);
	}
	
	
	// 满意度
	public final String SATISFACTION = "satisfaction";
	public int getSatisfaction(){
		return getInt(SATISFACTION);
	}
	public void setStisfaction(int satisfaction){
		put(SATISFACTION, satisfaction);
	}
	
	
	// 今日促销方式
	public final String PROMOTION = "promotion";
	public AVRelation<AVObject> getPromotion(){
		return getRelation(PROMOTION);
	}
	public void setPromotion(AVRelation<AVObject> relation){
		put(PROMOTION, relation);
	}
	
	
	// 陈列面
	public final String DISPLAYSURFACEIMAGES = "displaySurfaceImages";
	public AVRelation<AVObject> getDisplaySurfaceImages(){
		return getRelation(DISPLAYSURFACEIMAGES);
	}
	public void setDisplaySurfaceImages(AVRelation<AVObject> relation){
		put(DISPLAYSURFACEIMAGES, relation);
	}
	
	
	// 提交日期
	public final String DATE = "date";
	public Date getDate(){
		return getDate(DATE);
	}
	public void setDate(Date date){
		put(DATE, date);
	}
	

	
	
}
