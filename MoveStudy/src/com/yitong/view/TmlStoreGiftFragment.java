package com.yitong.view;

import java.util.ArrayList;

import com.example.movestudy.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.MySSLSocketFactory;
import com.yitong.app.MyApplication;
import com.yitong.baseAdapter.HomeNewsAdapter;
import com.yitong.biz.GiftDao;
import com.yitong.entity.GiftEntity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 终端店积分商城页面 * @author caoligai
 */
public class TmlStoreGiftFragment extends Fragment {

	private String Tag = "TmlStoreGiftFragment";

	private Activity mActivity;		

	PullToRefreshListView listView; // 礼品列表

	private ArrayList<GiftEntity> datas;	// 数据源

	private int showCount = 20;		// 显示条目数
	
	HomeNewsAdapter adapter;
	
	public TmlStoreGiftFragment(Activity mActivity,
			FragmentStatePagerAdapter adapter) {
		this.mActivity = mActivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tmlstore_gift, null);

		listView = (PullToRefreshListView) view.findViewById(R.id.lv_gifts);
		listView.setMode(Mode.BOTH);
		// 设置点击监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView tv_id = (TextView) view.findViewById(R.id.tv_news_id);
				Toast.makeText(getActivity(),
						"礼品 id 为 " + tv_id.getText().toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
		
		// 设置上/下拉刷新监听
		listView.setOnRefreshListener(new com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2<ListView>(){

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "下拉刷新，显示前 20 条记录", Toast.LENGTH_SHORT).show();
				showCount = 20;
				new MyTask().execute(showCount,null,null);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				showCount += 20;
				Toast.makeText(getActivity(), "上拉刷新，加载更多", Toast.LENGTH_SHORT).show();
				new MyTask().execute(showCount,null,null);
			}});

		new MyTask().execute(20, null, null);

		return view;
	}

	class MyTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {

			datas = new GiftDao().getAllGifts(params[0]);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (listView.isRefreshing()) {
				listView.onRefreshComplete();
			}
			if (20 == showCount) {
				myHandler.sendEmptyMessage(0);
			}else {
				myHandler.sendEmptyMessage(1);	// 发送更新数据信号
			}
		}

	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.d(Tag, "received the message");
			if (0 == msg.what) {
				adapter = new HomeNewsAdapter(datas, getActivity().getLayoutInflater(), mActivity);
				listView.setAdapter(adapter);
			}
			if (1 == msg.what) {
				adapter.notifyDataSetChanged();
			}

		}

	};

}
