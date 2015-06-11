package com.yitong.biz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.yitong.entity.CategorysEntity;
import com.yitong.entity.HomeResponseEntity;
import com.yitong.entity.TmlStoreInputEntity;

/**
 * * @author 作者 E-mail: * @date 创建时间：2015-5-29 下午4:14:03 * @version 1.0 * @parameter
 * * @since * @return
 */
public class TmlStoreInputDao implements BaseDao{
	
	private TmlStoreInputEntity TmlStoreRespose;
	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();
	
	public TmlStoreInputEntity getStudyRespose() {
		return TmlStoreRespose;
	}
	
	public void setStudyRespose(TmlStoreInputEntity tmlStoreInputRespose) {
		this.TmlStoreRespose = tmlStoreInputRespose;
	}

	public TmlStoreInputDao(Activity mActivity){
		
	}
	//获取数据
	public TmlStoreInputEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub

		
		//声明变量
		TmlStoreRespose = new TmlStoreInputEntity();
		
	return TmlStoreRespose;

	}
	
	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();
		cate1.setName("选择将要进入的模块");
		tabs.add(cate1);
		return tabs;
	}
	
}
