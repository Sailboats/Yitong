package com.yitong.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movestudy.R;
/**
 * author qcj
 * 
 * */
public class YiTongStatictiscDayFragment extends Fragment {
	private Context context;

	public YiTongStatictiscDayFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = (View) inflater.inflate(R.layout.yitong_statistics_fragment,
				null, false);
		return view;
	}

}
