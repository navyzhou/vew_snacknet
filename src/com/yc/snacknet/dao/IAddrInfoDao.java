package com.yc.snacknet.dao;

import java.util.List;

import com.yc.snacknet.bean.AddrInfo;

public interface IAddrInfoDao {
	public List<AddrInfo> findByMno(String mno);
}
