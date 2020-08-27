package com.yc.snacknet.biz.impl;

import java.util.Collections;
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
	public List<Map<String, String>> finds(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.finds(mno);
	}

	@Override
	public int add(String cnos, double totalPrice, String ano) {
		if (StringUtil.checkNull(cnos, ano)) {
			return -1;
		}
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.add(cnos, totalPrice, ano);
	}
}
