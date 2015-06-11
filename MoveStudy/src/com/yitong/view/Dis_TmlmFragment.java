package com.yitong.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.movestudy.R;
import com.yitong.widget.XListView;
import com.yitong.widget.XListView.IXListViewListener;

@SuppressLint("ValidFragment")
public class Dis_TmlmFragment extends Fragment implements IXListViewListener{

	
	
	Spinner areasSpinner;
	Spinner complySpinner;
	Spinner defaultsSpinner;
	Activity myActivity;
	View view;
	//TmlsBasePageAdapter myAdapter;
	
	private XListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	  
	
	public Dis_TmlmFragment(Activity activity,FragmentStatePagerAdapter studyBasePageAdapter){
		myActivity = activity;
		//myAdapter = (TmlsBasePageAdapter) studyBasePageAdapter;
	
		
	
	} 
	
	
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//webview = (WebView) getView().findViewById(R.id.webview);
	}
	//一个fragment里面至少要继承这些方法
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			//当创建fragment时, 系统调用该方法. 
			//在实现代码中,应当初始化想要在fragment中保持的必要组件, 当fragment被暂停或者停止后可以恢复.
			
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
			//用户将要离开fragment时,系统调用这个方法作为第一个指示(然而它不总是意味着fragment将被销毁.) ---
			//在当前用户会话结束之前,通常应当在这里提交任何应该持久化的变化(因为用户有可能不会返回).
			super.onPause();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
					
			//fragment第一次绘制它的用户界面的时候, 系统会调用此方法. 为了绘制fragment的UI,此方法必须返回一个View,-----
			// 这个view是你的fragment布局的根view. 如果fragment不提供UI, 可以返回null.
			// 填充一个布局View到ViewGrope中
			
			view = (View)inflater.inflate(R.layout.fragment_dis_tmlm, null, false);
			areasSpinner = (Spinner)view.findViewById(R.id.spinner_area);
			areasSpinner.setBackgroundResource(R.drawable.spinner1);
			complySpinner = (Spinner)view.findViewById(R.id.spinner_comply);
			complySpinner.setBackgroundResource(R.drawable.spinner1);
			defaultsSpinner = (Spinner)view.findViewById(R.id.spinner_default);
			defaultsSpinner.setBackgroundResource(R.drawable.spinner1);
			
			geneItems();
			mListView = (XListView)view.findViewById(R.id.Tmlm_xListView);
			mListView.setPullLoadEnable(true);
			mAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_item, items);
			mListView.setAdapter(mAdapter);
//			mListView.setPullLoadEnable(false);
//			mListView.setPullRefreshEnable(false);
			mListView.setXListViewListener(this);
			mHandler = new Handler();

			 
			 return view;
		}

		private void geneItems() {
			for (int i = 0; i != 20; ++i) {
				items.add("refresh cnt " + (++start));
			}
		}
		
		private void onLoad() {
			mListView.stopRefresh();
			mListView.stopLoadMore();
			mListView.setRefreshTime("刚刚");
		}
		

		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					start = ++refreshCnt;
					items.clear();
					geneItems();
					// mAdapter.notifyDataSetChanged();
					mAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_item, items);
					mListView.setAdapter(mAdapter);
					onLoad();
				}
			}, 2000);
		}



		@Override
		public void onLoadMore() {
			// TODO Auto-generated method stub
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					geneItems();
					mAdapter.notifyDataSetChanged();
					onLoad();
				}
			}, 2000);
		}
		
}
