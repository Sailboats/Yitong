package com.yitong.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;

import com.example.movestudy.R;
import com.yitong.baseAdapter.DistributorBasePageAdapter;
import com.yitong.baseAdapter.TmlsBasePageAdapter;
import com.yitong.biz.BaseDao;
import com.yitong.biz.Dis_TmlmDao;
import com.yitong.biz.HomeDao;
import com.yitong.config.Constants;
import com.yitong.entity.CategorysEntity;
import com.yitong.entity.Dis_tmlmResponseEntity;
import com.yitong.entity.HomeResponseEntity;
import com.yitong.entity.NavigationModel;
import com.yitong.entity.base.BaseResponseData;
import com.yitong.indicator.TitlePageIndicator;
import com.yitong.slidingmenu.SlidingMenu;
import com.yitong.ui.TmlStore_MainActivity.MyPageChangeListener;
import com.yitong.ui.TmlStore_MainActivity.MyTask;
import com.yitong.ui.TmlStore_MainActivity.tvOffAnimListener;
import com.yitong.ui.base.BaseSlidingFragmentActivity;
import com.yitong.utils.PopupWindowUtil1;

public class Distributor_MainActivity extends BaseSlidingFragmentActivity
		implements OnClickListener, AnimationListener {
	private final String LIST_TEXT = "text";
	private final String LIST_IMAGEVIEW = "img";

	// [start]变量
	/**
	 * 数字代表列表顺序
	 */
	private int mTag = 0;

	
	private View title;
	private LinearLayout mlinear_listview;

	// title标题
	private ImageView imgLeft;
	private ImageView imgRight;
	private FrameLayout mFrameTv;// FrameLayout是最简单的布局了。所有放在布局里的控件，都按照层次堆叠在屏幕的左上角。后加进来的控件覆盖前面的控件。
	// 在FrameLayout布局里，定义任何空间的位置相关的属性都毫无意义。控件自动的堆放在左上角，根本不听你的控制。
	private ImageView mImgTv;

	// views
	private ViewPager mViewPager;// ViewPager用于视图随着手势滑动进行切换
	// private TestPageAdapter mBasePageAdapter;//向viewpager里面加入fragment
	private TitlePageIndicator mIndicator;
	private LinearLayout loadLayout;
	private FrameLayout myindacaterLayout;
	private LinearLayout loadFaillayout;
	private DistributorBasePageAdapter mStudyBasePageAdapter;

	
	 private HomeDao homeDao;
	 private Dis_TmlmDao dis_TmlmDao;
	

	private List<Object> categoryList;

	private List<NavigationModel> navs;

	private ListView lvTitle;
	private SimpleAdapter lvAdapter;
	private ImageView llGoHome;
	private ImageButton imgLogin;
	private Button bn_refresh;

	private TextView mAboveTitle;
	private SlidingMenu sm;// 滑动菜单栏对象
	private boolean mIsTitleHide = false;
	private boolean mIsAnim = false;

	
	

	private String current_page;

	private InputMethodManager imm;// 当我们在Android提供的EditText中单击的时候，会自动的弹出软键盘，其实对于软键盘的控制我们可以通过InputMethodManager这个类来实现。
	// 我们需要控制软键盘的方式就是两种一个是像EditText那样当发生onClick事件的时候出现
	// 软键盘，还有就是当打开某个程序的时候自动的弹出软键盘。

	private boolean isShowPopupWindows = false;

	private Handler mHandler;

	// [end]

	// [start]生命周期
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSlidingMenu();
		setContentView(R.layout.above_slidingmenu);
		initClass();
		initControl();
		initViewPager();
		initListView();
		initgoHome();
		initNav();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}

	// [end]

	// [start]初始化函数
	private void initSlidingMenu() {
		setBehindContentView(R.layout.behind_slidingmenu);
		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		// sm.setShadowWidth(20);
		sm.setBehindScrollScale(0);
	}

	private void initControl() {

		imm = (InputMethodManager) getApplicationContext().getSystemService(
				Context.INPUT_METHOD_SERVICE);
		
		mAboveTitle = (TextView) findViewById(R.id.tv_above_title);
		mAboveTitle.setText("首页");
		// imgQuery.setVisibility(View.GONE);
		imgLeft = (ImageView) findViewById(R.id.imageview_above_left);
		imgRight = (ImageView) findViewById(R.id.imageview_above_right);
		// editSearch.setOnKeyListener(onkey);
		mViewPager = (ViewPager) findViewById(R.id.above_pager);
		mIndicator = (TitlePageIndicator) findViewById(R.id.above_indicator);
		myindacaterLayout = (FrameLayout) findViewById(R.id.myindicaterlayout);
		lvTitle = (ListView) findViewById(R.id.behind_list_show);
		llGoHome = (ImageView) findViewById(R.id.Linear_above_toHome);
		imgLogin = (ImageButton) findViewById(R.id.login_login);

		
		title = findViewById(R.id.main_title);
		mlinear_listview = (LinearLayout) findViewById(R.id.main_linear_listview);
		mFrameTv = (FrameLayout) findViewById(R.id.fl_off);
		mImgTv = (ImageView) findViewById(R.id.iv_off);

		
	}

	private void initClass() {
		
		 homeDao = new HomeDao(this);
//		 dis_TmlmDao = new Dis_TmlmDao(this);
		
	}

	private void initViewPager() {
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				NavigationModel navModel = navs.get(msg.what);
				mAboveTitle.setText(navModel.getName());
				current_page = navModel.getTags();
				View view = lvTitle.getChildAt(msg.what);// 获取Listview中的第msg.what个子view
				if (lvTitle.getTag() != null) {
					if (lvTitle.getTag() == view) {
						Distributor_MainActivity.this.showContent();
						return;
					}
					((View) lvTitle.getTag())
							.setBackgroundColor(Color.TRANSPARENT);
				}
				lvTitle.setTag(view);
				view.setBackgroundResource(R.drawable.back_behind_list);
				switch (msg.what) {
//				 case Constants.Slidingmeun.DIS_SLDM_HOME:
//				 //imgQuery.setVisibility(View.GONE);
//				 new MyDTask().execute(homeDao);
//				 break;
//				 case Constants.Slidingmeun.DIS_SLDM_TMLM:
//					 //imgQuery.setVisibility(View.GONE);
//					 new MyDTask().execute(dis_TmlmDao);
//					 break;
				
				}
				
			};
		};
		mStudyBasePageAdapter = new DistributorBasePageAdapter(Distributor_MainActivity.this, new MyDTask(), mHandler);
		mViewPager.setOffscreenPageLimit(0);

		mViewPager.setAdapter(mStudyBasePageAdapter);

		mIndicator.setViewPager(mViewPager);
		mIndicator.setOnPageChangeListener(new MyPageChangeListener());
		
		 new MyDTask().execute(homeDao);//设置刚开始的时候显示那一页

	}

	private void initListView() {// mj生成导航栏,也就是菜单栏
		lvAdapter = new SimpleAdapter(this, getData(),
				R.layout.behind_list_show, new String[] { LIST_TEXT,
						LIST_IMAGEVIEW },
				new int[] { R.id.textview_behind_title,
						R.id.imageview_behind_icon }) {// 第一个参数
														// 表示访问整个android应用程序接口，基本上所有的组件都需要
			// 第二个参数表示生成一个Map(String ,Object)列表选项
			// 第三个参数表示界面布局的id 表示该文件作为列表项的组件
			// 第四个参数表示该Map对象的哪些key对应value来生成列表项
			// 第五个参数表示来填充的组件 Map对象key对应的资源一依次填充组件 顺序有对应关系
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub.
				View view = super.getView(position, convertView, parent);
				if (position == mTag) {
					view.setBackgroundResource(R.drawable.back_behind_list);
					lvTitle.setTag(view);// 显示选中了position ==
											// mTag这个条件成立的那一项，最开始好像是显示“社区精选”（使它和其他项的背景颜色不同）
				} else {
					view.setBackgroundColor(Color.TRANSPARENT);
				}
				return view;
			}
		};
		lvTitle.setAdapter(lvAdapter);
		lvTitle.setOnItemClickListener(new OnItemClickListener() {// OnItemClickListener
																	// 是监听ListView中子条目的点击事件
			@Override
			// 进而获取每一页的内容
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				NavigationModel navModel = navs.get(position);
				mAboveTitle.setText(navModel.getName());
				current_page = navModel.getTags();
				if (lvTitle.getTag() != null) {
					if (lvTitle.getTag() == view) {
						Distributor_MainActivity.this.showContent();
						return;
					}
					((View) lvTitle.getTag())
							.setBackgroundColor(Color.TRANSPARENT);
				}
				lvTitle.setTag(view);
				view.setBackgroundResource(R.drawable.back_behind_list);
			
				switch (position) {
				 case Constants.Slidingmeun.DIS_SLDM_HOME:
					 //imgQuery.setVisibility(View.GONE);
					 new MyDTask().execute(homeDao);
					 break;
				 case Constants.Slidingmeun.DIS_SLDM_TMLM:
						 //imgQuery.setVisibility(View.GONE);
				     new MyDTask().execute(dis_TmlmDao);
					 break;

				
				}
			}
		});

	}

	private void initNav() {
		navs = new ArrayList<NavigationModel>();
		NavigationModel nav1 = new NavigationModel(getResources().getString(
				R.string.dis_menuHome), "");
		NavigationModel nav2 = new NavigationModel(getResources().getString(
				R.string.dis_menutmlsmg), Constants.TAGS.STUDY_TAG);
		NavigationModel nav3 = new NavigationModel(getResources().getString(
				R.string.dis_menuCount), Constants.TAGS.NEWS_TAG);
		NavigationModel nav4 = new NavigationModel(getResources().getString(
				R.string.dis_menuSet), Constants.TAGS.WIKI_TAG);
		
		Collections
				.addAll(navs, nav1, nav2, nav3, nav4);
	}

	private void initgoHome() {
		llGoHome.setOnClickListener(this);
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(LIST_TEXT, getResources().getString(R.string.dis_menuHome));
		map.put(LIST_IMAGEVIEW, R.drawable.dis_menu_handpick);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put(LIST_TEXT, getResources().getString(R.string.dis_menutmlsmg));
		map.put(LIST_IMAGEVIEW, R.drawable.dis_menu_studio);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put(LIST_TEXT, getResources().getString(R.string.dis_menuCount));
		map.put(LIST_IMAGEVIEW, R.drawable.dis_menu_news);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put(LIST_TEXT, getResources().getString(R.string.dis_menuSet));
		map.put(LIST_IMAGEVIEW, R.drawable.dis_menu_blog);
		list.add(map);
		
		return list;
	}

	// [end]

	// [start]继承方法
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Linear_above_toHome:// 监听above_title中的控件
			showMenu();// 调出菜单栏
			break;
		
		
		}
		

	}

	/**
	 * 连续按两次返回键就退出
	 */
	private int keyBackClickCount = 0;

	@Override
	protected void onResume() {
		super.onResume();
		keyBackClickCount = 0;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			switch (keyBackClickCount++) {
			case 0:
				Toast.makeText(this,
						getResources().getString(R.string.press_again_exit),
						Toast.LENGTH_SHORT).show();
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						keyBackClickCount = 0;
					}
				}, 3000);
				break;
			case 1:
				mFrameTv.setVisibility(View.VISIBLE);
				mImgTv.setVisibility(View.VISIBLE);
				Animation anim = AnimationUtils.loadAnimation(
						Distributor_MainActivity.this, R.anim.tv_off);
				anim.setAnimationListener(new tvOffAnimListener());
				mImgTv.startAnimation(anim);
				break;
			default:
				break;
			}
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {

			if (sm.isMenuShowing()) {
				toggle();
			} else {
				showMenu();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.dispatchTouchEvent(event);
		if (mIsAnim || mViewPager.getChildCount() <= 1) {
			return false;
		}
		final int action = event.getAction();

		float x = event.getX();
		float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			lastY = y;
			lastX = x;
			return false;
		case MotionEvent.ACTION_MOVE:
			float dY = Math.abs(y - lastY);
			float dX = Math.abs(x - lastX);
			boolean down = y > lastY ? true : false;
			lastY = y;
			lastX = x;
			if (dX < 8 && dY > 8 && !mIsTitleHide && !down) {
				Animation anim = AnimationUtils.loadAnimation(
						Distributor_MainActivity.this, R.anim.push_top_in);
				// anim.setFillAfter(true);
				anim.setAnimationListener(Distributor_MainActivity.this);
				title.startAnimation(anim);
			} else if (dX < 8 && dY > 8 && mIsTitleHide && down) {
				Animation anim = AnimationUtils.loadAnimation(
						Distributor_MainActivity.this, R.anim.push_top_out);
				// anim.setFillAfter(true);
				anim.setAnimationListener(Distributor_MainActivity.this);
				title.startAnimation(anim);
			} else {
				return false;
			}
			mIsTitleHide = !mIsTitleHide;
			mIsAnim = true;
			break;
		default:
			return false;
		}
		return false;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	// [end]

	/**
	 * 加载分类list的task
	 * 
	 * @author wangxin
	 */
	public class MyDTask extends AsyncTask<BaseDao, String, Map<String, Object>> {// 实现异步任务机制

		private boolean mUseCache;

		public MyDTask() {
			System.out.println(">>>>>>>>>>>>..............1");
			mUseCache = true;
		}

		public MyDTask(boolean useCache) {
			mUseCache = useCache;
		}

		@Override
		protected void onPreExecute() {// 在execute(Params...
										// params)被调用后立即执行，一般用来在执行后台任务前对UI做一些标记。
			// TODO Auto-generated method stub
			System.out.println(">>>>>>>>>>>>..............2");
			imgLeft.setVisibility(View.GONE);
			imgRight.setVisibility(View.GONE);
			// loadLayout.setVisibility(View.VISIBLE);
			mViewPager.setVisibility(View.GONE);
			mViewPager.removeAllViews();
			mStudyBasePageAdapter.Clear();
			Distributor_MainActivity.this.showContent();
			super.onPreExecute();
			isShowPopupWindows = false;
		}

		@Override
		protected Map<String, Object> doInBackground(BaseDao... params) {// 在onPreExecute()完成后立即执行，
			// 用于执行较为费时的操作，此方法将接收输入参数和返回计算结果。
			// 在执行过程中可以调用publishProgress(Progress... values)来更新进度信息。
			System.out.println(">>>>>>>>>>>>..............3");
			BaseDao dao = params[0];
			List<CategorysEntity> categorys;
			Map<String, Object> map = new HashMap<String, Object>();

			
			 if (dao instanceof HomeDao) {
			 mTag = 0;
			 HomeResponseEntity myrespResponseEntity;
			 if ((myrespResponseEntity = homeDao.mapperJson(true)) != null) {
			 //mIndicator.setVisibility(View.GONE);
			 try {
			
			 if(myindacaterLayout.getVisibility() == View.VISIBLE){
			 myindacaterLayout.setVisibility(View.GONE);
			 }
			 } catch (Exception e) {
			 // TODO: handle exception
			 }
			
			 categorys = homeDao.getCategorys();
			 map.put("tabs", categorys);
			 map.put("Entity", myrespResponseEntity);
			 }
			 }
			 else if(dao instanceof Dis_TmlmDao){
				 mTag = 1;
				 Dis_tmlmResponseEntity myrespResponseEntity;
				 if ((myrespResponseEntity = dis_TmlmDao.mapperJson(true)) != null) {
				 //mIndicator.setVisibility(View.GONE);
				 try {
				
				 if(myindacaterLayout.getVisibility() == View.VISIBLE){
				 myindacaterLayout.setVisibility(View.GONE);
				 }
				 } catch (Exception e) {
				 // TODO: handle exception
				 }
				
				 categorys = dis_TmlmDao.getCategorys();
				 map.put("tabs", categorys);
				 map.put("Entity", myrespResponseEntity);
				 }
			}
			

			return map;
		}

		@Override
		protected void onPostExecute(Map<String, Object> result) {// 当后台操作结束时，此方法将会被调用，
			// 计算结果将做为参数传递到此方法中，直接将结果显示到UI组件上。
			// TODO Auto-generated method stub
			System.out.println(">>>>>>>>>>>>..............4");
			super.onPostExecute(result);
			isShowPopupWindows = true;
			mStudyBasePageAdapter.Clear();
			mViewPager.removeAllViews();
			if (!result.isEmpty()) {// 获得的资源在这儿和pager进行匹配
				mStudyBasePageAdapter.addFragment((List) result.get("tabs"),
						(BaseResponseData) result.get("Entity"));
				imgRight.setVisibility(View.VISIBLE);
				// loadLayout.setVisibility(View.GONE);//View.GONE隐藏
				// 并且不占用界面空间：不占用页面空间就是说在页面上不给它留位置
				// loadFaillayout.setVisibility(View.GONE);//VIew.InVisible控件隐藏
				// 占用界面空间
			} else {
				mStudyBasePageAdapter.addNullFragment();
				// loadLayout.setVisibility(View.GONE);
				// loadFaillayout.setVisibility(View.VISIBLE);
			}
			mViewPager.setVisibility(View.VISIBLE);// 显示这个pager
			mStudyBasePageAdapter.notifyDataSetChanged();// notifyDataSetChanged方法通过一个外部的方法控制
			// 如果适配器的内容改变时需要强制调用getView来刷新每个Item的内容,可以实现动态的刷新列表的功能。
			mViewPager.setCurrentItem(0);
			mIndicator.notifyDataSetChanged();

		}
	}

	/**
	 * viewPager切换页面
	 * 
	 * @author mingxv
	 */
	class MyPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			if (arg0 == 0) {
				getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_FULLSCREEN);
				imgLeft.setVisibility(View.GONE);
			} else if (arg0 == mStudyBasePageAdapter.mFragments.size() - 1) {
				imgRight.setVisibility(View.GONE);
				getSlidingMenu()
						.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			} else {
				imgRight.setVisibility(View.VISIBLE);
				imgLeft.setVisibility(View.VISIBLE);
				getSlidingMenu()
						.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			}
		}
	}

	class tvOffAnimListener implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			defaultFinish();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (mIsTitleHide) {
			title.setVisibility(View.GONE);
		} else {

		}
		mIsAnim = false;
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		title.setVisibility(View.VISIBLE);
		if (mIsTitleHide) {
			FrameLayout.LayoutParams lp = (LayoutParams) mlinear_listview
					.getLayoutParams();
			lp.setMargins(0, 0, 0, 0);
			mlinear_listview.setLayoutParams(lp);
		} else {
			FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) title
					.getLayoutParams();
			lp.setMargins(0, 0, 0, 0);
			title.setLayoutParams(lp);
			FrameLayout.LayoutParams lp1 = (LayoutParams) mlinear_listview
					.getLayoutParams();
			lp1.setMargins(0,
					getResources().getDimensionPixelSize(R.dimen.title_height),
					0, 0);
			mlinear_listview.setLayoutParams(lp1);
		}
	}

	private float lastX = 0;
	private float lastY = 0;

}
