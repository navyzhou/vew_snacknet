package com.yc.snacknet.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.GoodsInfo;
import com.yc.snacknet.biz.IGoodsInfoBiz;
import com.yc.snacknet.dao.IGoodsInfoDao;
import com.yc.snacknet.dao.impl.GoodsInfoDaoImpl;
import com.yc.snacknet.util.StringUtil;

public class GoodsInfoBizImpl implements IGoodsInfoBiz{

	@Override
	public int add(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), gf.getPrice(), gf.getPics())) {
			return -1;
		}
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.add(gf);
	}

	@Override
	public int update(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), gf.getPrice(), gf.getPics())) {
			return -1;
		}
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.update(gf);
	}

	@Override
	public GoodsInfo findByGid(String gno) {
		if (StringUtil.checkNull(gno)) {
			return null;
		}
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.findByGid(gno);
	}

	@Override
	public Map<String, Object> findByPage(String gname, String tno, String start, String end, int page, int row) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", goodsInfoDao.total(gname, tno, start, end));
		map.put("rows", goodsInfoDao.findByPage(gname, tno, start, end, page, row));
		return map;
	}

	@Override
	public Map<String, Object> findByFirst(String gname, String tno, String sprice, String eprice, int page, int row, String order) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", goodsInfoDao.totals(gname, tno, sprice, eprice));
		map.put("rows", goodsInfoDao.finds(gname, tno, sprice, eprice, page, row, order));
		return map;
	}

	@Override
	public List<GoodsInfo> finds(String gname, String tno, String sprice, String eprice, int page, int row, String order) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.finds(gname, tno, sprice, eprice, page, row, order);
	}
}
