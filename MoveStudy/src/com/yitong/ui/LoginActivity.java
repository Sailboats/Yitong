package com.yitong.ui;

import com.example.movestudy.R;
import com.yitong.ui.Brand_MainActivity;
import com.yitong.ui.Distributor_MainActivity;
import com.yitong.ui.TmlStore_MainActivity;
import com.yitong.ui.base.BaseActivity;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Button but1 =(Button) findViewById(R.id.brand_login);
		Button but2 =(Button) findViewById(R.id.dis_login);
		Button but3 =(Button) findViewById(R.id.tml_login);

		but1.setOnClickListener(this);
		but2.setOnClickListener(this);
		but3.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.brand_login) {
			openActivity(Brand_MainActivity.class);
		}else if (arg0.getId() == R.id.dis_login) {
			openActivity(Distributor_MainActivity.class);
		}else if(arg0.getId() == R.id.tml_login){
			openActivity(TmlStore_MainActivity.class);
		}
	}






}
