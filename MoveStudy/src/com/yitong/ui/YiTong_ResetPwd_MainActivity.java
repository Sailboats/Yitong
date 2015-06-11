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
 * author qcj 重置密码密碼activity
 * 
 * */
public class YiTong_ResetPwd_MainActivity extends Activity implements
		OnClickListener {
	private EditText mEdtyitong_reset_edt_identify;
	private EditText mEdtyitong_reset_edt_newpwd;
	private Button mBtnyitong_reset_btn_reset;

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
		mEdtyitong_reset_edt_identify = (EditText) findViewById(R.id.yitong_reset_edt_identify);
		mEdtyitong_reset_edt_newpwd = (EditText) findViewById(R.id.yitong_reset_edt_newpwd);
		mBtnyitong_reset_btn_reset = (Button) findViewById(R.id.yitong_reset_btn_reset);
	}

	// 为各个按钮绑定监听事件
	private void setListener() {
		mEdtyitong_reset_edt_identify.setOnClickListener(this);
		mEdtyitong_reset_edt_newpwd.setOnClickListener(this);
		mBtnyitong_reset_btn_reset.setOnClickListener(this);
	}

	// 实现点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.yitong_reset_btn_reset:
			// 点击重置密码

			break;

		}
	}
}
