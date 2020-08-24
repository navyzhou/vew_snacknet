package com.yc.snacknet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.snacknet.bean.GoodsInfo;
import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.IGoodsInfoDao;
import com.yc.snacknet.util.StringUtil;

public class GoodsInfoDaoImpl implements IGoodsInfoDao {

	@Override
	public int add(GoodsInfo gf) {
		DBHelper db = new DBHelper();
		String sql = "insert into goodsinfo values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return db.update(sql, gf.getGname(), gf.getTno(), gf.getPrice(), gf.getIntro(), gf.getBalance(),
				gf.getPics(), gf.getUnit(), gf.getQperied(), gf.getWeight(), gf.getDescr(), gf.getStatus());
	}

	@Override
	public int update(GoodsInfo gf) {
		DBHelper db = new DBHelper();
		String sql = "update goodsinfo set gname=?, tno=?, price=?, intro=?, balance=?, pics=?, unit=?, qperied=?, weight=?, descr=? where gno=?";
		return db.update(sql, gf.getGname(), gf.getTno(), gf.getPrice(), gf.getIntro(), gf.getBalance(),
				gf.getPics(), gf.getUnit(), gf.getQperied(), gf.getWeight(), gf.getDescr(), gf.getStatus(), gf.getGno());
	}

	@Override
	public GoodsInfo findByGid(String gno) {
		DBHelper db = new DBHelper();
		String sql = "select gno, gname, g.tno, tname, price, balance, unit, qperied, weight, g.status, intro, pics, descr"
				+ " from goodsinfo g, goodstype t  where g.tno = t.tno and gno=?";
		return db.find(GoodsInfo.class, sql, gno);
	}

	@Override
	public List<GoodsInfo> findByPage(String gname, String tno, String start, String end, int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select gno, gname, g.tno, tname, price, balance, unit, qperied, weight, g.status from goodsinfo g, goodstype t"
				+ " where g.tno=t.tno";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		
		if (!StringUtil.checkNull(tno)) {
			sql += " and g.tno=?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(start)) {
			sql += " and balance >= ?";
			params.add(start);
		}
		
		if (!StringUtil.checkNull(end)) {
			sql += " and balance <= ?";
			params.add(end);
		}
		sql += " order by gno desc limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(GoodsInfo.class, sql, params);
	}

	@Override
	public int total(String gname, String tno, String start, String end) {
		DBHelper db = new DBHelper();
		String sql = "select count(gno) from goodsinfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno=?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(start)) {
			sql += " and balance >= ?";
			params.add(start);
		}
		
		if (!StringUtil.checkNull(end)) {
			sql += " and balance <= ?";
			params.add(end);
		}
		return db.total(sql, params);
	}

	@Override
	public List<GoodsInfo> finds(String gname, String tno, String sprice, String eprice, int page, int rows, String order) {
		DBHelper db = new DBHelper();
		String sql = "select gno, gname, tno, price, weight, pics pic from goodsinfo where status !=0";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno=?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		
		if (!StringUtil.checkNull(sprice)) {
			sql += " and price >= ?";
			params.add(sprice);
		}
		
		if (!StringUtil.checkNull(eprice)) {
			sql += " and price <=?";
			params.add(eprice);
		}
		
		if (!StringUtil.checkNull(order)) {
			sql += " order by price " + order;
		} else {
			sql += " order by gno desc";
		}
		
		sql += " limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(GoodsInfo.class, sql, params);
	}

	@Override
	public int totals(String gname, String tno, String sprice, String eprice) {
		DBHelper db = new DBHelper();
		String sql = "select count(gno) from goodsinfo where status !=0";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno=?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		
		if (!StringUtil.checkNull(sprice)) {
			sql += " and price >= ?";
			params.add(sprice);
		}
		
		if (!StringUtil.checkNull(eprice)) {
			sql += " and price <=?";
			params.add(eprice);
		}
		return db.total(sql, params);
	}
}
