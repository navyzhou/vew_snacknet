package com.yc.snacknet.biz;

import java.util.List;

import com.yc.snacknet.bean.AddrInfo;

public interface IAddrInfoBiz {
	public List<AddrInfo> findByMno(String mno);
}
