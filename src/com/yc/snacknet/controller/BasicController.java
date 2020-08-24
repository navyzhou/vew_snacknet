package com.yc.snacknet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BasicController extends HttpServlet{
	private static final long serialVersionUID = 365556827237541585L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	/**
	 * 返回一个状态码的方法
	 * @param response
	 * @param status 要返回的状态码
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, int status) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(status);
		out.flush();
	}
	
	/**
	 * 以json格式回送一个对象
	 * @param response
	 * @param obj 要返回的对象
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, Object obj) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		out.print(gson.toJson(obj));
		out.flush();
	}
	
	/**
	 * 以json格式回送数据的方法，返回一个状态码code和一个提示信息msg
	 * @param response
	 * @param code 状态码
	 * @param msg 要返回值的字符串信息
	 * @throws IOException 
	 */
	protected void send(HttpServletResponse response, int code, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		out.print(gson.toJson(map));
		out.flush();
	}
	
	/**
	 * 以json格式回送数据的方法，返回一个状态码code和一些数据
	 * @param response
	 * @param code 状态码
	 * @param data 要返回的数据信息
	 * @throws IOException 
	 */
	protected void send(HttpServletResponse response, int code, Object data) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		out.print(gson.toJson(map));
		out.flush();
	}
	
	protected void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		out.print("<script>alert('该请求不支持...');location.href='" + basePath + "index.html';</script>");
		out.flush();
		out.close();
	}
}
