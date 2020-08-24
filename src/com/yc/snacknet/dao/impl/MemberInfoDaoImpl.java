package com.yc.snacknet.dao.impl;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.IMemberInfoDao;

public class MemberInfoDaoImpl implements IMemberInfoDao{
	@Override
	public MemberInfo login(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "select mno, nickName, realName, tel, email, photo from memberInfo "
				+ "where status != 0 and (nickName=? or tel=? or email=?) and pwd=md5(?)";
		return db.find(MemberInfo.class, sql, mf.getNickName(), mf.getNickName(), mf.getNickName(), mf.getPwd());
	}
}
