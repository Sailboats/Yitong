package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器 PackingSpecification 表的子类
 * 
 * * @author caoligai
 */
@AVClassName("PackingSpecification")
public class PackingSpecification extends AVObject {

	
	// 包装名称
	public final String PACKINGNAME = "packingName";
	public String getPackingName(){
		return getString(PACKINGNAME);
	}
	public void setPackingName(String packingName){
		put(packingName, packingName);
	}
	
	
	// 单位
	public final String UNIT = "unit";
	public String getUnit(){
		return getString(UNIT);
	}
	public void setUnit(String unit){
		put(UNIT, unit);
	}
	
	
	// 内含单件数量
	public final String CONTAINSCOUNT = "containsCount";
	public int getContainsCount(){
		return getInt(CONTAINSCOUNT);
	}
	public void setContainsCount(int count){
		put(CONTAINSCOUNT, count);
	}
	
	
	// 单件单位
	public final String UNITINBULK = "unitInBulk";
	public String getUnitInBulk(){
		return getString(UNITINBULK);
	}
	public void setUnitInBulk(String unitInBulk){
		put(UNITINBULK, unitInBulk);
	}
	
	
	// 包装图片
	public final String IMAGE = "image";
	public AVFile getImage(){
		return getAVFile(IMAGE);
	}
	public void setImage(AVFile image){
		put(IMAGE, image);
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
