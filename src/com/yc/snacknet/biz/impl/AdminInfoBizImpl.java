package com.yc.snacknet.biz.impl;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.biz.IAdminInfoBiz;
import com.yc.snacknet.dao.IAdminInfoDao;
import com.yc.snacknet.dao.impl.AdminInfoDaoImpl;
import com.yc.snacknet.util.StringUtil;

public class AdminInfoBizImpl implements IAdminInfoBiz{

	@Override
	public AdminInfo login(String account, String pwd) {
		if (StringUtil.checkNull(account, pwd)) {
			return null;
		}
		
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.login(account, pwd);
	}
	
}
