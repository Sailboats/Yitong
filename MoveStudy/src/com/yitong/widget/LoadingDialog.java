package com.yitong.widget;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 各个页面加载网络数据时显示的提示“正在加载” Dialog
 * * @author caoligai
 */
public class LoadingDialog {
	
	ProgressDialog pd ;
	
	public ProgressDialog showDialog(Context context,String message){
		
		pd = new ProgressDialog(context);
		pd.setCancelable(false);
		pd.setMessage(message);
		
		pd.show();
		
		return pd;
		
	}
	
}
