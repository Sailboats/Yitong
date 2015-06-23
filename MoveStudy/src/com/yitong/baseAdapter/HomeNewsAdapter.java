package com.yitong.baseAdapter;

import java.util.ArrayList;

import com.example.movestudy.R;
import com.yitong.entity.HomeEntity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 首页新闻列表适配器 
 * * @author caoligai
 */
public class HomeNewsAdapter extends BaseAdapter {

	private ArrayList<HomeEntity> datas;
	
	private ArrayList<byte[]> images;

	LayoutInflater inflater;

	Context context;

	public HomeNewsAdapter(ArrayList<HomeEntity> datas,
			LayoutInflater inflater,ArrayList<byte[]> images, Context context) {
		super();
		this.datas = datas;
		this.images = images;
		this.inflater = inflater;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (null == convertView) {
			holder = new ViewHolder();

			convertView = inflater.inflate(
					R.layout.fragment_home_news_listview_item, null);
			holder.image = (ImageView) convertView
					.findViewById(R.id.iv_news_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.tv_news_title);
			holder.summary = (TextView) convertView
					.findViewById(R.id.tv_news_summary);
			holder.id = (TextView) convertView.findViewById(R.id.tv_news_id);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		HomeEntity data = datas.get(position);
		holder.image.setImageBitmap(BitmapFactory.decodeByteArray(images.get(position),0,images.get(position).length
				));
//		holder.image.setImageBitmap(BitmapFactory.decodeByteArray(data.getImage(), 0, data.getImage().length));
		holder.title.setText(data.getTitle());
		holder.summary.setText(data.getSummary());
		holder.id.setText(data.getId());

		return convertView;
	}

	public final class ViewHolder {
		public ImageView image;

		public TextView title;

		public TextView summary;

		public TextView id;
	}

}
