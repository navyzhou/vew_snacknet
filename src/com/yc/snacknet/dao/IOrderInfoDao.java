package com.yc.snacknet.dao;

import java.util.List;
import java.util.Map;

public interface IOrderInfoDao {
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
	 * @return 返回订单编号
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
