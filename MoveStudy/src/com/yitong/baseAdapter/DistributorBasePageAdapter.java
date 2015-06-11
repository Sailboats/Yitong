package com.yitong.baseAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.Dis_tmlmResponseEntity;
import com.yitong.entity.HomeResponseEntity;
import com.yitong.entity.base.BaseResponseData;
import com.yitong.ui.Distributor_MainActivity.MyDTask;
import com.yitong.ui.TmlStore_MainActivity.MyTask;
import com.yitong.view.Dis_TmlmFragment;
import com.yitong.view.HomePageFragment;

/*
 * ��viewpager�з���fragment
 */
public class DistributorBasePageAdapter extends FragmentStatePagerAdapter {
	
	//��viewpager�������fragment
	private MyDTask myTask;
	Handler myHandler ;
	public ArrayList<Fragment> mFragments = new ArrayList<Fragment>();;
	public List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();
	//public StudyDao myStudyDao;
	//public StudyResponseEntity myrespResponseEntity;

	private Activity mActivity;

	public DistributorBasePageAdapter(FragmentActivity activity,MyDTask myTask,Handler myHandler) {
		super(activity.getSupportFragmentManager());
		this.myTask = myTask;
		this.mActivity = activity;
		this.myHandler = myHandler;
	}

	/**
	 * ������listview�ּ��� tabs
	 * @param listObject
	 */
	public void addFragment(List<CategorysEntity> mList, BaseResponseData baseResponseData) {
	
		tabs = mList;
		if (baseResponseData instanceof HomeResponseEntity) {
			
		addTab(new HomePageFragment(mActivity,this));
		
		}
		else if (baseResponseData instanceof Dis_tmlmResponseEntity) {
			addTab(new Dis_TmlmFragment(mActivity, this));
		}
		
	}
	/**
	 * ֻ����listview������ tabs
	 * @param listObject
	 */
	public void addFragment( List<Object> listObject) {


	}

	public void addNullFragment() {
		CategorysEntity cate = new CategorysEntity();
		cate.setName("���Ӵ���");
		tabs.add(cate);
		//addTab(new HttpErrorFragment());
	}

	public void Clear() {
		mFragments.clear();
		tabs.clear();
	}

	public void addTab(Fragment fragment) {
		mFragments.add(fragment);
		notifyDataSetChanged();
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
