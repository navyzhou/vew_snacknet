package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.biz.IAdminInfoBiz;
import com.yc.snacknet.biz.impl.AdminInfoBizImpl;
import com.yc.snacknet.util.SessionKeys;

@WebServlet("/admin")
public class AdminInfoController extends BasicController{
	private static final long serialVersionUID = -3517058055121493619L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("login".equals(op)) { // 登陆操作
			login(request, response);
		} else if ("info".equals(op)) { // 获取登录用户信息的方法
			info(request, response);
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String aname = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		AdminInfo adminInfo = adminInfoBiz.login(aname, pwd);
		
		if (adminInfo == null) {
			this.send(response, 500, null);
			return;
		}
		
		// 如果登录成功，需要将此登录用户信息存到session
		request.getSession().setAttribute(SessionKeys.CURRENTBACKLOGINACCOUNT, adminInfo);
		this.send(response, 200, null);
	}

	private void info(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SessionKeys.CURRENTBACKLOGINACCOUNT);
		if (obj == null) { // 说明没有登录
			this.send(response, 500, null);
			return;
		}
		this.send(response, 200, obj);
	}
}
