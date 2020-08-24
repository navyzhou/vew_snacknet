package com.yc.snacknet.biz;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.CartInfo;

public interface ICartInfoBiz {
	public List<CartInfo> finds(String mno);
	
	public List<Map<String, String>> info(String mno);
	
	public List<CartInfo> findByCno(String cnos);
	
	public int updateNum(String cno, int num);
	
	public int delete(String cnos);
	
	public int add(CartInfo cf);
}
