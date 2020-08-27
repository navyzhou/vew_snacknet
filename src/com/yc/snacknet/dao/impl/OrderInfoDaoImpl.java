package com.yc.snacknet.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yc.snacknet.dao.DBHelper;
import com.yc.snacknet.dao.IOrderInfoDao;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年8月25日
 * Email haijunzhou@hnit.edu.cn
 */
public class OrderInfoDaoImpl implements IOrderInfoDao{
	@Override
	public List<Map<String, String>> finds(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select o.ono, date_format(odate, '%Y-%m-%d %H:%i') odate, oprice totalPrice, o.status, i.gno, nums, i.price, gname, pics, weight, unit"
				+ " from orderinfo o, orderiteminfo i, goodsinfo g, addrinfo a where o.ono=i.ono and i.gno=g.gno and a.ano=o.ano and a.mno=?"
				+ " order by odate desc";
		return db.gets(sql, mno);
	}

	@Override
	public int add(String cnos, double totalPrice, String ano) {
		DBHelper db = new DBHelper();
		List<String> sqls = new ArrayList<String>(); // 存放所有要执行的sql语句
		List<List<Object>> params = new ArrayList<List<Object>>(); // 每条sql语句对应的参数列表
		
		// 添加一条记录到订单表orderinfo
		String ono = UUID.randomUUID().toString().replace("-", "");
		String sql1 = "insert into orderinfo values(?, now(), ?, null, null, 1, ?, 0)";
		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, ono, ano, totalPrice);
		
		// 将这个条sql语句和参数列表添加到要执行的sql集合中
		sqls.add(sql1);
		params.add(param1);
		
		// 插入多条数据到订单详细表orderiteminfo -> 要添加的数据信息从购物车表中
		String sql2 = "insert into orderiteminfo select 0, ?, c.gno, c.nums, g.price, 1 from cartinfo c, goodsinfo g where c.gno=g.gno and cno in(";
		List<Object> param2 = new ArrayList<Object>();
		String[] temp = cnos.split(",");
		for (String cno : temp) {
			sql2 += "?,";  // cno in(?,?,?);
			param2.add(cno);	
		}
		sql2 = sql2.substring(0, sql2.lastIndexOf(",")) + ")";
		
		sqls.add(sql2);
		params.add(param2);
		
		// 修改商品的仓库量  goodsinfo -> 买了几个商品就要修改几个商品的库存 -> 买了几个只能从购物车表中获取
		String sql3 = null;
		List<Object> param3 = null;
		for (String cno : temp) {
			sql3 = "update goodsinfo set balance = balance - (select num from cartinfo where cno=?) where gno=(select gno from cartinfo where cno=?)";
			param3 = new ArrayList<Object>();
			Collections.addAll(param3, cno, cno);
			
			sqls.add(sql3);
			params.add(param3);
		}
		
		// 删除购物车表中以购买的商品  cartinfo
		String sql4 = "delete from cartinfo where cno in(";
		List<Object> param4 = new ArrayList<Object>();
		for (String cno : temp) {
			sql4 += "?,";  // cno in(?,?,?);
			param4.add(cno);	
		}
		sql4 = sql4.substring(0, sql4.lastIndexOf(",")) + ")";
		
		sqls.add(sql4);
		params.add(param4);
		
		return db.updates(sqls, params);
	}

}
