package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.Dis_tmlmResponseEntity;
import com.yitong.entity.HomeResponseEntity;

public class Dis_TmlmDao implements BaseDao{
	
	private Dis_tmlmResponseEntity dis_tmlmRespose;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();
	
	public Dis_tmlmResponseEntity getStudyRespose() {
		return dis_tmlmRespose;
	}
	
	public void setStudyRespose(Dis_tmlmResponseEntity dis_tmlmResponse) {
		this.dis_tmlmRespose = dis_tmlmResponse;
	}

//	public Dis_TmlmDao(Activity mActivity){
//		super(mActivity);
//		
//	}
	//��ȡ����
	public Dis_tmlmResponseEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		
		//��������
		dis_tmlmRespose = new Dis_tmlmResponseEntity();
		
	return dis_tmlmRespose;

	}
	
	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("ѡ��Ҫ�����ģ��");
		tabs.add(cate1);
		return tabs;
	}
}