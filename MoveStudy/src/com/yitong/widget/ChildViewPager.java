package com.yitong.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ChildViewPager extends ViewPager {

	public ChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ChildViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//������������¼�
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
			//������һ�¸�viewpager���¼�
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
//			������һ�¸�viewpager���¼�
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		return super.onTouchEvent(arg0);
	}
}
