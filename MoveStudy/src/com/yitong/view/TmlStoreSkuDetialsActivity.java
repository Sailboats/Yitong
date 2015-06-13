package com.yitong.view;

import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVObjectReferenceCount;
import com.avos.avoscloud.AVQuery;
import com.example.movestudy.R;
import com.yitong.avsubobject.Sku;
import com.yitong.biz.TmlStoreSkusDao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * sku 详情页面
 * 
 * * @author caoligai
 */
public class TmlStoreSkuDetialsActivity extends Activity implements
		View.OnClickListener {

	private String Tag = "TmlStoreSkuDetialsActivity";

	ImageView image; // 显示图片

	TextView name; // 显示名称

	List<byte[]> images; // 缓存中的产品图片

	List<String> names; // 缓存中的产品名称

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tmlstore_skudetials);

		initView(); // 初始化界面

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (-1 == msg.what) {
				Toast.makeText(TmlStoreSkuDetialsActivity.this, "未知错误",
						Toast.LENGTH_SHORT).show();
				finish();
			}

			image.setImageBitmap(BitmapFactory.decodeByteArray(
					images.get(msg.what), 0, images.get(msg.what).length));
			name.setText(names.get(msg.what).toString());

		}
	};

	/**
	 * 初始化界面
	 */
	private void initView() {

		image = (ImageView) findViewById(R.id.tmlstore_listview_item_enter_iv);

		name = (TextView) findViewById(R.id.tmlstore_listview_item_enter_tv);

		findViewById(R.id.tmlstore_listview_item_enter_btn_find)
				.setOnClickListener(this);
		findViewById(R.id.tmlstore_listview_item_enter_btn_add)
				.setOnClickListener(this);

		new Thread(new Runnable() {

			@Override
			public void run() {
				images = new TmlStoreSkusDao().getAllSkuImage();
				Log.d(Tag, "ths length of the list images is " + images.size());

				names = new TmlStoreSkusDao().getAllSkuNames();
				Log.d(Tag, "ths length of the list names is " + names.size());

				myHandler
						.sendEmptyMessage(getIntent().getIntExtra("index", -1));

			}
		}).start();

	}

	/*
	 * 按下 back 键 finish
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();

		finish();

	}

	@Override
	public void onClick(View v) {

		int viewId = v.getId();

		switch (viewId) {
		case R.id.Linear_above_toHome: // 点击返回箭头 finish
			finish();
			break;

		case R.id.tmlstore_listview_item_enter_btn_find: // 库存盘点
			startActivity(new Intent(TmlStoreSkuDetialsActivity.this,
					TmlStoreRepertoryCheck.class).putExtra("id", getIntent()
					.getStringExtra("id")));
			break;

		case R.id.tmlstore_listview_item_enter_btn_add: // 进货录入
			startActivity(new Intent(TmlStoreSkuDetialsActivity.this,
					TmlStoreGoodsInputActivity.class).putExtra("id",
					getIntent().getStringExtra("id")));
			break;

		default:
			break;
		}

	}

}