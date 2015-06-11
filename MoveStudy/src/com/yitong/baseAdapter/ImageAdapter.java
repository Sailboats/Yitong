/*
 * Copyright (C) 2011 Patrik �kerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yitong.baseAdapter;



import com.example.movestudy.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * 终端店首页的滚动图片的�?配器
 * 
 * @author caoligai
 *
 */
public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private static final int[] ids = { R.drawable.picture1,
			R.drawable.picture2, R.drawable.picture3 };

	public ImageAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;// 返回很大的�?使得getView中的position不断增大来实现循�?	
		}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.viewflow_item, null);
		}
		((ImageView) convertView.findViewById(R.id.iv_viewflow_item))
				.setImageResource(ids[position % ids.length]);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(mContext, DetailActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putInt("image_id", ids[position % ids.length]);
				// intent.putExtras(bundle);
				// mContext.startActivity(intent);

				Toast.makeText(mContext, "点击了第 " + position % ids.length  + " 张图�?",
						Toast.LENGTH_SHORT).show();

			}
		});
		return convertView;
	}

}
