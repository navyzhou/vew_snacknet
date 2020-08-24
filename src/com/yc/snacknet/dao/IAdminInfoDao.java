package com.yc.snacknet.dao;

import com.yc.snacknet.bean.AdminInfo;

public interface IAdminInfoDao {
	public AdminInfo login(String account, String pwd);
}
