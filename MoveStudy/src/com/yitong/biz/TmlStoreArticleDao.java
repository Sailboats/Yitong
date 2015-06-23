package com.yitong.biz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.google.gson.JsonArray;
import com.yitong.avsubobject.Article;
import com.yitong.entity.HomeAdsEntity;
import com.yitong.entity.HomeEntity;

/**
 * 文章相关操作 Dao * @author caoligai
 */
public class TmlStoreArticleDao {

	private String Tag = "TmlStoreArticleDao";

	/**
	 * 查询所有新闻
	 * 
	 * @return List<HomeEntity>
	 */
	public ArrayList<HomeEntity> getAllArticle() {
		Log.d(Tag, "this.getAllArticle()");

		AVQuery<Article> query = AVObject.getQuery(Article.class);
		ArrayList<HomeEntity> result = new ArrayList<HomeEntity>();

		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");

		try {
			// ArrayList<byte[]> images = getAllArticlelistImage();
			// Log.d(Tag, "共有 " + images.size() + " 个新闻图片");

			List<Article> articles = query.find();
			Log.d(Tag, "共有 " + articles.size() + " 条新闻");
			for (Article article : articles) {
				result.add(new HomeEntity(article.getObjectId(), null, article
						.getTitle(), article.getSummary()));
			}
		} catch (AVException e) {
			e.printStackTrace();
			Log.d(Tag, "查询新闻出现错误");
		}

		return result;
	}

	/**
	 * 查询所有 Article 一级图片
	 * 
	 * @return
	 */
	public ArrayList<byte[]> getAllArticlelistImage() {
		Log.d(Tag, "this.getAllArticlelistImage()");
		ArrayList<byte[]> images = new ArrayList<byte[]>();

		AVQuery<Article> query = AVObject.getQuery(Article.class);
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addDescendingOrder("createdAt");

		try {
			List<Article> articles = query.find();
			Log.d(Tag, "共有 " + articles.size() + " 个新闻图片");
			for (Article article : articles) {
				images.add(article.getListImageFile().getData());
			}
		} catch (AVException e) {
			Log.d(Tag, "查询 Article 一级图片出现错误");
		}
		return images;
	}

	public ArrayList<HomeAdsEntity> getAllAds() {
		AVQuery<Article> query = AVQuery.getQuery(Article.class);
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.addAscendingOrder("createdAt");

		ArrayList<HomeAdsEntity> result = new ArrayList<HomeAdsEntity>();
		try {
			List<Article> articles = query.find();
			for (Article article : articles) {
//				JSONArray array = article.getJSONArray("tag");
//				Log.d(Tag, array.toString());
				if (article.getTag().toString().contains("ads")) {
					result.add(new HomeAdsEntity(article.getListImageFile()
							.getData(), article.getSummary()));
				}
			}
		} catch (AVException e) {
			e.printStackTrace();
			Log.d(Tag, "查询文章表出现错误");
		}
		Log.d(Tag, "共有 " + result.size() + " 条广告");
		return result;
	}

}
