package com.yitong.avsubobject;

import java.util.Date;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器进货表子类
 * 
 * * @author caoligai
 */
@AVClassName("Purchases")
public class Purchases extends AVObject {

	
	// SKU
	public final String SKU = "sku";
	public Sku getSku(){
		return getAVObject(SKU);
	}
	public void setSku(Sku sku){
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
	
	
	// 进货前库存
	public final String STOCKCOUNTBEFOREPURCHASE = "stockCountBeforePurchase";
	public int getStockCountBeforePurchase(){
		return getInt(STOCKCOUNTBEFOREPURCHASE);
	}
	public void setStockCountBeforePurchase(int count){
		put(STOCKCOUNTBEFOREPURCHASE, count);
	}
	
	
	// 进货数量
	public final String COUNT = "count";
	public int getCount(){
		return getInt(COUNT);
	}
	public void setCount(int count){
		put(COUNT, count);
	}
	
	
	// 进货后库存
	public final String STOCKCOUNTAFTERPURCHASE = "stockCountAfterPurchase";
	public int getStockCountAfterPurchase(){
		return getInt(STOCKCOUNTAFTERPURCHASE);
	}
	public void setStockCountAfterPurchase(int count){
		put(STOCKCOUNTAFTERPURCHASE, count);
	}
	
	
	// 录入时间
	public final String DATE = "date";
	public Date getDate(){
		return getDate(DATE);
	}
	public void setDate(Date date){
		put(DATE, date);
	}
	
}
