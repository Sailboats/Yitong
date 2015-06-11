package com.yitong.view;

import com.example.movestudy.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 
 * ÖÕ¶Ëµê½ø»õÂ¼Èë Activity
 * 
 * * @author caoligai
 */
public class TmlStoreGoodsInputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tmlstore_goods_input);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
