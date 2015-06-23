package com.yitong.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.movestudy.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yitong.baseAdapter.HomeNewsAdapter;
import com.yitong.baseAdapter.ImageAdapter;
import com.yitong.biz.TmlStoreArticleDao;
import com.yitong.entity.HomeAdsEntity;
import com.yitong.entity.HomeEntity;
import com.yitong.widget.CircleFlowIndicator;
import com.yitong.widget.ViewFlow;
import com.yitong.widget.ViewFlow.ViewSwitchListener;

/**
 * 
 * 三种用户的主页面
 * 
 * @author caoligai
 * 
 */
@SuppressLint("ValidFragment")
public class HomePageFragment extends Fragment {

	private String Tag = "HomePageFragment";

	Activity myActivity;

	ViewFlow viewflow; // 滚动图片
	CircleFlowIndicator viewflowIndicator; // 滚动图片下面的圆圈

	ListView listView; // 新闻列表
	// PullToRefreshListView listView;

	ArrayList<HomeEntity> datas; // 新闻列表数据源
	ArrayList<byte[]> images; // 新闻列表图片

	ArrayList<HomeAdsEntity> adsData; // 广告位数据
	TextView adsTextView;	// 广告图片下方的文字说明

	@SuppressLint("ValidFragment")
	public HomePageFragment(Activity activity,
			FragmentStatePagerAdapter studyBasePageAdapter) {
		myActivity = activity;
		// myAdapter = (TmlsBasePageAdapter) studyBasePageAdapter;

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// webview = (WebView) getView().findViewById(R.id.webview);
	}

	// 一个fragment里面至少要继承这些方法
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 当创建fragment时, 系统调用该方法.
		// 在实现代码中,应当初始化想要在fragment中保持的必要组件, 当fragment被暂停或者停止后可以恢复.

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		// 用户将要离开fragment时,系统调用这个方法作为第一个指示(然而它不总是意味着fragment将被销毁.) ---
		// 在当前用户会话结束之前,通常应当在这里提交任何应该持久化的变化(因为用户有可能不会返回).
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// fragment第一次绘制它的用户界面的时候, 系统会调用此方法. 为了绘制fragment的UI,此方法必须返回一个View,-----
		// 这个view是你的fragment布局的根view. 如果fragment不提供UI, 可以返回null.
		// 填充一个布局View到ViewGrope中
		Log.i("tag", "------------------------------->");

		View view = (View) inflater
				.inflate(R.layout.fragment_main, null, false);
		// 初始化 ViewFlow(滚动图片)
		viewflow = (ViewFlow) view.findViewById(R.id.viewflow);
//		viewflow.setAdapter(new ImageAdapter(getActivity(), getActivity()
//				.getLayoutInflater(), adsData));
		viewflowIndicator = (CircleFlowIndicator) view
				.findViewById(R.id.viewflowindicator);
		viewflow.setFlowIndicator(viewflowIndicator);
		adsTextView = (TextView) view.findViewById(R.id.tv_ads_summary);

		// listView = (PullToRefreshListView) view.findViewById(R.id.lv_news);
		listView = (ListView) view.findViewById(R.id.lv_news);

		getNewsData();

		return view;

	}

	/**
	 * 获取服务器数据
	 */
	private void getNewsData() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				datas = new TmlStoreArticleDao().getAllArticle();

				images = new TmlStoreArticleDao().getAllArticlelistImage();
				adsData = new TmlStoreArticleDao().getAllAds();

				myHandler.sendEmptyMessage(0);
			}
		}).start();

	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.d(Tag, "receive the message");

			listView.setAdapter(new HomeNewsAdapter(new TmlStoreArticleDao()
					.getAllArticle(), getActivity().getLayoutInflater(),
					images, myActivity));
			
			viewflow.setAdapter(new ImageAdapter(getActivity(), getActivity()
					.getLayoutInflater(), adsData));
			viewflow.setTimeSpan(1000 * 3);
			viewflow.startAutoFlowTimer();
			viewflow.setAlwaysDrawnWithCacheEnabled(true);
			// 显示第一条广告 summary
			adsTextView.setText(adsData.get(0).getSummary());
			viewflow.setOnViewSwitchListener(new ViewSwitchListener() {

				@Override
				public void onSwitched(View view, int position) {
					// TODO Auto-generated method stub
					// 当图片切换时，图片下方的文字也将改变
					Log.d(Tag, "广告页面切换" + position);
					
					adsTextView.setText(adsData.get(position % adsData.size()).getSummary());
				}
			});
		}
	};

}
