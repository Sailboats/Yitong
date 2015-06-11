package com.yitong.avsubobject;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

/**
 * 
 * 服务器图文表的子类
 * 
 * * @author caoligai
 */
@AVClassName("ImageText")
public class ImageText extends AVObject {

	
	// 图片
	public final String IMAGE = "image";
	public AVFile getImage(){
		return getAVFile(IMAGE);
	}
	public void setImage(AVFile image){
		put(IMAGE, image);
	}
	
	
	// 文字
	public final String TEXT = "text";
	public String getText(){
		return getString(TEXT);
	}
	public void setText(String text){
		put(TEXT, text);
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
