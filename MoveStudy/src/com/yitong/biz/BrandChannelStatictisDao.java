package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.BrandChannelStatictisEntity;
import com.yitong.entity.BrandDistributorStatictisEntity;
import com.yitong.entity.BrandSKUStatictisEntity;
import com.yitong.entity.CategorysEntity;

public class BrandChannelStatictisDao implements BaseDao {
	private BrandChannelStatictisEntity brandChannelStatictisEntity;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public BrandChannelStatictisEntity getBrandChannelStatictisEntity() {
		return brandChannelStatictisEntity;
	}

	public void setBrandChannelStatictisEntity(
			BrandChannelStatictisEntity brandChannelStatictisEntity) {
		this.brandChannelStatictisEntity = brandChannelStatictisEntity;
	}

	public BrandChannelStatictisDao(Activity mActivity) {

	}

	// 获取数据
	public BrandChannelStatictisEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		// 声明变量
		brandChannelStatictisEntity = new BrandChannelStatictisEntity();

		return brandChannelStatictisEntity;

	}

	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}
}
