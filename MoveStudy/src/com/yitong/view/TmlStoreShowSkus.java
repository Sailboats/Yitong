package com.yitong.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.AVException;
import com.example.movestudy.R;
import com.yitong.avsubobject.Sku;
import com.yitong.baseAdapter.TmlStoreSkusListAdapter;
import com.yitong.biz.TmlStoreSkusDao;
import com.yitong.widget.CircleChart02View;
import com.yitong.widget.LoadingDialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 库存盘点/进货录入页面
 * 展示服务器的所有 sku 列表
 * 
 * * @author caoligai
 */
public class TmlStoreShowSkus extends Fragment {

	private String Tag = "TmlStoreShowSkus";

	// private SimpleAdapter adapter;

	private Activity mactivity;

	private ListView listview;

	List<byte[]> images;

	List<String> names;

	TmlStoreSkusListAdapter adapter;

	List<String> ids;
	
	CircleChart02View chart = null;
	
	ProgressDialog pd ;

	public TmlStoreShowSkus(Activity mactivity,
			FragmentStatePagerAdapter adapter) {
		this.mactivity = mactivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(Tag, "this.onCreateView()");
		View view = inflater.inflate(R.layout.tmlstore_sku_list, container,
				false);

		// 初始化界面(获取列表数据及设置监听)
		initView(view);

		return view;
	}

	private void initView(View view) {

		
		pd = new LoadingDialog().showDialog(mactivity, "拼了命的加载 SKU 信息。。。");
		
		listview = (ListView) view.findViewById(R.id.lv_sku);

		new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();
				images = new TmlStoreSkusDao().getAllSkuImage();

				names = new TmlStoreSkusDao().getAllSkuNames();

				ids = new TmlStoreSkusDao().getAllObjectId();

				myHandler.sendEmptyMessage(0);

			}
		}).start();

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tv = (TextView) view
						.findViewById(R.id.tmlstore_show_sku_item_objectid);
				Log.d(Tag, "点击了第"+ position +"sku");
				startSkuDetialsActivity(position,tv.getText().toString());
			}

		});
	}

	/**
	 * 跳转到 sku 详情页面
	 * 
	 * @param position
	 *            传递点击的 item 序号，sku 详情页面便可以根据序号来获取要显示的产品
	 */
	private void startSkuDetialsActivity(int position,String id) {

		startActivity(new Intent(mactivity, TmlStoreSkuDetialsActivity.class)
				.putExtra("index", position).putExtra("id", id));
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			Log.d(Tag, "receive the message");

			if (pd.isShowing()) {
				pd.dismiss();
			}
			
			adapter = new TmlStoreSkusListAdapter(getActivity(), getActivity()
					.getLayoutInflater(), images, names,ids);

			listview.setAdapter(adapter);

		}
	};

}
