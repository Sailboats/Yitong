package com.yitong.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.movestudy.R;
import com.yitong.app.MyApplication;
import com.yitong.avsubobject.MyUser;

/**
 * 
 * author qcj 锟斤拷陆activity
 * 
 * */
public class YiTong_login_MainActivity extends Activity implements
		OnClickListener {
	private EditText mEdtTelphone;
	private EditText mEdtPwd;
	private Button mBtnLogin;
	private TextView mTvForgetPwd;
	private TextView mTvRegist;
	private TextView mTvBrand_Dis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yitong_login);
		initView();
		setListener();
	}

	// 锟斤拷始锟斤拷锟斤拷锟斤拷锟秸硷拷
	private void initView() {
		mEdtTelphone = (EditText) findViewById(R.id.yitong_edt_telphone);
		mEdtPwd = (EditText) findViewById(R.id.yitong_edt_pwd);
		mBtnLogin = (Button) findViewById(R.id.yitong_btn_login);
		mTvForgetPwd = (TextView) findViewById(R.id.yitong_tv_forgetpwd);
		mTvRegist = (TextView) findViewById(R.id.yitong_tv_register);
		mTvBrand_Dis = (TextView) findViewById(R.id.yitong_brand_distributor);
	}

	// 为锟斤拷锟斤拷锟斤拷钮锟襟定硷拷锟斤拷锟铰硷拷
	private void setListener() {
		mBtnLogin.setOnClickListener(this);
		mTvForgetPwd.setOnClickListener(this);
		mTvRegist.setOnClickListener(this);
		mTvBrand_Dis.setOnClickListener(this);
	}

	// 实锟街碉拷锟斤拷录锟�
	@Override
	public void onClick(View v) {

		Intent intent = new Intent();
		switch (v.getId()) {

		case R.id.yitong_btn_login:
			final String tel = mEdtTelphone.getText().toString().trim();
			final String password = mEdtPwd.getText().toString().trim();
			Log.d(null, "tel = " + tel + " password = " + password);
			new Thread() {

				@Override
				public void run() {
					super.run();

					MyUser myUser = null;

					try {

						myUser = AVUser.logIn(tel, password,MyUser.class);
						Log.d(null, "昵称: " + myUser.getNickName());
						MyApplication app = (MyApplication) getApplication();
						app.setCurrentUser(myUser);
						if (null != myUser) {
							startActivity(new Intent(
									YiTong_login_MainActivity.this,
									LoginActivity.class));
							finish();
						} else {
							Toast.makeText(YiTong_login_MainActivity.this,
									"登录失败", Toast.LENGTH_SHORT).show();
						}

					} catch (AVException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}.start();

			break;

		case R.id.yitong_tv_forgetpwd:
			intent.setClass(this, YiTong_FindPwd_MainActivity.class);
			this.startActivity(intent);
			break;

		case R.id.yitong_tv_register:

			intent.setClass(this, YiTong_regist_MainActivity.class);
			this.startActivity(intent);
			break;

		case R.id.yitong_brand_distributor:
			intent.setClass(this, YiTong_FindPwd_MainActivity.class);
			this.startActivity(intent);
			break;

		}

	}
}
