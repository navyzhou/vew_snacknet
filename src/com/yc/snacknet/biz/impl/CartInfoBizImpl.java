package com.yc.snacknet.biz.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.CartInfo;
import com.yc.snacknet.biz.ICartInfoBiz;
import com.yc.snacknet.dao.ICartInfoDao;
import com.yc.snacknet.dao.impl.CartInfoDaoImpl;
import com.yc.snacknet.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年8月23日
 * Email haijunzhou@hnit.edu.cn
 */
public class CartInfoBizImpl implements ICartInfoBiz{

	@Override
	public List<CartInfo> finds(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.finds(mno);
	}

	@Override
	public List<Map<String, String>> info(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.info(mno);
	}

	@Override
	public List<CartInfo> findByCno(String cnos) {
		if (StringUtil.checkNull(cnos)) {
			return Collections.emptyList();
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.findByCno(cnos.split(","));
	}

	@Override
	public int updateNum(String cno, int num) {
		if (StringUtil.checkNull(cno)) {
			return -1;
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.updateNum(cno, num);
	}

	@Override
	public int delete(String cnos) {
		if (StringUtil.checkNull(cnos)) {
			return -1;
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		if (cnos.contains(",")) {
			return cartInfoDao.delete(cnos.split(","));
		}
		return cartInfoDao.delete(cnos);
	}

	@Override
	public int add(CartInfo cf) {
		if (StringUtil.checkNull(cf.getCno())) {
			return -1;
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.add(cf);
	}

}
