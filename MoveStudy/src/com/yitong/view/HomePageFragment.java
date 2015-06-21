package com.yitong.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.example.movestudy.R;
import com.yitong.baseAdapter.BrandBasePageAdapter;
import com.yitong.baseAdapter.ImageAdapter;
import com.yitong.baseAdapter.TmlsBasePageAdapter;
import com.yitong.widget.CircleFlowIndicator;
import com.yitong.widget.ViewFlow;

/**
 * 
 * 三种用户的主页面
 * 
 * @author caoligai
 * 
 */
@SuppressLint("ValidFragment")
public class HomePageFragment extends Fragment {

	ViewFlow viewflow;
	CircleFlowIndicator viewflowIndicator;

	RelativeLayout newsLayout;
	TextView tvNewsItem;

	LinearLayout repertoryLayout;

	TableLayout table;

	Activity myActivity;

	// TmlsBasePageAdapter myAdapter;

	@SuppressLint("ValidFragment")
	public HomePageFragment(Activity activity,
			FragmentStatePagerAdapter studyBasePageAdapter) {
		myActivity = activity;
		// myAdapter = (TmlsBasePageAdapter) studyBasePageAdapter;

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// webview = (WebView) getView().findViewById(R.id.webview);
	}

	// 一个fragment里面至少要继承这些方法
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 当创建fragment时, 系统调用该方法.
		// 在实现代码中,应当初始化想要在fragment中保持的必要组件, 当fragment被暂停或者停止后可以恢复.

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		// 用户将要离开fragment时,系统调用这个方法作为第一个指示(然而它不总是意味着fragment将被销毁.) ---
		// 在当前用户会话结束之前,通常应当在这里提交任何应该持久化的变化(因为用户有可能不会返回).
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// fragment第一次绘制它的用户界面的时候, 系统会调用此方法. 为了绘制fragment的UI,此方法必须返回一个View,-----
		// 这个view是你的fragment布局的根view. 如果fragment不提供UI, 可以返回null.
		// 填充一个布局View到ViewGrope中

		View view = (View) inflater
				.inflate(R.layout.fragment_main, null, false);
		// 初始化 ViewFlow
		viewflow = (ViewFlow) view.findViewById(R.id.viewflow);
		viewflow.setAdapter(new ImageAdapter(getActivity()));
		viewflowIndicator = (CircleFlowIndicator) view
				.findViewById(R.id.viewflowindicator);
		viewflow.setFlowIndicator(viewflowIndicator);
		viewflow.setTimeSpan(1000 * 3);
		viewflow.startAutoFlowTimer();

		// 初始化新闻
		/*int[] tvid = { R.id.news_item1, R.id.news_item2, R.id.news_item3,
				R.id.news_item4, R.id.news_item5 };
		List<String> content = new ArrayList<String>();
		content.add("您的四月份销售分析报告已生成，点击查看  [2015/4/22]");
		content.add("雪花啤酒四川新春订货会圆满结束  [2015/4/22]");
		content.add("把酿酒师拉进朋友圈  [2015/4/22]");
		content.add("雪花啤酒：捧技能“明星”，酿经典品质  [2015/4/22]");
		content.add("雪花啤酒：好雇主会让你在“任性”和“韧性”中成长  [2015/4/22]");

		for (int i = 0; i < 5; i++) {
			newsLayout = (RelativeLayout) view.findViewById(tvid[i]);
			tvNewsItem = (TextView) newsLayout.findViewById(R.id.tv_new_item);
			tvNewsItem.setText(content.get(i));
		}

		// 初始化库存信息
		// LayoutInflater inflater = getLayoutInflater();
		// 加载行布局(包含两个产品)
		repertoryLayout = (LinearLayout) inflater.inflate(
				R.layout.repertory_item, null);
		// 设置第一个单元格
		repertoryLayout.findViewById(R.id.image1).setBackground(
				getResources().getDrawable(R.drawable.beer1));
		TextView status1 = (TextView) repertoryLayout
				.findViewById(R.id.status1);
		status1.setText("库存充足");
		TextView name1 = (TextView) repertoryLayout.findViewById(R.id.name1);
		name1.setText("勇闯天涯520ml");
		TextView percent1 = (TextView) repertoryLayout
				.findViewById(R.id.percent1);
		percent1.setText("85%");

		// 设置第二个单元格
		repertoryLayout.findViewById(R.id.image2).setBackground(
				getResources().getDrawable(R.drawable.beer3));
		TextView status2 = (TextView) repertoryLayout
				.findViewById(R.id.status2);
		status2.setText("库存告急");
		status2.setTextColor(Color.RED);
		TextView name2 = (TextView) repertoryLayout.findViewById(R.id.name2);
		name2.setText("干啤330ml");
		TextView percent2 = (TextView) repertoryLayout
				.findViewById(R.id.percent2);
		percent2.setText("25%");

		
		table = (TableLayout) view.findViewById(R.id.tr_table);
		TableRow row = new TableRow(getActivity());
		row.addView(repertoryLayout);
		table.addView(row);
		
		// 添加第二行
		repertoryLayout = (LinearLayout) inflater.inflate(
				R.layout.repertory_item, null);
		// 设置第一个单元格
		repertoryLayout.findViewById(R.id.image1).setBackground(
				getResources().getDrawable(R.drawable.beer4));
		TextView status3 = (TextView) repertoryLayout
				.findViewById(R.id.status1);
		status3.setText("库存充足");
		TextView name3 = (TextView) repertoryLayout.findViewById(R.id.name1);
		name3.setText("迷彩520ml");
		TextView percent3 = (TextView) repertoryLayout
				.findViewById(R.id.percent1);
		percent3.setText("60%");
		
		repertoryLayout.findViewById(R.id.item2).setVisibility(View.INVISIBLE);
		
		TableRow row2 = new TableRow(getActivity());
		row2.addView(repertoryLayout);
		table.addView(row2);*/
		
		return view;
	}

}
