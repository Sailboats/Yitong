package com.yitong.view;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.movestudy.R;
import com.example.movestudy.R.id;
import com.yitong.app.MyApplication;
import com.yitong.avsubobject.PackingSpecification;
import com.yitong.biz.PurchasesDao;
import com.yitong.biz.TmlStorePacksDao;
import com.yitong.biz.TmlStoreSkusDao;
import com.yitong.widget.LoadingDialog;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 进货录入 Activity
 * 
 * * @author caoligai
 */
public class TmlStoreGoodsInputActivity extends Activity {

	private String Tag = "TmlStoreGoodsInputActivity";

	private ImageView iv_sku; // sku 图片控件

	private TextView tv_name; // sku 名称控件

	private List<PackingSpecification> packs = null; // 包装类型

	private List<byte[]> packsImages = null; // 包装类型图片

	private byte[] image; // sku 图片

	private String name; // sku 名称

	private TextView tv_date; // 日期控件

	private EditText et1, et2, et3, etPro1, etPro2, etPro3; // et 为进货输入框,etPro
															// 为进货前库存输入框

	private int date_year = 0, date_month, date_day; // 年月日
	
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tmlstore_goods_input);

		initView();

	}

	private void initView() {
		Log.d(Tag, "id === " + getIntent().getStringExtra("id"));
		pd = new LoadingDialog().showDialog(this, "拼了命的加载进货信息。。。");

		et1 = (EditText) findViewById(R.id.tml_goods_input_single)
				.findViewById(R.id.ev_single);
		et2 = (EditText) findViewById(R.id.tml_goods_input_six).findViewById(
				R.id.ev_single);
		et3 = (EditText) findViewById(R.id.tml_goods_input_box).findViewById(
				R.id.ev_single);

		etPro1 = (EditText) findViewById(R.id.tml_goods_input_single)
				.findViewById(R.id.ev_single1);
		etPro2 = (EditText) findViewById(R.id.tml_goods_input_six)
				.findViewById(R.id.ev_single1);
		etPro3 = (EditText) findViewById(R.id.tml_goods_input_box)
				.findViewById(R.id.ev_single1);

		iv_sku = (ImageView) findViewById(R.id.iv_name);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_date = (TextView) findViewById(R.id.tv_check_date);
		tv_date.setText(Calendar.getInstance().get(Calendar.YEAR)
				+ "-"
				+ new String().valueOf(Calendar.getInstance().get(
						Calendar.MONTH) + 1) + "-"
				+ Calendar.getInstance().get(Calendar.DATE));
		
		// 设置点击状态栏返回图标返回上级页面
				findViewById(R.id.above_title).findViewById(R.id.Linear_above_toHome).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});

		// 获取数据
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
						TmlStoreGoodsInputActivity.this,
						new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
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
				dialog.setTitle("请选择进货日期");
				dialog.show();
			}
		});

		// 设置提交监听
		findViewById(R.id.btn_tml_ckeck_commit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!hasData()) {
							Log.d(Tag, "输入不合法");
							return;
						}
						switch (packs.size()) {
						case 1:
							int count1 = 0;
							int proCount1 = 0;

							count1 = Integer.parseInt(et1.getText().toString())
									* packs.get(0).getContainsCount();// 数量等于数量
							// * 单位
							proCount1 = Integer.parseInt(etPro1.getText()
									.toString())
									+ packs.get(0).getContainsCount();
							addAndUPLOAD(count1, proCount1);

							break;
						case 2:
							int count2 = 0;
							int proCount2 = 0;
							if (!et1.getText().toString().equals("")) {
								count2 = Integer.parseInt(et1.getText()
										.toString())
										* packs.get(0).getContainsCount();
								proCount2 = Integer.parseInt(etPro1.getText()
										.toString())
										* packs.get(0).getContainsCount();
							}
							if (!et2.getText().toString().equals("")) {
								count2 += Integer.parseInt(et2.getText()
										.toString())
										* packs.get(1).getContainsCount();
								proCount2 += Integer.parseInt(etPro2.getText()
										.toString())
										* packs.get(1).getContainsCount();
							}

							addAndUPLOAD(count2, proCount2);
							break;
						case 3:
							int count3 = 0;
							int proCount3 = 0;
							if (!et1.getText().toString().equals("")) {
								count3 = Integer.parseInt(et1.getText()
										.toString())
										* packs.get(0).getContainsCount();
								proCount3 = Integer.parseInt(etPro1.getText()
										.toString())
										* packs.get(0).getContainsCount();
							}
							if (!et2.getText().toString().equals("")) {
								count3 += +Integer.parseInt(et2.getText()
										.toString())
										* packs.get(1).getContainsCount();
								proCount3 += Integer.parseInt(etPro3.getText()
										.toString())
										* packs.get(1).getContainsCount();
							}
							if (!et3.getText().toString().equals("")) {
								count3 += Integer.parseInt(et3.getText()
										.toString())
										* packs.get(2).getContainsCount();
								proCount3 += Integer.parseInt(etPro3.getText()
										.toString())
										* packs.get(2).getContainsCount();
							}

							addAndUPLOAD(count3, proCount3);
						default:
							break;
						}
					}
				});

	}

	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if (pd.isShowing()) {
				pd.dismiss();
			}

			if (0 == msg.what) {

				iv_sku.setImageBitmap(BitmapFactory.decodeByteArray(image, 0,
						image.length));
				tv_name.setText(name);

				// 设置显示包装类型
				setUpPacks(packs);
			}
			if (1 == msg.what) {
				Toast.makeText(TmlStoreGoodsInputActivity.this, "操作成功",
						Toast.LENGTH_SHORT).show();
			}
			if (3 == msg.what) {
				Toast.makeText(TmlStoreGoodsInputActivity.this, "请填写对应的进货前库存",
						Toast.LENGTH_LONG).show();
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
			findViewById(R.id.tml_goods_input_single).setVisibility(View.GONE);
			findViewById(R.id.tml_goods_input_six).setVisibility(View.GONE);
			findViewById(R.id.tml_goods_input_box).setVisibility(View.GONE);
			break;
		case 1:
			findViewById(R.id.tml_goods_input_six).setVisibility(View.GONE);
			findViewById(R.id.tml_goods_input_box).setVisibility(View.GONE);
			ImageView iv = (ImageView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.iv_single_pic);
			iv.setImageBitmap(BitmapFactory.decodeByteArray(packsImages.get(0),
					0, packsImages.get(0).length));
			TextView tv = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_single_name);
			tv.setText(packs.get(0).getPackingName());
			TextView tv1 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_unit);
			tv1.setText(packs.get(0).getUnit());
			TextView tv2 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_unit_pro);
			tv2.setText(packs.get(0).getUnit());

			break;
		case 2:
			// findViewById(R.id.tmlstore_repertory_check_six_layout).setVisibility(View.GONE);
			findViewById(R.id.tml_goods_input_box).setVisibility(View.GONE);
			ImageView iv1 = (ImageView) findViewById(
					R.id.tml_goods_input_single).findViewById(
					R.id.iv_single_pic);
			iv1.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(0), 0, packsImages.get(0).length));
			TextView tv3 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_single_name);
			tv3.setText(packs.get(0).getPackingName());
			TextView tv4 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_unit);
			tv4.setText(packs.get(0).getUnit());
			TextView tv5 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_unit_pro);
			tv5.setText(packs.get(0).getUnit());

			ImageView iv2 = (ImageView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.iv_single_pic);
			iv2.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(1), 0, packsImages.get(1).length));
			TextView tv6 = (TextView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.tv_single_name);
			tv6.setText(packs.get(1).getPackingName());
			TextView tv7 = (TextView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.tv_unit);
			tv7.setText(packs.get(1).getUnit());
			TextView tv8 = (TextView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.tv_unit_pro);
			tv8.setText(packs.get(1).getUnit());
			break;

		case 3:
			ImageView iv3 = (ImageView) findViewById(
					R.id.tml_goods_input_single).findViewById(
					R.id.iv_single_pic);
			iv3.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(0), 0, packsImages.get(0).length));
			TextView tv9 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_single_name);
			tv9.setText(packs.get(0).getPackingName());
			TextView tv10 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_unit);
			tv10.setText(packs.get(0).getUnit());
			TextView tv11 = (TextView) findViewById(R.id.tml_goods_input_single)
					.findViewById(R.id.tv_unit_pro);
			tv11.setText(packs.get(0).getUnit());

			ImageView iv4 = (ImageView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.iv_single_pic);
			iv4.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(1), 0, packsImages.get(1).length));
			TextView tv12 = (TextView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.tv_single_name);
			tv12.setText(packs.get(1).getPackingName());
			TextView tv13 = (TextView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.tv_unit);
			tv13.setText(packs.get(1).getUnit());
			TextView tv14 = (TextView) findViewById(R.id.tml_goods_input_six)
					.findViewById(R.id.tv_unit_pro);
			tv14.setText(packs.get(1).getUnit());

			ImageView iv5 = (ImageView) findViewById(R.id.tml_goods_input_box)
					.findViewById(R.id.iv_single_pic);
			iv5.setImageBitmap(BitmapFactory.decodeByteArray(
					packsImages.get(2), 0, packsImages.get(2).length));
			TextView tv15 = (TextView) findViewById(R.id.tml_goods_input_box)
					.findViewById(R.id.tv_single_name);
			tv15.setText(packs.get(2).getPackingName());
			TextView tv16 = (TextView) findViewById(R.id.tml_goods_input_box)
					.findViewById(R.id.tv_unit);
			tv16.setText(packs.get(2).getUnit());
			TextView tv17 = (TextView) findViewById(R.id.tml_goods_input_box)
					.findViewById(R.id.tv_unit_pro);
			tv17.setText(packs.get(2).getUnit());

			break;

		default:
			break;
		}

	}

	/**
	 * 提交一次进货
	 */
	protected void addAndUPLOAD(final int totalCount, final int proCount) {
		Log.d(Tag, "进货总量为: " + totalCount);

		new Thread(new Runnable() {

			@Override
			public void run() {
				Date date;
				if (0 != date_year) {
					date = new Date();
					date.setYear(date_year);
					date.setMonth(date_month);
					date.setDate(date_day);
				} else {
					date = new Date();
				}

				MyApplication app = (MyApplication) getApplication();

				new PurchasesDao().addPurchasesAndUpload(getIntent()
						.getStringExtra("id"), proCount, totalCount, date, app
						.getCurrentUser());
				myHandler.sendEmptyMessage(1);
			}
		}).start();

	}

	/**
	 * 检查用户是否输入了数量
	 */
	private boolean hasData() {
		if (et1.getText().toString().equals("")
				&& et2.getText().toString().equals("")
				&& et3.getText().toString().equals("")) {
			return false;
		}
		if ((!et1.getText().toString().equals("") && etPro1.getText()
				.toString().equals(""))
				|| (!et2.getText().toString().equals("") && etPro2.getText()
						.toString().equals(""))
				|| (!et3.getText().toString().equals("") && etPro3.getText()
						.toString().equals(""))
				|| (et1.getText().toString().equals("") && !etPro1.getText()
						.toString().equals(""))
				|| (et2.getText().toString().equals("") && !etPro2.getText()
						.toString().equals(""))
				|| (et3.getText().toString().equals("") && !etPro3.getText()
						.toString().equals(""))) {
			myHandler.sendEmptyMessage(3);
			return false;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
