package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.yitong.avsubobject.PackingSpecification;
import com.yitong.avsubobject.Sku;

/**
 * * @author caoligai
 */
public class TmlStorePacksDao {

	private String Tag = "TmlStorePacksDao";

	/**
	 * 根据包装类型获取对应的包装图片
	 * @param packs
	 * @return
	 */
	public List<byte[]> getPackImageByPacks(List<PackingSpecification> packs) {
		List<byte[]> images = new ArrayList<byte[]>();
		for (PackingSpecification packingSpecification : packs) {
			try {
				byte[] image = packingSpecification.getImage().getData();
				images.add(image);
			} catch (AVException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return images;
	}

}
