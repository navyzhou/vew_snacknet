package com.yc.snacknet.dao.impl;

import java.util.List;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.IGoodsTypeDao;

public class GoodsTypeDaoImpl implements IGoodsTypeDao{
	@Override
	public int add(GoodsType type) {
		DBHelper db = new DBHelper();
		String sql = "insert into goodstype values(0, ?, ?)";
		return db.update(sql, type.getTname(), type.getStatus());
	}

	@Override
	public int update(GoodsType type) {
		DBHelper db = new DBHelper();
		String sql = "update goodstype set tname=?, status=? where tno=?";
		return db.update(sql, type.getTname(), type.getStatus(), type.getTno());
	}

	@Override
	public List<GoodsType> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select tno, tname, status from goodstype";
		return db.finds(GoodsType.class, sql);
	}
	
	@Override
	public List<GoodsType> finds() {
		DBHelper db = new DBHelper();
		String sql = "select tno, tname, status from goodstype where status != 0";
		return db.finds(GoodsType.class, sql);
	}
}
