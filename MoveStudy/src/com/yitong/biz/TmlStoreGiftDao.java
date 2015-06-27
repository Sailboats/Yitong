package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.HomeResponseEntity;
import com.yitong.entity.base.BaseResponseData;

/**
 * * @author caoligai
 */
public class TmlStoreGiftDao implements BaseDao {

	public TmlStoreGiftDao(Activity mActivity) {

	}

	private HomeResponseEntity homeRespose;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public HomeResponseEntity getStudyRespose() {
		return homeRespose;
	}

	public void setStudyRespose(HomeResponseEntity homeRespose) {
		this.homeRespose = homeRespose;
	}

	@Override
	public BaseResponseData mapperJson(boolean useCache) {
		// TODO Auto-generated method stub
		homeRespose = new HomeResponseEntity();
		
		return homeRespose;
	}

	@Override
	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}

}