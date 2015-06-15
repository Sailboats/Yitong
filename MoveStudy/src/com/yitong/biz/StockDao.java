package com.yitong.biz;

import java.util.Date;
import java.util.List;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.yitong.avsubobject.MyUser;
import com.yitong.avsubobject.Stock;
import com.yitong.avsubobject.TerminalStore;

/**
 * ��װ�� Stock (����)����ز���
 * * @author caoligai
 */
public class StockDao {
	
	private String Tag = "StockDao";
	
	/**
	 * ����һ����沢�ϴ���������
	 * 
	 * @param sku
	 * @param count
	 * @param date
	 */
	public void addStockAndUpload(String skuId,int count,Date date,MyUser myuser,int updateFrom){
		Stock stock = new Stock();
		
		stock.setSku(new TmlStoreSkusDao().getSkuById(skuId));
		stock.setStock(count);
		stock.setDate(date);
		
		try {
			
			AVRelation<TerminalStore> storesRelation = myuser.getTerminalStores();
			AVQuery<TerminalStore> query = storesRelation.getQuery();
			List<TerminalStore> stores = query.find();
			stock.setStore(stores.get(0));
			
			stock.setUpdateFrom(1);
			stock.save();
		} catch (AVException e) {
			Log.d(Tag, "�ϴ� Stock ���ִ���");
		}
		
		
		
	}
	
}
