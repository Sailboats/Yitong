package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.GiftResponseEntity;

/**
 * * @author caoligai
 */
public class TmlGiftDaoforFrame  implements BaseDao{

	private GiftResponseEntity giftRespose;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public GiftResponseEntity getGiftRespose() {
		return giftRespose;
	}

	public void setGiftRespose(GiftResponseEntity giftRespose) {
		this.giftRespose = giftRespose;
	}

	public TmlGiftDaoforFrame(Activity mActivity) {

	}

	// ��ȡ����
	public GiftResponseEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		// ��������
		giftRespose = new GiftResponseEntity();

		return giftRespose;

	}

	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}
}
