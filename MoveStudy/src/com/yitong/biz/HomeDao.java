package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.HomeResponseEntity;

/**
 * ������ʾ��ҳ�ĵ�
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
	//��ȡ����
	public HomeResponseEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		
		//��������
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