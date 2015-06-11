package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器促销方式表子类
 * 
 * * @author caoligai
 */
@AVClassName("Promotion")
public class Promotion extends AVObject {

	
	// 名称
	public final String PROMOTIONNAME = "promotionName";
	public String getPromotionName(){
		return getString(PROMOTIONNAME);
	}
	public void setPromotionName(String name){
		put(PROMOTIONNAME, name);
	}
	
	
	// 排序 
	public final String ORDER = "order";
	public int getOrder(){
		return getInt(ORDER);
	}
	public void setOrder(int order){
		put(ORDER, order);
	}
	
}
