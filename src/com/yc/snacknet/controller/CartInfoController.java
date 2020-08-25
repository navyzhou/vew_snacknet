package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.bean.CartInfo;
import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.biz.ICartInfoBiz;
import com.yc.snacknet.biz.impl.CartInfoBizImpl;
import com.yc.snacknet.util.RequestParamUtil;
import com.yc.snacknet.util.SessionKeys;

@WebServlet("/cart")
public class CartInfoController extends BasicController{
	private static final long serialVersionUID = 2741640012103775063L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "info": info(request, response); break; // 获取当前登录用户的购物车列表
		case "update": update(request, response); break; // 更新数量的方法
		case "add" : add(request, response); break; // 添加
		case "find" : find(request, response); break; // 查询用户的购物车信息
		case "delete" : delete(request, response); break; // 查询用户的购物车信息
		case "findByCnos" : findByCnos(request, response); break; // 根据购物车编号查询商品信息
		default:
			break;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cnos = request.getParameter("cno");
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		int result = cartInfoBiz.delete(cnos);
		if (result > 0) {
			this.send(response, 200, null);
			return;
		}
		this.send(response, 500, null);
	}

	private void findByCnos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cnos = request.getParameter("cnos");
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		this.send(response, cartInfoBiz.findByCno(cnos));
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			this.send(response, 501, null); // 说明未登录
			return;
		}
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		MemberInfo mf = (MemberInfo) obj;
		this.send(response, 200, cartInfoBiz.finds(String.valueOf(mf.getMno())));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CartInfo cf = RequestParamUtil.getParams(CartInfo.class, request);
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		// 生成购物车编号
		String cno = UUID.randomUUID().toString().replace("-", "");
		cf.setCno(cno);
		
		int result = cartInfoBiz.add(cf);
		if (result > 0) {
			this.send(response, 200, cno);
			return;
		}
		this.send(response, 500, null);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cno = request.getParameter("cno");
		int num = Integer.parseInt(request.getParameter("num"));
		
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		// 生成购物车编号
		
		int result = cartInfoBiz.updateNum(cno, num);
		if (result > 0) {
			this.send(response, 200, null);
			return;
		}
		this.send(response, 500, null);
	}

	private void info(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			this.send(response, 501, null); // 说明未登录
			return;
		}
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		MemberInfo mf = (MemberInfo) obj;
		this.send(response, 200, cartInfoBiz.info(String.valueOf(mf.getMno())));
	}
}
