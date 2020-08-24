package com.yc.snacknet.biz.impl;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.biz.IMemberInfoBiz;
import com.yc.snacknet.dao.IMemberInfoDao;
import com.yc.snacknet.dao.impl.MemberInfoDaoImpl;
import com.yc.snacknet.util.StringUtil;

public class MemberInfoBizImpl implements IMemberInfoBiz{
	@Override
	public MemberInfo login(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getNickName(), mf.getPwd())) {
			return null;
		}
		
		IMemberInfoDao memberInfoDao = new MemberInfoDaoImpl();
		return memberInfoDao.login(mf);
	}

}
