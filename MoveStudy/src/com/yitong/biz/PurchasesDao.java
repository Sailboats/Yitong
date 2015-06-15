package com.yitong.biz;

import java.util.Date;
import java.util.List;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.yitong.avsubobject.MyUser;
import com.yitong.avsubobject.Purchases;
import com.yitong.avsubobject.Sku;
import com.yitong.avsubobject.TerminalStore;

/**
 * * @author caoligai
 */
public class PurchasesDao {

	private String Tag = "PurchasesDao";
	
	
	/**
	 * 增加一条进货记录并提交到服务器
	 * @param skuId
	 * @param countPro
	 * @param count
	 * @param date
	 * @param myUser
	 */
	public void addPurchasesAndUpload(String skuId,int countPro,int count,Date date,MyUser myUser){
		Purchases purchase = new Purchases();
		
		purchase.setSku(new TmlStoreSkusDao().getSkuById(skuId));
		purchase.setCount(count);
		purchase.setStockCountBeforePurchase(countPro);
		purchase.setDate(date);
		
		try {
			AVRelation<TerminalStore> relation = myUser.getTerminalStores();
			AVQuery<TerminalStore> query = relation.getQuery();
			List<TerminalStore> stores  = query.find();
			
			purchase.setStore(stores.get(0));
			purchase.save();
		} catch (AVException e) {
			Log.d(Tag, "上传 Purchase 出现错误");
		}
		
	}
	
	
}
