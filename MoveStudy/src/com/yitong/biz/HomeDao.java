package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.HomeResponseEntity;

/**
 * 用来显示主页的的
 * @return
 */
public class HomeDao implements BaseDao{
	
	private HomeResponseEntity homeRespose;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();
	
	public HomeResponseEntity getStudyRespose() {
		return homeRespose;
	}
	
	public void setStudyRespose(HomeResponseEntity homeRespose) {
		this.homeRespose = homeRespose;
	}

	public HomeDao(Activity mActivity){
		
	}
	//获取数据
	public HomeResponseEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		
		//声明变量
		homeRespose = new HomeResponseEntity();
		
	return homeRespose;

	}
	
	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}
}