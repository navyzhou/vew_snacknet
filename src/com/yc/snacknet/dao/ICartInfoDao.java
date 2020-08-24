package com.yc.snacknet.dao;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.CartInfo;

public interface ICartInfoDao {
	public List<CartInfo> finds(String mno);
	
	/**
	 * 这个只查当前用不的购物车编号和商品编号 
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> info(String mno);
	
	public int updateNum(String cno, int num);
	
	public int add(CartInfo cf);
	
	public int delete(String cno);
	
	public int delete(String[] cnos);
	
	public List<CartInfo> findByCno(String[] cnos);
}
