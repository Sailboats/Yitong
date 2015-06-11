package com.yitong.biz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.yitong.avsubobject.Sku;

/**
 * 
 * 终端店查询 sku
 * 
 * * @author caoligai
 */
public class TmlStoreSkusDao {

	private String Tag = "TmlStoreSkusDao";

	public TmlStoreSkusDao() {
	}

	AVQuery<Sku> query = AVObject.getQuery(Sku.class);

	/**
	 * 
	 * 获取所有 sku
	 * 
	 * @return
	 */
	public ArrayList<Sku> getAllSkus() {
		
		ArrayList<Sku> skus = new ArrayList<Sku>();
		
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");
		
		try {
			List<Sku> list = query.find();
			Log.d(Tag, "共有  " + skus.size() + " 条 sku 记录");
			
			for (Sku sku : list) {
				skus.add(sku);
			}
			
			return skus;
		} catch (Exception e) {
			Log.d(Tag, "查询所有 sku 出现错误");
		}
		return skus;
	}

	/**
	 * 得到所有 sku 的名字
	 * 
	 * @return 包含所有 sku 名字的 list
	 */
	public List<String> getAllSkuNames() {

		List<String> list = new ArrayList<String>();

		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");

		try {
			List<Sku> skus = query.find();
			Log.d(Tag, "共有  " + skus.size() + " 条 sku 记录");

			for (Sku sku : skus) {
				String name = sku.getString("skuName");
				list.add(name);
			}

		} catch (AVException e) {
			e.printStackTrace();
			Log.d(Tag, "查询错误");
		}

		return list;
	}

	/**
	 * 得到所有 sku 的图片
	 * 
	 * @return
	 */
	public List<byte[]> getAllSkuImage() {

		List<byte[]> list = new ArrayList<byte[]>();

		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");

		try {
			List<Sku> skus = query.find();
			Log.d(Tag, "共有  " + skus.size() + " 条 sku 记录");
			
			for (Sku sku : skus) {
				AVFile file = sku.getAVFile("image");
				list.add(file.getData());
			}
		} catch (Exception e) {
			Log.d(Tag, "查询出现错误");
		}
		return list;
	}
	
	
	public List<String> getAllObjectId(){
		
		List<String> ids = new ArrayList<String>();
		
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");
		
		try {
			List<Sku> list = query.find();
			for (Sku sku : list) {
				ids.add(sku.getObjectId());
			}
		} catch (AVException e) {
			Log.d(Tag, "查询 sku id 时出现错误");
		}
		
		return ids;
	}

}
