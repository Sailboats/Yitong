package com.yitong.biz;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.HomeResponseEntity;
import com.yitong.entity.base.BaseResponseData;

import android.app.Activity;

public interface BaseDao {
	/**
	 * 作用：dao的基础类
	 */
	ObjectMapper mObjectMapper = new ObjectMapper();

	public BaseResponseData mapperJson(boolean useCache);

	public List<CategorysEntity> getCategorys();
}
