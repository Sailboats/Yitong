package com.yitong.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movestudy.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
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
	TextView adsTextView; // 广告图片下方的文字说明
	PullToRefreshScrollView scrollview;

	HomeNewsAdapter newsAdapter;
	ImageAdapter viewFlowAdapter;

	ProgressDialog pd;
	protected boolean progressShow = false;

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

		scrollview = (PullToRefreshScrollView) view
				.findViewById(R.id.scrollview_refresh);
		scrollview.setMode(Mode.PULL_FROM_START);
		// scrollview.getLoadingLayoutProxy().setLastUpdatedLabel("lastUpdateLabel");
		scrollview.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				// 执行刷新函数
				Log.d(Tag, "开始刷新");
				getData(1);
			}
		});

		// 根据屏幕大小设置 viewflow 容器 (RelativeLayout) 的长宽按比例显示
		RelativeLayout viewflowContainor = (RelativeLayout) view.findViewById(R.id.rl_viewflow_containor);
		double width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		double height = width * 0.5625;
		Log.d(Tag, "width = " + width + " height =  " + height);
		LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) viewflowContainor.getLayoutParams();
		params.height = (int)height;
		params.width = (int)width;
		viewflowContainor.setLayoutParams(params);
		
		// 初始化 ViewFlow(滚动图片)
		viewflow = (ViewFlow) view.findViewById(R.id.viewflow);
		
		viewflowIndicator = (CircleFlowIndicator) view
				.findViewById(R.id.viewflowindicator);
		viewflow.setFlowIndicator(viewflowIndicator);
		adsTextView = (TextView) view.findViewById(R.id.tv_ads_summary);

		// listView = (PullToRefreshListView) view.findViewById(R.id.lv_news);
		listView = (ListView) view.findViewById(R.id.lv_news);

		getData(0); // 获取数据
		
		// 显示进度条
		pd = new ProgressDialog(myActivity);
		pd.setCanceledOnTouchOutside(false);
		pd.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				progressShow = false;
			}
		});
		pd.setMessage("正在获取数据...");
		pd.show();
		progressShow = true;

		return view;

	}

	/**
	 * @param mode
	 *            0 第一次获取数据 1 刷新数据
	 */
	private void getData(final int mode) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				Log.d(Tag, "new Thread.start()");
				datas = new TmlStoreArticleDao().getAllArticle();

				images = new TmlStoreArticleDao().getAllArticlelistImage();
				adsData = new TmlStoreArticleDao().getAllAds();

				if (0 == mode) {
					myHandler.sendEmptyMessage(0);
				} else if (1 == mode) {
					myHandler.sendEmptyMessage(1);
				}
			}
		}).start();

	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.d(Tag, "receive the message");

			if (progressShow) {
				pd.dismiss();
			}
			
			if (0 == msg.what) {
				Log.d(Tag, "message.what = " + msg.what);
				newsAdapter = new HomeNewsAdapter(datas, getActivity()
						.getLayoutInflater(), images, myActivity);
				listView.setAdapter(newsAdapter);
				setListViewHeight(listView); // 因为 ScrollView 和 ListView
												// 有冲突，所以要调用此方法解决
				viewFlowAdapter = new ImageAdapter(getActivity(), getActivity()
						.getLayoutInflater(), adsData);
				viewflow.setAdapter(viewFlowAdapter);
				viewflow.setCount(adsData.size()); // 设置 viewflow 的真实个数
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

						adsTextView.setText(adsData.get(
								position % adsData.size()).getSummary());
					}
				});
			}

			if (1 == msg.what) {
				Log.d(Tag, "message.what = " + msg.what);
				Toast.makeText(myActivity, "刷新成功", Toast.LENGTH_SHORT).show();
				scrollview.onRefreshComplete();
				newsAdapter = new HomeNewsAdapter(datas, getActivity()
						.getLayoutInflater(), images, myActivity);
				listView.setAdapter(newsAdapter);
				setListViewHeight(listView); // 因为 ScrollView 和 ListView
												// 有冲突，所以要调用此方法解决
				viewFlowAdapter = new ImageAdapter(getActivity(), getActivity()
						.getLayoutInflater(), adsData);
				viewflow.setAdapter(viewFlowAdapter);
				adsTextView.setText(adsData.get(0).getSummary());
			}

		}
	};

	/**
	 * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
	 * 
	 * @param listView
	 */
	public void setListViewHeight(ListView listView) {

		// 获取ListView对应的Adapter

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

}
