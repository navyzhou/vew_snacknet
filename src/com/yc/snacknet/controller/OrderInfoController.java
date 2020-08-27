package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class OrderInfoController extends BasicController{
	private static final long serialVersionUID = -4838723014769092225L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "add": add(request, response); break;
		default: error(request, response); break;
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
