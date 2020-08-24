package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.biz.IMemberInfoBiz;
import com.yc.snacknet.biz.impl.MemberInfoBizImpl;
import com.yc.snacknet.util.RequestParamUtil;
import com.yc.snacknet.util.SessionKeys;

@WebServlet("/member")
public class MemberInfoController extends BasicController {
	private static final long serialVersionUID = 1541343297567029693L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "login": login(request, response);	break;
		case "info": info(request, response);	break;
		default: error(request, response); break;
		}
	}

	private void info(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			this.send(response, 500, null); // 说明没有登录
			return;
		}
		this.send(response, 200, obj);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberInfo mf = RequestParamUtil.getParams(MemberInfo.class, request);
		
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("vcode");
		if (obj == null) {
			this.send(response, 501, null); // 说明验证码错误
			return;
		}
		
		if (!String.valueOf(obj).equalsIgnoreCase(mf.getRealName())) {
			this.send(response, 501, null); // 说明验证码错误
			return;
		}
		
		IMemberInfoBiz memberInfoBiz = new MemberInfoBizImpl();
		MemberInfo member = memberInfoBiz.login(mf);
		if (member == null) {
			this.send(response, 500, null); // 说明账号或密码错误
			return;
		}
		
		// 存到session
		session.setAttribute(SessionKeys.CURRENTMEMBERACCOUNT, member);
		this.send(response, 200, null);
	}
}
