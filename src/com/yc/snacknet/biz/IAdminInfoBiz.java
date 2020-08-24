package com.yc.snacknet.biz;

import com.yc.snacknet.bean.AdminInfo;

public interface IAdminInfoBiz {
	public AdminInfo login(String account, String pwd);
}
