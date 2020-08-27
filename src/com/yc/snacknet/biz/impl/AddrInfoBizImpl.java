package com.yc.snacknet.biz.impl;

import java.util.Collections;
import java.util.List;

import com.yc.snacknet.bean.AddrInfo;
import com.yc.snacknet.biz.IAddrInfoBiz;
import com.yc.snacknet.dao.IAddrInfoDao;
import com.yc.snacknet.dao.impl.AddrInfoDaoImpl;
import com.yc.snacknet.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年8月25日
 * Email haijunzhou@hnit.edu.cn
 */
public class AddrInfoBizImpl implements IAddrInfoBiz {

	@Override
	public List<AddrInfo> findByMno(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		IAddrInfoDao addrInfoDao = new AddrInfoDaoImpl();
		return addrInfoDao.findByMno(mno);
	}

}
