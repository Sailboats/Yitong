package com.yitong.avsubobject;


import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * 
 * 服务器 sku 表的子类
 * 
 * * @author caoligai
 */

@AVClassName("SKU")
public class Sku extends AVObject {



	// 图片
	public final String SKU_IMAGE = "image";
	public AVFile getImage() {
		return getAVFile(SKU_IMAGE);
	}
	public void setImage(AVFile file) {
		put(SKU_IMAGE, file);
	}

	// sku 名称
	public final String SKU_NAME = "skuName";
	public String getName() {
		return getString(SKU_NAME);
	}
	public void setName(String name){
		put(SKU_NAME, name);
	}
	
	// EAN13 码
	public final String COMMODITY_CODE = "commodityCode";
	public String getCommodityCode(){
		return getString(COMMODITY_CODE);
	}
	public void setCommodityCode(String code){
		put(COMMODITY_CODE, code);
	}
	
	
	// 包装类型
	public final String PACKINGSPECIFICATION = "packingSpecification";
	public AVRelation<PackingSpecification> getPackingSpecification(){
		return getRelation(PACKINGSPECIFICATION);
	}
	public void setPackingSpecification(AVRelation<PackingSpecification> relation){
		put(PACKINGSPECIFICATION, relation);
	}
	
	
	// 所属品牌
	public final String BRAND = "brand";
	public AVObject getBrand(){
		return getAVObject(BRAND);
	}
	public void setBrand(AVObject brand){
		put(BRAND, brand);
	}
	
	
}
