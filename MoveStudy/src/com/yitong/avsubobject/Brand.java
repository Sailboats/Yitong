package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器 Brand 表的子类
 * 
 * * @author caoligai
 */
@AVClassName("Brand")
public class Brand extends AVObject {

	
	// 品牌名称
	public final String BRANDNAME = "brandName";
	public String getBrandName(){
		return getString(BRANDNAME);
	}
	public void setBrandName(String name){
		put(BRANDNAME, name);
	}
	
	
	
	
	
}
