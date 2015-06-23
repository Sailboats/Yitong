package com.yitong.baseAdapter;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.example.movestudy.R;
import com.yitong.avsubobject.Sku;
import com.yitong.widget.CircleChart02View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.renderscript.Double2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 终端店库存盘点/进货录入页面适配器 
 * * @author caoligai
 */
public class TmlStoreSkusListAdapter extends BaseAdapter {

	private String Tag = "TmlStoreSkusListAdapter";

	Context context;

	List<byte[]> images;

	List<String> names;

	List<String> objectIds;

	LayoutInflater inflater;

	public TmlStoreSkusListAdapter(Context context, LayoutInflater inflater,
			List<byte[]> images, List<String> names, List<String> objectIds) {

		Log.d(Tag, "adapter has been constructed");

		this.context = context;

		this.images = images;

		this.names = names;

		this.objectIds = objectIds;

		this.inflater = inflater;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(Tag, "=======this.getView() objectId = " + objectIds.get(position));

		viewHolder holder = null;

		if (null == convertView) {

			holder = new viewHolder();

			convertView = inflater.inflate(R.layout.tmlstore_skus_listview_item,
					null);
			holder.img = (ImageView) convertView
					.findViewById(R.id.tmlstore_listview_item_iv);
			holder.textview = (TextView) convertView
					.findViewById(R.id.tmlstore_listview_item_tv);
			holder.objectId = (TextView) convertView
					.findViewById(R.id.tmlstore_show_sku_item_objectid);
			holder.chart = (CircleChart02View) convertView.findViewById(R.id.circle_view);

			convertView.setTag(holder);

		} else {

			holder = (viewHolder) convertView.getTag();

		}

		holder.img.setImageBitmap(BitmapFactory.decodeByteArray(
				images.get(position), 0, images.get(position).length));
		holder.textview.setText(names.get(position));
		holder.objectId.setText(objectIds.get(position));
		
		holder.chart.setPercentage((int)(Math.random() * 100));
		holder.chart.chartRender();
		holder.chart.invalidate();

		return convertView;
	}

	public final class viewHolder {

		public ImageView img;

		public TextView textview;

		public TextView objectId;

		public CircleChart02View chart;
	}

}
