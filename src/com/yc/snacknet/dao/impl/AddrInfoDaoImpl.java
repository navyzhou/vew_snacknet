package com.yc.snacknet.dao.impl;

import java.util.List;

import com.yc.snacknet.bean.AddrInfo;
import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.IAddrInfoDao;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年8月25日
 * Email haijunzhou@hnit.edu.cn
 */
public class AddrInfoDaoImpl implements IAddrInfoDao{

	@Override
	public List<AddrInfo> findByMno(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select ano, mno, name, tel, province, city, area, addr, flag from addrinfo where status!=0 and mno=?";
		return db.finds(AddrInfo.class, sql, mno);
	}

}
