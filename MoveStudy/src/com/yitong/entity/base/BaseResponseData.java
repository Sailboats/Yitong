package com.yitong.entity.base;
import java.util.List;

import com.yitong.entity.CategorysEntity;



/**
 * 返回的大的json的封装 基类
 * @author maojie
 *
 */
public abstract class BaseResponseData {
	 
	private long date;				//鍒涘缓鏃堕棿
	private List<CategorysEntity> categorys;	//鍒嗙被
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public List<CategorysEntity> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<CategorysEntity> categorys) {
		this.categorys = categorys;
	}
	

}
