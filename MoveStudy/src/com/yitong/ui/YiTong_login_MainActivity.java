package com.yitong.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.movestudy.R;

/**
 * 
 * author qcj ��½activity
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

	// ��ʼ�������ռ�
	private void initView() {
		mEdtTelphone = (EditText) findViewById(R.id.yitong_edt_telphone);
		mEdtPwd = (EditText) findViewById(R.id.yitong_edt_pwd);
		mBtnLogin = (Button) findViewById(R.id.yitong_btn_login);
		mTvForgetPwd = (TextView) findViewById(R.id.yitong_tv_forgetpwd);
		mTvRegist = (TextView) findViewById(R.id.yitong_tv_register);
		mTvBrand_Dis = (TextView) findViewById(R.id.yitong_brand_distributor);
	}

	// Ϊ������ť�󶨼����¼�
	private void setListener() {
		mBtnLogin.setOnClickListener(this);
		mTvForgetPwd.setOnClickListener(this);
		mTvRegist.setOnClickListener(this);
		mTvBrand_Dis.setOnClickListener(this);
	}

	// ʵ�ֵ���¼�
	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent();
		switch (v.getId()) {
		
		case R.id.yitong_btn_login:
            String tel = mEdtTelphone.getText().toString();
            String password = mEdtPwd.getText().toString();
			
			break;

		case R.id.yitong_tv_forgetpwd:
			intent.setClass(this,YiTong_FindPwd_MainActivity.class);
			this.startActivity(intent);

			break;

		case R.id.yitong_tv_register:

			
			intent.setClass(this,YiTong_regist_MainActivity.class);
			this.startActivity(intent);
			break;

		case R.id.yitong_brand_distributor:
			intent.setClass(this,YiTong_FindPwd_MainActivity.class);
			this.startActivity(intent);
			break;

		}
		
	}
}
