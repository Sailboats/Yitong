package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.BrandDistributorStatictisEntity;
import com.yitong.entity.BrandSKUStatictisEntity;
import com.yitong.entity.CategorysEntity;

public class BrandSKUStatictisDao implements BaseDao {
	private BrandSKUStatictisEntity brandSKUStatictisEntity;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public BrandSKUStatictisEntity getBrandSKUStatictisEntity() {
		return brandSKUStatictisEntity;
	}

	public void setBrandSKUStatictisEntity(
			BrandSKUStatictisEntity brandSKUStatictisEntity) {
		this.brandSKUStatictisEntity = brandSKUStatictisEntity;
	}

	public BrandSKUStatictisDao(Activity mActivity) {

	}

	// 获取数据
	public BrandSKUStatictisEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		// 声明变量
		brandSKUStatictisEntity = new BrandSKUStatictisEntity();

		return brandSKUStatictisEntity;

	}

	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}
}
