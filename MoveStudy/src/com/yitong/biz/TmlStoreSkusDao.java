package com.yitong.biz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.avos.avoscloud.AVRelation;
import com.yitong.avsubobject.PackingSpecification;
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

	

	/**
	 * 
	 * 获取所有 sku
	 * 
	 * @return
	 */
	public ArrayList<Sku> getAllSkus() {
		
		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
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

		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
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

		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
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
	
	
	/**
	 * 
	 * 获取所有 sku 的 ObjectId
	 * 
	 * @return
	 */
	public List<String> getAllObjectId(){
		
		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
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
	
	/**
	 * 根据 objectId 获取 sku
	 * 
	 * @param objectId
	 * @return
	 */
	public Sku getSkuById(String objectId){
		
		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
		Sku sku = null;
		
//		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		try {
			sku = query.get(objectId);
		} catch (AVException e) {
			Log.d(Tag, "根据 objectId 获取 sku 失败");
		}
		return sku;
	}
	
	
	/**
	 * 根据 id 获取特定 sku 的全部包装类型
	 * @param id
	 * @return
	 */
	public List<PackingSpecification> getAllpackingSpecificationById(String id){
		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
		Sku sku;
		List<PackingSpecification> list = new ArrayList<PackingSpecification>();
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");
		
		try {
			sku = query.get(id);
			AVRelation<PackingSpecification> relation = sku.getPackingSpecification();
			List<PackingSpecification> packs = relation.getQuery().find();
			Log.d(Tag, "有 " + packs.size() + " 种包装");
			for (PackingSpecification packingSpecification : packs) {
				list.add(packingSpecification);
			}
		} catch (AVException e) {
			Log.d(Tag, "根据 id 获取 sku 出错");
		}
		
		return list;
	}
	
	
	/**
	 * 根据 id 获取图片
	 * @param id
	 * @return
	 */
	public byte[] getImageById(String id){
		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		Sku sku = null;
		byte [] image = null;
		
		try {
			sku = query.get(id);
			image = sku.getImage().getData();
		} catch (AVException e) {
			Log.d(Tag, "根据 id 获取 sku 出错");
		}
		return image;
		
	}
	
	public String getNameById(String id){
		AVQuery<Sku> query = AVObject.getQuery(Sku.class);
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		Sku sku  = null;
		String name = null;
		
		try {
			sku = query.get(id);
			name = sku.getName();
		} catch (AVException e) {
			Log.d(Tag, "根据 id 获取 sku 出错");
		}
		return name;
	}

}
