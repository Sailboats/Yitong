package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.BrandDistributorStatictisEntity;
import com.yitong.entity.CategorysEntity;

public class BrandDistributorStatictisDao implements BaseDao {
	private BrandDistributorStatictisEntity BrandDistributorStatictisRespose;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public BrandDistributorStatictisEntity getBrandDistributorStatictisRespose() {
		return BrandDistributorStatictisRespose;
	}

	public void setBrandDistributorStatictisRespose(
			BrandDistributorStatictisEntity brandDistributorStatictisRespose) {
		BrandDistributorStatictisRespose = brandDistributorStatictisRespose;
	}

	public BrandDistributorStatictisDao(Activity mActivity) {

	}

	// 获取数据
	public BrandDistributorStatictisEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		// 声明变量
		BrandDistributorStatictisRespose = new BrandDistributorStatictisEntity();

		return BrandDistributorStatictisRespose;

	}

	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}
}
