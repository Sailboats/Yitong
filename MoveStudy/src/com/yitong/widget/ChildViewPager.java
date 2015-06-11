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
		//拦截这个触发事件
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
			//屏蔽了一下父viewpager的事件
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
//			屏蔽了一下父viewpager的事件
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		return super.onTouchEvent(arg0);
	}
}
