package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * 
 * 服务器终端店表的子类
 * 
 * * @author caoligai
 */
@AVClassName("TerminalStore")
public class TerminalStore extends AVObject {

	// 店主
	public final String SHOPKEEPER = "shopKeeper";
	public MyUser getShopKeeper() {
		return getAVUser(SHOPKEEPER, MyUser.class);
	}
	public void setShopKeeper(MyUser user) {
		put(SHOPKEEPER, user);
	}

	
	// 代理品牌
	public final String BRAND = "brand";
	public AVRelation<AVObject> getBrand() {
		return getRelation(BRAND);
	}
	public void setBrand(AVRelation<AVObject> relation) {
		put(BRAND, relation);
	}

	
	// SKU
	public final String SKU = "sku";
	public AVRelation<AVObject> getSku() {
		return getRelation(SKU);
	}
	public void setSku(AVRelation<AVObject> relation) {
		put(SKU, relation);
	}

	
	// 店名
	public final String STORENAME = "storeName";
	public String getStoreName() {
		return getString(STORENAME);
	}
	public void setStoreName(String name) {
		put(STORENAME, name);
	}

	
	// 地址
	public final String ADDRESS = "address";
	public String getAddress() {
		return getString(ADDRESS);
	}
	public void setAddress(String address) {
		put(ADDRESS, address);
	}

	
	// 地理位置
	public final String LOCATION = "location";
	public AVGeoPoint getLocation() {
		return getAVGeoPoint(LOCATION);
	}
	public void setLocation(AVGeoPoint geoPoint) {
		put(LOCATION, geoPoint);
	}
	

	// 电话
	public final String TELNO = "telNo";
	public String getTelNo() {
		return getString(TELNO);
	}
	public void setTelNo(String telNo) {
		put(telNo, telNo);
	}

	
	// 渠道类型
	public final String CHANNEL = "channel";
	public AVObject getChannel() {
		return getAVObject(CHANNEL);
	}
	public void setChannel(AVObject channel) {
		put(CHANNEL, channel);
	}

	
	// 门脸照片
	public final String SHOPFRONTPHOTO = "shopFrontPhoto";
	public AVFile getShopFrontPhoto() {
		return getAVFile(SHOPFRONTPHOTO);
	}
	public void setShopFrontPhoto(AVFile file) {
		put(SHOPFRONTPHOTO, file);
	}
	
	
	// 营业执照
	public final String BUSINESSLICENSENO = "businessLicenseNo";
	public String getBusinessLicenseNo(){
		return getString(BUSINESSLICENSENO);
	}
	public void setBusinessLicenseNo(String businessLicenseNo){
		put(businessLicenseNo, businessLicenseNo);
	}

	
	
	
}
