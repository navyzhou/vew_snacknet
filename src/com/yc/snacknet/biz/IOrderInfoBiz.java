package com.yc.snacknet.biz;

import java.util.List;
import java.util.Map;

public interface IOrderInfoBiz {
	/**
	 * 根据会员编号，获取历史订单
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> finds(String mno);
	
	/**
	 * 添加订单
	 * @param cnos
	 * @param totalPrice
	 * @param ano
	 * @return
	 */
	public int add(String cnos, double totalPrice, String ano);
}
