package com.yitong.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movestudy.R;

/**
 * 
 * author qcj 找回密碼activity
 * 
 * */
public class YiTong_FindPwd_MainActivity extends Activity implements
		OnClickListener {
	private EditText mEdtyitong_findpwd_edt_tel;
	private Button mBtnyitong_findpwd_btn_findpwd;
	private ImageView mIvyitong_findpwd_edt_cancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yitong_findpwd);
		initView();
		setListener();
	}

	// 初始化各个空间
	private void initView() {
		mEdtyitong_findpwd_edt_tel = (EditText) findViewById(R.id.yitong_findpwd_edt_tel);
		mBtnyitong_findpwd_btn_findpwd = (Button) findViewById(R.id.yitong_findpwd_btn_findpwd);
		mIvyitong_findpwd_edt_cancle = (ImageView) findViewById(R.id.yitong_findpwd_edt_cancle);
	}

	// 为各个按钮绑定监听事件
	private void setListener() {
		mEdtyitong_findpwd_edt_tel.setOnClickListener(this);
		mBtnyitong_findpwd_btn_findpwd.setOnClickListener(this);
		mIvyitong_findpwd_edt_cancle.setOnClickListener(this);
	}

	// 实现点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.yitong_findpwd_btn_findpwd:
			// 点击实现找回密码

			break;
		case R.id.yitong_findpwd_edt_cancle:
			// 点击取消输入内容

			break;

		}
	}
}
