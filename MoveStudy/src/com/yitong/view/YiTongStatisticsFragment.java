package com.yitong.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movestudy.R;
import com.yitong.baseAdapter.BrandBasePageAdapter;
import com.yitong.widget.ChildViewPager;

@SuppressLint("ValidFragment")
public class YiTongStatisticsFragment extends Fragment implements
		OnClickListener {

	Activity myActivity;

	public final int mItemCount = 3;
	BrandBasePageAdapter myAdapter;
	View mView;
	private TextView mYearText;
	private TextView mMonthText;
	private TextView mDayText;
	private TextView mTextBottemLine;
	private int moveWidth = 0;
	private int mCurrentDes = 0;

	private ChildViewPager mViewPager;

	private List<Fragment> mFragments;
	MyFragmentAdapter mAdapter;

	@SuppressLint("ValidFragment")
	public YiTongStatisticsFragment(Activity activity,
			BrandBasePageAdapter studyBasePageAdapter) {
		myActivity = activity;
		myAdapter = studyBasePageAdapter;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = (View) inflater
				.inflate(R.layout.yitong_statistics, null, false);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
		InitImageViewWidth();
	}

	private void initView() {
		mYearText = (TextView) mView.findViewById(R.id.yitong_statistics_year);
		mMonthText = (TextView) mView
				.findViewById(R.id.yitong_statistics_month);
		mDayText = (TextView) mView.findViewById(R.id.yitong_statistics_day);
		mTextBottemLine = (TextView) mView
				.findViewById(R.id.yitong_bottom_line);
		mViewPager = (ChildViewPager) mView
				.findViewById(R.id.yitong_statistics_viewPager);
		mFragments = new ArrayList<Fragment>();
		mAdapter = new MyFragmentAdapter(getChildFragmentManager());
	}

	private void InitImageViewWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		moveWidth = screenW / mItemCount;
		mTextBottemLine.setWidth(moveWidth);
	}

	private void initData() {
		mYearText.setOnClickListener(this);
		mMonthText.setOnClickListener(this);
		mDayText.setOnClickListener(this);
		YiTongStatictiscYearFragment yiTongStatictiscYearFragment = new YiTongStatictiscYearFragment();
		mFragments.add(yiTongStatictiscYearFragment);
		YiTongStatictiscMonthFragment yiTongStatictiscMonthFragment = new YiTongStatictiscMonthFragment();
		mFragments.add(yiTongStatictiscMonthFragment);
		YiTongStatictiscDayFragment yiStatictiscDayFragment = new YiTongStatictiscDayFragment();
		mFragments.add(yiStatictiscDayFragment);
		Log.i("tag", "mFragments.add(yiStatictiscDayFragment);");
		mViewPager.setCurrentItem(0);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private class MyFragmentAdapter extends FragmentPagerAdapter {

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int pos) {
			Log.i("tag", "getItem(int arg0)");
			return mFragments.get(pos);
		}

		@Override
		public int getCount() {
			Log.i("tag", "getCount()");
			return mFragments.size();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.yitong_statistics_year:
			mViewPager.setCurrentItem(0);
			break;

		case R.id.yitong_statistics_month:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.yitong_statistics_day:
			mViewPager.setCurrentItem(2);
			break;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				animation = new TranslateAnimation(mCurrentDes, 0, 0, 0);
				mCurrentDes = 0 * moveWidth;
				break;
			case 1:
				animation = new TranslateAnimation(mCurrentDes, moveWidth, 0, 0);
				mCurrentDes = 1 * moveWidth;
				break;
			case 2:
				animation = new TranslateAnimation(mCurrentDes, moveWidth * 2,
						0, 0);
				mCurrentDes = 2 * moveWidth;
				break;
			}
			animation.setFillAfter(true);
			animation.setDuration(300);
			mTextBottemLine.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

}
