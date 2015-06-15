package com.yitong.app;

import java.io.File;
import java.util.List;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.yitong.avsubobject.Article;
import com.yitong.avsubobject.Brand;
import com.yitong.avsubobject.ImageText;
import com.yitong.avsubobject.InvestigateEveryday;
import com.yitong.avsubobject.MarketingChannel;
import com.yitong.avsubobject.MyUser;
import com.yitong.avsubobject.PackingSpecification;
import com.yitong.avsubobject.Promotion;
import com.yitong.avsubobject.Purchases;
import com.yitong.avsubobject.Sku;
import com.yitong.avsubobject.Stock;
import com.yitong.avsubobject.TerminalStore;
import com.yitong.biz.TmlStoreSkusDao;

public class MyApplication extends Application {

	private String Tag = "MyApplication";

	private MyUser currentUser = null; // ��ǰ�û�

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		// ���� Last-Modified ģʽ
		AVOSCloud.setLastModifyEnabled(true);

		// ע�� LeanCloud ����(�����ڳ�ʼ�� SDK��֮ǰ)
		AVObject.registerSubclass(Sku.class);
		AVObject.registerSubclass(TerminalStore.class);
		AVObject.registerSubclass(Brand.class);
		AVObject.registerSubclass(MarketingChannel.class);
		AVObject.registerSubclass(MarketingChannel.class);
		AVObject.registerSubclass(PackingSpecification.class);
		AVObject.registerSubclass(Stock.class);
		AVObject.registerSubclass(Purchases.class);
		AVObject.registerSubclass(InvestigateEveryday.class);
		AVObject.registerSubclass(Promotion.class);
		AVObject.registerSubclass(Article.class);
		AVObject.registerSubclass(ImageText.class);

		// ��ʼ�� LeanCloud SDK
		AVOSCloud.initialize(this,
				"to0nyg7vtky1bna4ybrclwrm3hm0r94oqw45eiost7mqrbi5",
				"kbq185r1thmzpbod54og7ml9vll7pzmb5yegd2jyyfcw3qaa");

		Log.d(Tag, "AVOSCloud has been inited");

		// NOTE:�ڸ���������Ⱦ ImageView ��ʱ����ֱ��ͨ�� BitmapFactory.decodeByteArray()
		// ������ֱ�ӽ��� AVFile ������Ҫ�ں�̨�߳��н� AVFile ����ת����Ϊ byte[] ���飬ԭ���������Ϊ AVFile ��
		// getDate() ��������ֱ���� UI �߳��е���

		// ���Դ���
		/*
		 * new Thread(){
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * List<String> names = new TmlStoreSkusDao().getAllSkuNames();
		 * 
		 * for (String string : names) { Log.d(Tag, string); }
		 * 
		 * }
		 * 
		 * 
		 * }.start();
		 */

	}

	public MyUser getCurrentUser() {
			return currentUser;
	}

	public void setCurrentUser(MyUser currentUser) {
		this.currentUser = currentUser;
	}

}
