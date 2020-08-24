package com.yc.snacknet.biz.impl;

import java.util.List;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.biz.IGoodsTypeBiz;
import com.yc.snacknet.dao.IGoodsTypeDao;
import com.yc.snacknet.dao.impl.GoodsTypeDaoImpl;
import com.yc.snacknet.util.StringUtil;

public class GoodsTypeBizImpl implements IGoodsTypeBiz{

	@Override
	public int add(GoodsType type) {
		if (StringUtil.checkNull(type.getTname())) {
			return -1;
		}
		IGoodsTypeDao typeDao = new GoodsTypeDaoImpl();
		return typeDao.add(type);
	}

	@Override
	public int update(GoodsType type) {
		if (StringUtil.checkNull(type.getTname())) {
			return -1;
		}
		IGoodsTypeDao typeDao = new GoodsTypeDaoImpl();
		return typeDao.update(type);
	}

	@Override
	public List<GoodsType> findAll() {
		IGoodsTypeDao typeDao = new GoodsTypeDaoImpl();
		return typeDao.findAll();
	}

	@Override
	public List<GoodsType> finds() {
		IGoodsTypeDao typeDao = new GoodsTypeDaoImpl();
		return typeDao.finds();
	}
}
