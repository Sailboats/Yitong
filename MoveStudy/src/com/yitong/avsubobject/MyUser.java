package com.yitong.avsubobject;

import com.alibaba.fastjson.annotation.JSONType;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;

/**
 * 
 * 服务器用户表的子类(直接继承 AVUser,不用注解和注册)
 * 
 * * @author caoligai
 */
@JSONType(ignores = "terminalStores")
public class MyUser extends AVUser {

	// 昵称
	public final String NICKNAME = "nickname";

	public String getNickName() {
		return getString(NICKNAME);
	}

	public void setNickName(String name) {
		put(NICKNAME, name);
	}

	
	// 用户名
	public final String USERNAME = "username";
	public String getUserName(){
		return getString(USERNAME);
	}
	public void setUserName(String name){
		put(USERNAME, name);
	}
	
	
	// 头像
	public final String HEADPORTRAIT = "headPortrait";
	public AVFile getHeadportrait(){
		return getAVFile(HEADPORTRAIT);
	}
	public void setHeadPortrait(AVFile file){
		put(HEADPORTRAIT, file);
	}
	
	
	// 角色
	public final String ROLE = "role";
	public int getRole(){
		return getInt(ROLE);
	}
	public void setRole(int role){
		put(ROLE, role);
	}
	
	
	// 管理终端店
	public final String TERMINALSTORES = "terminalStores";
	public AVRelation<TerminalStore> getTerminalStores(){
		return getRelation(TERMINALSTORES);
	}
	public void setTerminalStores(AVRelation<TerminalStore> relation){
		put(TERMINALSTORES, relation);
	}
	
	
	

}
