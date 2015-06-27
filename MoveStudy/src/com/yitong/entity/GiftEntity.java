package com.yitong.entity;

/**
 * 积分商城首页实体类
 * * @author caoligai
 */
public class GiftEntity {

	private String objectId;
	
	private String giftName;
	
	private byte[] image;
	
	private String summary;
	
	public GiftEntity() {
		
	}

	public GiftEntity(String objectId, String giftName, byte[] image,
			String summary) {
		super();
		this.objectId = objectId;
		this.giftName = giftName;
		this.image = image;
		this.summary = summary;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
