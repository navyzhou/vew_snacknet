package com.yc.snacknet.biz;

import java.util.List;
import java.util.Map;

public interface IOrderInfoBiz {
	/**
	 * 根据会员编号，获取历史订单
	 * @param mno
	 * @return
	 */
	public List<Map<String, Object>> finds(String mno);
	
	/**
	 * 添加订单
	 * @param cnos
	 * @param totalPrice
	 * @param ano
	 * @return
	 */
	public String add(String cnos, double totalPrice, String ano);
	
	/**
	 * 修改订单状态
	 * @param ono
	 * @param status
	 * @return
	 */
	public int update(String ono, Integer status);
}
