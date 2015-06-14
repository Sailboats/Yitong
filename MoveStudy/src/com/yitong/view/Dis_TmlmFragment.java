package com.yitong.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.movestudy.R;
import com.yitong.entity.Dis_Tmls_ItemEntity;
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
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	XlistAdapter myAdapter;
	List<Dis_Tmls_ItemEntity> list;
	  
	
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
			
			list = new ArrayList<Dis_Tmls_ItemEntity>();
			geneItems();
			mListView = (XListView)view.findViewById(R.id.Tmlm_xListView);
			mListView.setPullLoadEnable(true);
			
			myAdapter = new XlistAdapter(list,myActivity);
			
			mListView.setAdapter(myAdapter);
//			mListView.setPullLoadEnable(false);
//			mListView.setPullRefreshEnable(false);
			mListView.setXListViewListener(this);
			mHandler = new Handler();

			 
			 return view;
		}

		private void geneItems() {
			
			Dis_Tmls_ItemEntity entity1 = new Dis_Tmls_ItemEntity();
			entity1.setPctureurl(null);
			entity1.setName("鲜又多超市");
			entity1.setGrade(3);
			entity1.setNeed("三日内需要补货");
			list.add(entity1);
			Dis_Tmls_ItemEntity entity2 = new Dis_Tmls_ItemEntity();
			entity2.setPctureurl(null);
			entity2.setName("音乐盒子");
			entity2.setGrade(4);
			entity2.setNeed("三日内需要补货");
			list.add(entity2);
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
					
					Dis_Tmls_ItemEntity entity11 = new Dis_Tmls_ItemEntity();
					entity11.setPctureurl(null);
					entity11.setName("鲜又多超市");
					entity11.setGrade(3);
					entity11.setNeed("三日内需要补货");
					list.add(entity11);
					Dis_Tmls_ItemEntity entity22 = new Dis_Tmls_ItemEntity();
					entity22.setPctureurl(null);
					entity22.setName("音乐盒子");
					entity22.setGrade(4);
					entity22.setNeed("三日内需要补货");
					list.add(entity22);
					XlistAdapter myAdapter = new XlistAdapter(list,myActivity);
					mListView.setAdapter(myAdapter);
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
					myAdapter.notifyDataSetChanged();
					onLoad();
				}
			}, 2000);
		}
		
}

class XlistAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
	List<Dis_Tmls_ItemEntity> list;
	
	public XlistAdapter(List<Dis_Tmls_ItemEntity> list,Context context) {
		// TODO Auto-generated method stub
        this.list = list;
        mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		view = (View)mInflater.inflate(R.layout.dis_tmls_list_item, null);
		ImageView tml_picture = (ImageView) view.findViewById(R.id.dis_tmlm_picture);
		TextView tml_name = (TextView)view.findViewById(R.id.dis_tmlm_name);
		TextView tml_grade = (TextView)view.findViewById(R.id.dis_tmlm_grade);
		TextView tml_need = (TextView)view.findViewById(R.id.dis_tmlm_need);
		RatingBar tml_ratingbarBar = (RatingBar)view.findViewById(R.id.dis_tmlm_ratingBar);
		
		tml_picture.setImageResource(R.drawable.dis_tmlm_picture);
		tml_name.setText(list.get(arg0).getName());
		String text = "店铺评级："+list.get(arg0).getGrade()+"星";
		tml_grade.setText(text);
		tml_ratingbarBar.setRating(list.get(arg0).getGrade());
		tml_need.setText(list.get(arg0).getNeed());
		
		return view;
	}
	

}
