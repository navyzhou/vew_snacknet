package com.yc.snacknet.dao;

import java.util.List;

import com.yc.snacknet.bean.GoodsType;

public interface IGoodsTypeDao {
	public int add(GoodsType type);
	
	public int update(GoodsType type);
	
	public List<GoodsType> findAll();
	
	public List<GoodsType> finds();
}
