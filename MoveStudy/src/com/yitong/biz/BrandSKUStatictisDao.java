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

	// ��ȡ����
	public BrandSKUStatictisEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		// ��������
		brandSKUStatictisEntity = new BrandSKUStatictisEntity();

		return brandSKUStatictisEntity;

	}

	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("ѡ��Ҫ�����ģ��");
		tabs.add(cate1);
		return tabs;
	}
}
