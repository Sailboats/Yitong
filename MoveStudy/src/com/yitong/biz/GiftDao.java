package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.yitong.avsubobject.Gift;
import com.yitong.entity.GiftEntity;

/**
 * 封闭了服务器端 Gift 表相关操作 * @author caoligai
 */
public class GiftDao {

	private String Tag = "GiftDao";

	public ArrayList<GiftEntity> getAllGifts(int limit) {
		Log.d(Tag, "this.getAllGifts()");
		
		ArrayList<GiftEntity> result = new ArrayList<GiftEntity>();

		AVQuery<Gift> query = AVObject.getQuery(Gift.class);
		query.setCachePolicy(CachePolicy.NETWORK_ELSE_CACHE);
		query.addDescendingOrder("createdAt");
		query.setLimit(limit);

		try {
			List<Gift> gifts = query.find();
			for (Gift gift : gifts) {
				result.add(new GiftEntity(gift.getObjectId(), gift
						.getGiftName(), gift.getImage().getData(), gift.getSummary()));
			}
		} catch (AVException e) {
			e.printStackTrace();
			Log.d(Tag, "查询礼品表出现错误");
		}

		return result;
	}
	
	

}
