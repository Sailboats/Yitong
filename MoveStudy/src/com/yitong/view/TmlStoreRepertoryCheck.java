package com.yitong.view;

import java.util.List;

import com.avos.avoscloud.AVException;
import com.example.movestudy.R;
import com.yitong.avsubobject.PackingSpecification;
import com.yitong.biz.TmlStorePacksDao;
import com.yitong.biz.TmlStoreSkusDao;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 库存盘点
 * 
 * * @author caoligai
 */
public class TmlStoreRepertoryCheck extends Activity {

	private String Tag = "TmlStoreRepertoryCheck";

	private List<PackingSpecification> packs = null;

	private byte[] image;

	private String name;

	private ImageView iv_sku;

	private TextView tv_name;

	private List<byte[]> packsImages = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tmlstore_repertory_check);

		initView();

	}

	private void initView() {

		Log.d(Tag, "id === " + getIntent().getStringExtra("id"));
		iv_sku = (ImageView) findViewById(R.id.iv_name);
		tv_name = (TextView) findViewById(R.id.tv_name);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				packs = new TmlStoreSkusDao()
						.getAllpackingSpecificationById(getIntent()
								.getStringExtra("id"));
				Log.d(Tag, "共有 " + packs.size() + " 种包装类型");

				packsImages = new TmlStorePacksDao().getPackImageByPacks(packs);

				image = new TmlStoreSkusDao().getImageById(getIntent()
						.getStringExtra("id"));

				name = new TmlStoreSkusDao().getNameById(getIntent()
						.getStringExtra("id"));

				myHandler.sendEmptyMessage(0);

			}
		}).start();

	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			iv_sku.setImageBitmap(BitmapFactory.decodeByteArray(image, 0,
					image.length));
			tv_name.setText(name);
			setUpPacks(packs);

		}

	};

	/**
	 * 显示包装类型 包装类型的数量不确定，xml 预留了 3 个包装类型的
	 * view，因此要动态地根据服务器数据的包装类型数量设置显示方式，例如只有一种包装类型，则只显示第一个预留的 view,后面两个都设置
	 * Visable 属性为 View.GONE
	 */
	private void setUpPacks(List<PackingSpecification> packs) {
		switch (packs.size()) {
		case 0:
			findViewById(R.id.tmlstore_repertory_check_sengle_layout)
					.setVisibility(View.GONE);
			findViewById(R.id.tmlstore_repertory_check_six_layout)
					.setVisibility(View.GONE);
			findViewById(R.id.tmlstore_repertory_check_box_layout)
					.setVisibility(View.GONE);
			break;
		case 1:
			findViewById(R.id.tmlstore_repertory_check_six_layout)
					.setVisibility(View.GONE);
			findViewById(R.id.tmlstore_repertory_check_box_layout)
					.setVisibility(View.GONE);
			ImageView iv = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.iv_single_pic);
			iv.setImageBitmap(BitmapFactory.decodeByteArray(packsImages.get(0),
					0, packsImages.get(0).length));

			break;
		case 2:
			// findViewById(R.id.tmlstore_repertory_check_six_layout).setVisibility(View.GONE);
			findViewById(R.id.tmlstore_repertory_check_box_layout)
					.setVisibility(View.GONE);
			ImageView iv1 = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.iv_single_pic);
			iv1.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(0), 0, packsImages.get(0).length));
			ImageView iv2 = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.iv_single_pic);
			iv2.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(1), 0, packsImages.get(1).length));
			break;

		default:
			break;
		}

	}
}
