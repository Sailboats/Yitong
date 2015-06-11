package com.yitong.baseAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.yitong.entity.BrandChannelStatictisEntity;
import com.yitong.entity.BrandDistributorStatictisEntity;
import com.yitong.entity.BrandSKUStatictisEntity;
import com.yitong.entity.CategorysEntity;
import com.yitong.entity.HomeResponseEntity;
import com.yitong.entity.base.BaseResponseData;
import com.yitong.ui.Brand_MainActivity.MyBTask;
import com.yitong.view.YiTongStatisticsFragment;
import com.yitong.view.HomePageFragment;

/*
 * 向viewpager中放入fragment
 */
public class BrandBasePageAdapter extends FragmentStatePagerAdapter {

	// 向viewpager里面加入fragment
	private MyBTask myTask;
	Handler myHandler;
	public ArrayList<Fragment> mFragments = new ArrayList<Fragment>();;
	public List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();
	// public StudyDao myStudyDao;
	// public StudyResponseEntity myrespResponseEntity;

	private Activity mActivity;

	public BrandBasePageAdapter(FragmentActivity activity, MyBTask myTask,
			Handler myHandler) {
		super(activity.getSupportFragmentManager());
		this.myTask = myTask;
		this.mActivity = activity;
		this.myHandler = myHandler;
	}

	/**
	 * 即加载listview又加载 tabs
	 * 
	 * @param listObject
	 */
	public void addFragment(List<CategorysEntity> mList,
			BaseResponseData baseResponseData) {

		if (baseResponseData instanceof HomeResponseEntity) {
			tabs = mList;
			addTab(new HomePageFragment(mActivity, this));
			addTab(new HomePageFragment(mActivity, this));
		} else if (baseResponseData instanceof BrandDistributorStatictisEntity) {
			addTab(new YiTongStatisticsFragment(mActivity, this));
		} else if (baseResponseData instanceof BrandSKUStatictisEntity) {
			addTab(new YiTongStatisticsFragment(mActivity, this));
		} else if (baseResponseData instanceof BrandChannelStatictisEntity) {
			addTab(new YiTongStatisticsFragment(mActivity, this));
		}

	}

	/**
	 * 只加载listview不包含 tabs
	 * 
	 * @param listObject
	 */
	public void addFragment(List<Object> listObject) {

	}

	public void addNullFragment() {
		CategorysEntity cate = new CategorysEntity();
		cate.setName("连接错误");
		tabs.add(cate);
		// addTab(new HttpErrorFragment());
	}

	public void Clear() {
		mFragments.clear();
		tabs.clear();
	}

	public void addTab(Fragment fragment) {
		mFragments.add(fragment);
//		notifyDataSetChanged();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabs.get(position).getName();
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	//

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
}
