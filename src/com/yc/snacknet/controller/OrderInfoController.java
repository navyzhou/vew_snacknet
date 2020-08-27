package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.biz.IOrderInfoBiz;
import com.yc.snacknet.biz.impl.OrderInfoBizImpl;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/order")
public class OrderInfoController extends BasicController{
	private static final long serialVersionUID = -4838723014769092225L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		switch (op) {
		case "add": add(request, response); break;
		case "find" : find(request, response); break;
		default: error(request, response); break;
		}
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			this.send(response, 501, null); // 说明未登录
			return;
		}
		MemberInfo mf = (MemberInfo) obj;
		IOrderInfoBiz orderInfoBiz = new OrderInfoBizImpl();
		
		this.send(response, 200, orderInfoBiz.finds(String.valueOf(mf.getMno())));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cnos = request.getParameter("cnos");
		Double totalPrice = Double.valueOf(request.getParameter("totalPrice"));
		String ano = request.getParameter("ano");

		IOrderInfoBiz orderInfoBiz = new OrderInfoBizImpl();
		String result = orderInfoBiz.add(cnos, totalPrice, ano);
		if (!StringUtil.checkNull(result)) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, null);
	}

}
