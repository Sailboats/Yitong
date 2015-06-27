package com.yitong.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.movestudy.R;
import com.yitong.avsubobject.Article;
import com.yitong.avsubobject.Brand;
import com.yitong.avsubobject.Gift;
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

	private MyUser currentUser = null; // 当前用户

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		// 设置 Last-Modified 模式
//		AVOSCloud.setLastModifyEnabled(true);

		// 注册 LeanCloud 子类(必须在初始化 SDK　之前)
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
		AVObject.registerSubclass(Gift.class);
		
		// 初始化 LeanCloud SDK
		AVOSCloud.initialize(this,
				"to0nyg7vtky1bna4ybrclwrm3hm0r94oqw45eiost7mqrbi5",
				"kbq185r1thmzpbod54og7ml9vll7pzmb5yegd2jyyfcw3qaa");

		Log.d(Tag, "AVOSCloud has been inited");

		// NOTE:在各个界面渲染 ImageView 的时候不能直接通过 BitmapFactory.decodeByteArray()
		// 方法来直接解码 AVFile 对象，需要在后台线程中将 AVFile 对象转换成为 byte[] 数组，原因可能是因为 AVFile 的
		// getDate() 方法不能直接在 UI 线程中调用

		// 测试代码

		/*new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					Article article = new Article();
					article.setTitle("title " + i);
					article.setSummary("summary " + i);

					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					Bitmap bm = BitmapFactory.decodeResource(getResources(),
							R.drawable.test);
					bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					
					article.setListImageFile(new AVFile("testImage.jpg",stream.toByteArray()));
					article.setHeaderImageFile(new AVFile("testImage.jpg",stream.toByteArray()));
					
					LinkedList<String> tag = new LinkedList<String>();
					tag.add("news");
					if (i % 2 == 0) {
						tag.add("ads");
					}
					article.setTag(new JSONArray(tag));
					article.setSource("caoligai_test");
					try {
						article.save();
					} catch (AVException e) {
						e.printStackTrace();
					}
					
					Log.d(Tag, "成功插入一条测试数据");
				}
			}
		}.start();*/

	}

	public MyUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(MyUser currentUser) {
		this.currentUser = currentUser;
	}

}
