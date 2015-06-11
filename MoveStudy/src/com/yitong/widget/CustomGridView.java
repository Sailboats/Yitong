package com.yitong.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.GridView;

public class CustomGridView extends GridView {
	/**
	 * GridView(网格视图)是按照行列的方式来显示内容的，一般用于显示图片，图片等内容，比如实现九宫格图，用GridView是首选，
	 * 也是最简单的。主要用于设置Adapter。(之九宫格也是用它）
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */

	public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}

	public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}

	public CustomGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
