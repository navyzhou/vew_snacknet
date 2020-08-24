package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.biz.IGoodsTypeBiz;
import com.yc.snacknet.biz.impl.GoodsTypeBizImpl;
import com.yc.snacknet.util.RequestParamUtil;

@WebServlet("/type")
public class GoodsTypeController extends BasicController{
	private static final long serialVersionUID = 5110837791774506814L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch(op) {
		case "add": add(request, response); break;
		case "update": update(request, response); break;
		case "findAll": findAll(request, response); break;
		case "finds": finds(request, response); break;
		default: this.error(request, response); break;
		}
	}

	private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IGoodsTypeBiz typeBiz = new GoodsTypeBizImpl();
		this.send(response, typeBiz.finds());
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IGoodsTypeBiz typeBiz = new GoodsTypeBizImpl();
		this.send(response, typeBiz.findAll());
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsType type = RequestParamUtil.getParams(GoodsType.class, request);
		
		IGoodsTypeBiz typeBiz = new GoodsTypeBizImpl();
		this.send(response, typeBiz.update(type));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsType type = RequestParamUtil.getParams(GoodsType.class, request);
		IGoodsTypeBiz typeBiz = new GoodsTypeBizImpl();
		this.send(response, typeBiz.add(type));
	}
}
