package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.biz.IAddrInfoBiz;
import com.yc.snacknet.biz.impl.AddrInfoBizImpl;
import com.yc.snacknet.util.SessionKeys;

@WebServlet("/addr")
public class AddrInfoController extends BasicController {
	private static final long serialVersionUID = 8444802577258055605L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "findByMno": findByMno(request, response); break;
		default: error(request, response); break;
		}
	}

	private void findByMno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			this.send(response, 501, null);
			return;
		}
		
		IAddrInfoBiz addrInfoBiz = new AddrInfoBizImpl();
		MemberInfo mf = (MemberInfo) obj;
		this.send(response, 200, addrInfoBiz.findByMno(String.valueOf(mf.getMno())));
	}
}
