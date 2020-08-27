package com.yc.snacknet.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.snacknet.biz.IOrderInfoBiz;
import com.yc.snacknet.dao.IOrderInfoDao;
import com.yc.snacknet.dao.impl.OrderInfoDaoImpl;
import com.yc.snacknet.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年8月25日
 * Email haijunzhou@hnit.edu.cn
 */
public class OrderInfoBizImpl implements IOrderInfoBiz{

	@Override
	public List<Map<String, Object>> finds(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		List<Map<String, String>> list = orderInfoDao.finds(mno);
		if (list == null || list.isEmpty()) {
			return Collections.emptyList();
		}
		
		// 处理好之后的订单数据
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		String preOno = list.get(0).get("ono"); // 获取第一个订单的订单编号
		String curOno = preOno;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ono", preOno); // 订单编号
		map.put("odate", list.get(0).get("odate")); // 订单日期
		map.put("totalPrice", list.get(0).get("totalprice")); // 订单总价
		map.put("status", list.get(0).get("status")); // 订单的状态
		
		// 这个订单下面的所有商品信息
		List<Map<String, String>> singleOrder = new ArrayList<Map<String, String>>(); // 存单个订单
		Map<String, String> singleProduct = null; // 存单个商品
		
		for (Map<String ,String> rt : list) {
			curOno = rt.get("ono"); // 每次循环取出当前数据的订单号
			
			if (!preOno.equals(curOno)) { // 意味着这是一个新的订单了，说明上一个订单已经循环完成
				preOno = curOno;
				map.put("goods", singleOrder); // 将这个订单的商品存到map中
				
				// 说明这个订单完成
				result.add(map);
				
				// 初始化数据，准备接收下一个订单商品数据
				singleOrder = new ArrayList<Map<String, String>>();
				
				// 准备存放下一个订单的信息
				map = new HashMap<String, Object>();
				map.put("ono", preOno); // 订单编号
				map.put("odate", rt.get("odate")); // 订单日期
				map.put("totalPrice", rt.get("totalprice")); // 订单总价
				map.put("status", rt.get("status")); // 订单的状态
			}
			
			// 取这个订单的商品信息
			singleProduct = new HashMap<String, String>(); // 存单个商品
			singleProduct.put("gno", rt.get("gno"));
			singleProduct.put("nums", rt.get("nums"));
			singleProduct.put("price", rt.get("price"));
			singleProduct.put("gname", rt.get("gname"));
			singleProduct.put("pics", rt.get("pics"));
			singleProduct.put("weight", rt.get("weight"));
			singleProduct.put("unit", rt.get("unit"));
			singleOrder.add(singleProduct); // 说明当前这个订单的商品已经循环完成
		}
		map.put("goods", singleOrder); // 将这个订单的商品存到map中
		// 说明这个订单完成
		result.add(map);
		
		return result;
	}

	@Override
	public String add(String cnos, double totalPrice, String ano) {
		if (StringUtil.checkNull(cnos, ano)) {
			return "";
		}
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.add(cnos, totalPrice, ano);
	}

	@Override
	public int update(String ono, Integer status) {
		if (StringUtil.checkNull(ono)) {
			return -1;
		}
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.update(ono, status);
	}
}
