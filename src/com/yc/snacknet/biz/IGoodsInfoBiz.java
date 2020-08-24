package com.yc.snacknet.biz;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.GoodsInfo;

public interface IGoodsInfoBiz {
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
	public Map<String, Object> findByPage(String gname, String tno, String start, String end, int page, int row);
	
	public Map<String, Object> findByFirst(String gname, String tno, String sprice, String eprice, int page, int row, String order);
	
	public List<GoodsInfo> finds(String gname, String tno, String sprice, String eprice, int page, int row, String order);
}
