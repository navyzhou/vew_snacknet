package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.snacknet.bean.GoodsInfo;
import com.yc.snacknet.biz.IGoodsInfoBiz;
import com.yc.snacknet.biz.impl.GoodsInfoBizImpl;
import com.yc.snacknet.util.FileUploadUtil;

@WebServlet("/goods")
public class GoodsInfoController extends BasicController{
	private static final long serialVersionUID = 5110837791774506814L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch(op) {
		case "add": add(request, response); break;
		case "update": update(request, response); break;
		case "findByPage": findByPage(request, response); break;
		case "upload": upload(request, response); break;
		case "findByFirst": findByFirst(request, response); break;
		case "finds": finds(request, response); break;
		case "findByGno": findByGno(request, response); break;
		default: this.error(request, response); break;
		}
	}
	
	private void findByGno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gno = request.getParameter("gno");
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByGid(gno));
	}

	private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gname = request.getParameter("gname");
		String tno = request.getParameter("tno");
		String sprice = request.getParameter("sprice");
		String eprice = request.getParameter("eprice");
		String order = request.getParameter("order");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.finds(gname, tno, sprice, eprice, page, row, order));
	}

	private void findByFirst(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gname = request.getParameter("gname");
		String tno = request.getParameter("tno");
		String sprice = request.getParameter("sprice");
		String eprice = request.getParameter("eprice");
		String order = request.getParameter("order");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByFirst(gname, tno, sprice, eprice, page, row, order));
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileUploadUtil fuu = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, String> map =fuu.upload(pageContext);
			String path = map.get("upload");
			
			// 以下必须这样写
			result.put("fileName", path.substring(path.lastIndexOf("_")));
			result.put("url", "../../" + path);
			result.put("uploaded", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 必须以json格式返回这个结果
		this.send(response, result);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil fuu = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
			GoodsInfo gf = fuu.upload(GoodsInfo.class, pageContext);
			IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
			this.send(response, goodsInfoBiz.update(gf));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gname = request.getParameter("gname");
		String tno = request.getParameter("tno");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByPage(gname, tno, start, end, page, row));
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileUploadUtil fuu = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
			GoodsInfo gf = fuu.upload(GoodsInfo.class, pageContext);
			IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
			this.send(response, goodsInfoBiz.add(gf));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
