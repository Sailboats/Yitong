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
 * author qcj ע��activity
 * 
 * */
public class YiTong_regist_MainActivity extends Activity implements
		OnClickListener {
	private EditText mEdtyitong_member_register;
	private EditText mEdtyitong_member_register_edt_pwd;
	private Button mBtnyitong_member_register_btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yitong_member_register);
		initView();
		setListener();
	}

	// ��ʼ�������ռ�
	private void initView() {
		mEdtyitong_member_register = (EditText) findViewById(R.id.yitong_member_register_edt_tel);
		mEdtyitong_member_register_edt_pwd = (EditText) findViewById(R.id.yitong_member_register_edt_pwd);
		mBtnyitong_member_register_btn_register = (Button) findViewById(R.id.yitong_member_register_btn_register);
	}

	// Ϊ������ť�󶨼����¼�
	private void setListener() {
		mEdtyitong_member_register.setOnClickListener(this);
		mEdtyitong_member_register_edt_pwd.setOnClickListener(this);
		mBtnyitong_member_register_btn_register.setOnClickListener(this);
	}

	// ʵ�ֵ���¼�
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.yitong_member_register_btn_register:
			// ���ע��ʵ���߼����������

			break;

		}
	}
}
