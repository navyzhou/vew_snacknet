package com.yc.snacknet.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

/**
 * 文件上传的工具类
 * company 源辰信息
 * @author navy
 * @date 2020年8月8日
 * Email haijunzhou@hnit.edu.cn
 */
public class FileUploadUtil {
	public static String PATH = "images"; // 默认上传路径
	private static final String ALLOWEDLIST = "gif,jpg,png,jpeg,doc,docx,xls,xlsx,txt,zip,rar,mp3,mp4"; // 允许上传的文件后缀
	private static final int MAXFILESIZE = 10 * 1024 * 1024; // 上传的单个文件的最大大小
	private static final int TOTALMAXSIZE = 100 * 1024 * 1024; // 每次上传的文件总大小


	/**
	 * 
	 * @param pageContext
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> upload(PageContext pageContext) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		// 实例化上传组件
		SmartUpload su = new SmartUpload();

		// 初始化上传组件
		su.initialize(pageContext);

		// 设置上传的参数
		su.setMaxFileSize(MAXFILESIZE);
		su.setAllowedFilesList(ALLOWEDLIST);
		// su.setDeniedFilesList(s); 不允许上传的文件后缀
		su.setTotalMaxFileSize(TOTALMAXSIZE);
		su.setCharset("utf-8");
		su.upload(); // 开始上传

		// 获取非文件域参数
		Request req = su.getRequest(); // 注意：此时的Request是jspsmrtupload中的而不是servlet中的

		// 获取所有的文本框的name属性的属性值
		Enumeration<String> enums =  req.getParameterNames();

		String name = null;
		while (enums.hasMoreElements()) {
			name = enums.nextElement();
			map.put(name, req.getParameter(name));
		}

		// 处理要上传的文件
		Files files = su.getFiles(); // 获取所有要上传的文件
		if (files == null || files.getCount() <= 0 || files.getSize() <= 0 || files.getFile(0).getSize() <= 0) {
			return map; // 则我们认为没有文件要处理
		}

		Collection<File> fls = files.getCollection();

		// 获取Tomcat在服务器中的绝对路径
		String basePath = pageContext.getServletContext().getRealPath("/");

		String fileName = null; // 上传后新的文件名
		String fieldName = null; // 上次这个文件的文件域的name属性的属性值
		String filePath = null; // 文件的保存路径
		String pathStr = "";
		String temp = null;
		for (File fl : fls) {
			if (!fl.isMissing()) { // 说明没有丢失数据
				// 将这个文件数据存到服务器

				// 为了避免文件名相同是出现的文件覆盖，所有我们要对上传的文件重命名
				temp = fl.getFieldName();
				if (StringUtil.checkNull(fieldName)) {
					fieldName = temp;
				} else {
					if ( !temp.equals(fieldName)) { // 说你此时第一个文件域已经读完
						// 将这个文件域中的内容存到map中
						map.put(fieldName, pathStr);
						pathStr = ""; // 初始化参数，准备存放下一个文件域中的内容
						fieldName = temp;
					}
				}

				fieldName = fl.getFieldName();
				fileName = new Date().getTime() + "_" + fl.getFileName();

				// 将这个文件存到服务器的指定目录中
				filePath = PATH + "/" + fileName;  // images/4325435435435_1.gif

				// 将文件存到服务器
				fl.saveAs(basePath + filePath, SmartUpload.SAVE_PHYSICAL);

				if (StringUtil.checkNull(pathStr)) {
					pathStr = filePath;
				} else {
					pathStr += "," + filePath;
				}
			}
		}
		map.put(fieldName, pathStr);
		return map;
	}

	@SuppressWarnings("unchecked")
	public <T> T upload(Class<T> cls, PageContext pageContext) throws Exception {
		T t = cls.newInstance(); // 实例化对象

		// 实例化上传组件
		SmartUpload su = new SmartUpload();

		// 初始化上传组件
		su.initialize(pageContext);

		// 设置上传的参数
		su.setMaxFileSize(MAXFILESIZE);
		su.setAllowedFilesList(ALLOWEDLIST);
		// su.setDeniedFilesList(s); 不允许上传的文件后缀
		su.setTotalMaxFileSize(TOTALMAXSIZE);
		su.setCharset("utf-8");
		su.upload(); // 开始上传

		// 获取非文件域参数
		Request request = su.getRequest(); // 注意：此时的Request是jspsmrtupload中的而不是servlet中的

		// 获取所有的文本框的name属性的属性值
		Enumeration<String> enums =  request.getParameterNames();

		// 获取指定类的setter方法
		Method[] methods = cls.getMethods();
		Map<String, Method> setters = new HashMap<String, Method>();

		String methodName = null;
		for (Method md : methods) {
			methodName = md.getName();
			if (methodName.startsWith("set")) {
				setters.put(methodName, md);
			}
		}

		String name = null;
		Class<?>[] types = null;
		Class<?> type = null;
		Method method = null;
		
		while (enums.hasMoreElements()) {
			name = enums.nextElement();
			methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
			
			method = setters.getOrDefault(methodName, null); // 根据方法名查找对应的方法
			if (method == null) { // 如果没有对应的方法，说明这个属性不需要注值，那么就不管
				continue;
			}
			
			types = method.getParameterTypes(); // 如果有，则获取这个方法的形参类型列表
			if (types == null || types.length <= 0) { // 如果没有形参，则也不能注值
				continue;
			}
			
			type = types[0]; // 如果有则取第一个形参的类型
			
			if (Integer.TYPE == type) {
				method.invoke(t, Integer.parseInt(request.getParameter(name)));
			} else if(Integer.class == type) { // 说明要的是一个整形值或一个整形对象
				method.invoke(t, Integer.valueOf(request.getParameter(name)));
			} else if (Float.TYPE == type || Float.class == type) {
				method.invoke(t, Float.parseFloat(request.getParameter(name)));
			} else if (Double.TYPE == type || Double.class == type) {
				method.invoke(t,Double.parseDouble(request.getParameter(name)));
			} else {
				method.invoke(t, request.getParameter(name));
			}
		}

		// 处理要上传的文件
		Files files = su.getFiles(); // 获取所有要上传的文件
		if (files == null || files.getCount() <= 0 || files.getSize() <= 0 || files.getFile(0).getSize() <= 0) {
			return t; // 则我们认为没有文件要处理
		}

		Collection<File> fls = files.getCollection();

		// 获取Tomcat在服务器中的绝对路径
		String basePath = pageContext.getServletContext().getRealPath("/");

		String fileName = null; // 上传后新的文件名
		String fieldName = null; // 上次这个文件的文件域的name属性的属性值
		String filePath = null; // 文件的保存路径
		String pathStr = "";
		String temp = null;
		
		for (File fl : fls) {
			if (!fl.isMissing()) { // 说明没有丢失数据
				// 将这个文件数据存到服务器

				// 为了避免文件名相同是出现的文件覆盖，所有我们要对上传的文件重命名
				temp = fl.getFieldName();
				if (StringUtil.checkNull(fieldName)) {
					fieldName = temp;
				} else {
					if ( !temp.equals(fieldName)) { // 说你此时第一个文件域已经读完
						methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						
						method = setters.getOrDefault(methodName, null); // 根据方法名查找对应的方法
						if (method == null) { // 如果没有对应的方法，说明这个属性不需要注值，那么就不管
							continue;
						}
						
						types = method.getParameterTypes(); // 如果有，则获取这个方法的形参类型列表
						if (types == null || types.length <= 0) { // 如果没有形参，则也不能注值
							continue;
						}
						
						method.invoke(t, pathStr);
						
						// 将这个文件域中的内容存到map中
						pathStr = ""; // 初始化参数，准备存放下一个文件域中的内容
						fieldName = temp;
					}
				}

				fieldName = fl.getFieldName();
				fileName = new Date().getTime() + "_" + fl.getFileName();

				// 将这个文件存到服务器的指定目录中
				filePath = PATH + "/" + fileName;  // images/4325435435435_1.gif

				// 将文件存到服务器
				fl.saveAs(basePath + filePath, SmartUpload.SAVE_PHYSICAL);

				if (StringUtil.checkNull(pathStr)) {
					pathStr = filePath;
				} else {
					pathStr += "," + filePath;
				}
			}
		}
		methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		
		method = setters.getOrDefault(methodName, null); // 根据方法名查找对应的方法
		if (method == null) { // 如果没有对应的方法，说明这个属性不需要注值，那么就不管
			return t;
		}
		
		types = method.getParameterTypes(); // 如果有，则获取这个方法的形参类型列表
		if (types == null || types.length <= 0) { // 如果没有形参，则也不能注值
			return t;
		}
		
		method.invoke(t, pathStr);
		return t;
	}
}
