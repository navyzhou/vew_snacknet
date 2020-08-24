package com.yc.snacknet.dao;

import java.util.List;

import com.yc.snacknet.bean.GoodsInfo;

public interface IGoodsInfoDao {
	public int add(GoodsInfo gf);
	
	public int update(GoodsInfo gf);
	
	/**
	 * 查看商品详细
	 * @param gid
	 * @return
	 */
	public GoodsInfo findByGid(String gno);
	
	/**
	 * 
	 * @param gname
	 * @param tno
	 * @param start 库存的开始值
	 * @param end 库存的结束值
	 * @param page
	 * @param row
	 * @return
	 */
	public List<GoodsInfo> findByPage(String gname, String tno, String start, String end, int page, int row);
	
	public int total(String gname, String tno, String start, String end);
	
	/**
	 * 前台分页查询
	 * @param gname
	 * @param tno
	 * @param sprice
	 * @param eprice
	 * @param page
	 * @param row
	 * @param order
	 * @return
	 */
	public List<GoodsInfo> finds(String gname, String tno, String sprice, String eprice, int page, int row, String order);

	public int totals(String gname, String tno, String sprice, String eprice);
}


