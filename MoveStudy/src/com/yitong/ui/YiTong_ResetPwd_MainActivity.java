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
 * author qcj ���������ܴaactivity
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

	// ��ʼ�������ռ�
	private void initView() {
		mEdtyitong_reset_edt_identify = (EditText) findViewById(R.id.yitong_reset_edt_identify);
		mEdtyitong_reset_edt_newpwd = (EditText) findViewById(R.id.yitong_reset_edt_newpwd);
		mBtnyitong_reset_btn_reset = (Button) findViewById(R.id.yitong_reset_btn_reset);
	}

	// Ϊ������ť�󶨼����¼�
	private void setListener() {
		mEdtyitong_reset_edt_identify.setOnClickListener(this);
		mEdtyitong_reset_edt_newpwd.setOnClickListener(this);
		mBtnyitong_reset_btn_reset.setOnClickListener(this);
	}

	// ʵ�ֵ���¼�
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.yitong_reset_btn_reset:
			// �����������

			break;

		}
	}
}
