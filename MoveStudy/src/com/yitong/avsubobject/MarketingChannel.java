package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器渠道类型表的子类
 * 
 * * @author caoligai
 */
@AVClassName("MarketingChannel")
public class MarketingChannel extends AVObject {

	
	// 渠道名称
	public final String CHANNELNAME = "channelName";
	public String getChannelName(){
		return getString(CHANNELNAME);
	}
	public void setChannelName(String name){
		put(CHANNELNAME, name);
	}
	
	
	// 上级渠道
	public final String PARENTCHANNEL = "parentChannel";
	public AVObject getParentChannel(){
		return getAVObject(PARENTCHANNEL);
	}
	public void setParentChannel(AVObject parent){
		put(PARENTCHANNEL, parent);
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
