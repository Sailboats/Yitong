package com.yitong.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.example.movestudy.R;
import com.yitong.app.MyApplication;
import com.yitong.avsubobject.PackingSpecification;
import com.yitong.biz.StockDao;
import com.yitong.biz.TmlStorePacksDao;
import com.yitong.biz.TmlStoreSkusDao;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 库存盘点
 * 
 * * @author caoligai
 */
public class TmlStoreRepertoryCheck extends Activity {

	private String Tag = "TmlStoreRepertoryCheck";

	private TextView tv_date; // 日期

	private List<PackingSpecification> packs = null; // 包装类型
	private byte[] image; // 产品图片
	private String name; // 产品名字

	private ImageView iv_sku; // 图片控件
	private TextView tv_name; // 名字控件

	private List<byte[]> packsImages = null; // 包装类型图片

	EditText et1, et2, et3; // 三种包装类型的输入框

	private int date_year = 0, date_month, date_day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tmlstore_repertory_check);

		initView();
	}

	private void initView() {

		Log.d(Tag, "id === " + getIntent().getStringExtra("id"));
		et1 = (EditText) findViewById(
				R.id.tmlstore_repertory_check_sengle_layout).findViewById(
				R.id.et_single);
		et2 = (EditText) findViewById(R.id.tmlstore_repertory_check_six_layout)
				.findViewById(R.id.et_single);
		et3 = (EditText) findViewById(R.id.tmlstore_repertory_check_box_layout)
				.findViewById(R.id.et_single);
		iv_sku = (ImageView) findViewById(R.id.iv_name);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_date = (TextView) findViewById(R.id.tv_check_date);
		tv_date.setText(Calendar.getInstance().get(Calendar.YEAR)
				+ "-"
				+ new String().valueOf(Calendar.getInstance().get(
						Calendar.MONTH) + 1) + "-"
				+ Calendar.getInstance().get(Calendar.DATE));

		new Thread(new Runnable() {

			@Override
			public void run() {
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

		// 设置日期监听
		findViewById(R.id.check_date).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dialog = new DatePickerDialog(
						TmlStoreRepertoryCheck.this, new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// TODO Auto-generated method stub
								StringBuffer sb = new StringBuffer();
								sb.append(year).append("-")
										.append(monthOfYear + 1).append("-")
										.append(dayOfMonth);
								tv_date.setText(sb.toString());
								date_year = year - 1900;
								date_month = monthOfYear;
								date_day = dayOfMonth;
							}
						}, year, month, day);
				dialog.setTitle("请选择盘点日期");
				dialog.show();
			}
		});

		// 设置提交监听
		findViewById(R.id.tmlstore_repertory_cherck_btn_commit)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!hasData()) {
							return;
						}
						switch (packs.size()) {
						case 1:
							int count1 = 0;
							if (!et1.getText().equals("")) {

								count1 = Integer.parseInt(et1.getText()
										.toString())
										* packs.get(0).getContainsCount();// 数量等于数量
								// * 单位
								addAndUPLOAD(count1);
							}

							break;
						case 2:
							int count2 = 0;
							if (!et1.getText().toString().equals("")) {

								count2 = Integer.parseInt(et1.getText()
										.toString())
										* packs.get(0).getContainsCount();
							}
							if (!et2.getText().toString().equals("")) {
								count2 += Integer.parseInt(et2.getText()
										.toString())
										* packs.get(1).getContainsCount();
							}
							addAndUPLOAD(count2);
							break;
						case 3:
							int count3 = 0;
							if (!et1.getText().toString().equals("")) {
								count3 = Integer.parseInt(et1.getText()
										.toString())
										* packs.get(0).getContainsCount();

							}
							if (!et2.getText().toString().equals("")) {
								count3 += +Integer.parseInt(et2.getText()
										.toString())
										* packs.get(1).getContainsCount();

							}
							if (!et3.getText().toString().equals("")) {
								count3 += Integer.parseInt(et3.getText()
										.toString())
										* packs.get(2).getContainsCount();
							}

							addAndUPLOAD(count3);
						default:
							break;
						}
					}

					/**
					 * 检查用户是否输入了数量
					 */
					private boolean hasData() {
						if (et1.getText().toString().equals("")&&et2.getText().toString().equals("")&&et3.getText().toString().equals("")) {
							return false;
						}
						return true;
					}

				});

	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (0 == msg.what) {
				iv_sku.setImageBitmap(BitmapFactory.decodeByteArray(image, 0,
						image.length));
				tv_name.setText(name);

				// 设置显示包装类型
				setUpPacks(packs);
			}
			if (1 == msg.what) {
				Toast.makeText(TmlStoreRepertoryCheck.this,
						"操作成功,盘点总量为 " + msg.getData().getInt("totalCount"),
						Toast.LENGTH_SHORT).show();
			}

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
			TextView packingName = (TextView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.tv_single_name);
			packingName.setText(packs.get(0).getPackingName());
			TextView unit = (TextView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.unit);
			unit.setText(packs.get(0).getUnit());

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
			TextView packingName1 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.tv_single_name);
			packingName1.setText(packs.get(0).getPackingName());
			TextView unit1 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.unit);
			unit1.setText(packs.get(0).getUnit());

			ImageView iv2 = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.iv_single_pic);
			iv2.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(1), 0, packsImages.get(1).length));
			TextView packingName2 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.tv_single_name);
			packingName2.setText(packs.get(1).getPackingName());
			TextView unit2 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.unit);
			unit2.setText(packs.get(1).getUnit());
			break;

		case 3:
			ImageView iv3 = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.iv_single_pic);
			iv3.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(0), 0, packsImages.get(0).length));
			TextView packingName3 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.tv_single_name);
			packingName3.setText(packs.get(0).getPackingName());
			TextView unit3 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_sengle_layout).findViewById(
					R.id.unit);
			unit3.setText(packs.get(0).getUnit());

			ImageView iv4 = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.iv_single_pic);
			iv4.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(1), 0, packsImages.get(1).length));
			TextView packingName4 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.tv_single_name);
			packingName4.setText(packs.get(1).getPackingName());
			TextView unit4 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_six_layout).findViewById(
					R.id.unit);
			unit4.setText(packs.get(1).getUnit());

			ImageView iv5 = (ImageView) findViewById(
					R.id.tmlstore_repertory_check_box_layout).findViewById(
					R.id.iv_single_pic);
			iv5.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(2), 0, packsImages.get(2).length));
			TextView packingName5 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_box_layout).findViewById(
					R.id.tv_single_name);
			packingName5.setText(packs.get(2).getPackingName());
			TextView unit5 = (TextView) findViewById(
					R.id.tmlstore_repertory_check_box_layout).findViewById(
					R.id.unit);
			unit5.setText(packs.get(2).getUnit());

			break;

		default:
			break;
		}

	}

	private void addAndUPLOAD(final int count) {
		Log.d(Tag, "盘点总量为: " + count);
		new Thread(new Runnable() {

			@Override
			public void run() {
				Date date;
				if (date_year != 0) {
					date = new Date();
					date.setYear(date_year);
					date.setMonth(date_month);
					date.setDate(date_day);
				} else {
					date = new Date();
				}

				MyApplication app = (MyApplication) getApplication();
				new StockDao().addStockAndUpload(
						getIntent().getStringExtra("id"), count, date,
						app.getCurrentUser(), 1);

				Message message = new Message();
				message.what = 1;
				Bundle bundle = new Bundle();
				bundle.putInt("totalCount", count);
				message.setData(bundle);
				myHandler.sendMessage(message);
			}
		}).start();
	}
}
