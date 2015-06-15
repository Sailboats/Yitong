package com.yitong.avsubobject;

import java.util.Date;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器库存表的子类
 * 
 * * @author caoligai
 */
@AVClassName("Stock")
public class Stock extends AVObject {

	
	// SKU 
	public final String SKU = "sku";
	public AVObject getSku(){
		return getAVObject(SKU);
	}
	public void setSku(AVObject sku){
		put(SKU, sku);
	}
	
	
	// 终端店
	public final String STORE = "store";
	public TerminalStore getStore(){
		return getAVObject(STORE);
	}
	public void setStore(TerminalStore store){
		put(STORE, store);
	}
	
	
	// 数量
	public final String STOCK = "stock";
	public int getStock(){
		return getInt(STOCK);
	}
	public void setStock(int stock){
		put(STOCK, stock);
	}
	
	
	// 录入时间
	public final String DATE = "date";
	public Date getDate(){
		return getDate(DATE);
	}
	public void setDate(Date date){
		put(DATE, date);
	}
	
	
	// 数据来源
	public final String UPDATEFROM = "updateFrom";
	public int getUpdateFrom(){
		return getInt(UPDATEFROM);
	}
	public void setUpdateFrom(int from){
		put(UPDATEFROM, from);
	}
	
	
	
}
