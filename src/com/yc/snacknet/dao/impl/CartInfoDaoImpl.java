package com.yc.snacknet.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.CartInfo;
import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.ICartInfoDao;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年8月23日
 * Email haijunzhou@hnit.edu.cn
 */
public class CartInfoDaoImpl implements ICartInfoDao {

	@Override
	public List<CartInfo> finds(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select cno, c.gno, num, price, pics, gname, unit, weight from goodsinfo g, cartinfo c where c.gno=g.gno and mno=?";
		return db.finds(CartInfo.class, sql, mno);
	}

	@Override
	public List<Map<String, String>> info(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select cno, gno from cartinfo where mno=?";
		return db.gets(sql, mno);
	}

	@Override
	public int updateNum(String cno, int num) {
		DBHelper db = new DBHelper();
		String sql = "update cartinfo set num = num + ? where cno = ?";
		return db.update(sql, num, cno);
	}

	@Override
	public int add(CartInfo cf) { 
		DBHelper db = new DBHelper();
		String sql = "insert into cartinfo values(?, ?, ?, ?)";
		return db.update(sql, cf.getCno(), cf.getMno(), cf.getGno(), cf.getNum());
	}

	@Override
	public int delete(String cno) {
		DBHelper db = new DBHelper();
		String sql = "delete from cartinfo where cno=?";
		return db.update(sql, cno);
	}

	@Override
	public int delete(String[] cnos) {
		DBHelper db = new DBHelper();
		String sql = "delete from cartinfo where cno in(";
		List<Object> params = new ArrayList<Object>();
		for (String cno : cnos) {
			sql += "?,";
			params.add(cno);
		}
		sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
		return db.update(sql, params);
	}

	@Override
	public List<CartInfo> findByCno(String[] cnos) {
		DBHelper db = new DBHelper();
		String sql = "select cno, c.gno, num, price, pics, gname, unit, weight from goodsinfo g, cartinfo c where c.gno=g.gno and cno in(";
		List<Object> params = new ArrayList<Object>();
		for (String cno : cnos) {
			sql += "?,";
			params.add(cno);
		}
		sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
		return db.finds(CartInfo.class, sql, params);
	}
}
