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
	//һ��fragment��������Ҫ�̳���Щ����
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			//������fragmentʱ, ϵͳ���ø÷���. 
			//��ʵ�ִ�����,Ӧ����ʼ����Ҫ��fragment�б��ֵı�Ҫ���, ��fragment����ͣ����ֹͣ����Իָ�.
			
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
			//�û���Ҫ�뿪fragmentʱ,ϵͳ�������������Ϊ��һ��ָʾ(Ȼ������������ζ��fragment��������.) ---
			//�ڵ�ǰ�û��Ự����֮ǰ,ͨ��Ӧ���������ύ�κ�Ӧ�ó־û��ı仯(��Ϊ�û��п��ܲ��᷵��).
			super.onPause();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
					
			//fragment��һ�λ��������û������ʱ��, ϵͳ����ô˷���. Ϊ�˻���fragment��UI,�˷������뷵��һ��View,-----
			// ���view�����fragment���ֵĸ�view. ���fragment���ṩUI, ���Է���null.
			// ���һ������View��ViewGrope��
			
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
			mListView.setRefreshTime("�ո�");
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
