package com.yc.snacknet.dao.impl;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.IAdminInfoDao;

public class AdminInfoDaoImpl implements IAdminInfoDao{
	@Override
	public AdminInfo login(String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select aid, aname, tel from admininfo where (aname=? or tel=?) and pwd=md5(?) and status !=0";
		return db.find(AdminInfo.class, sql, account, account, pwd);
	}
}
