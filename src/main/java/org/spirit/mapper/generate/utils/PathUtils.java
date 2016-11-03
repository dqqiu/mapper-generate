package org.spirit.mapper.generate.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.utils.PathUtils.java
 * @Description   : qiudequan 路径工具类
 * @Author        : qiudequan
 * @Creation Date : 2016年11月3日 下午3:50:39 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月3日        create
 */
public class PathUtils {

	/**
	 *  @Description	: qiudequan 获取classpath，退出target/classes目录
	 *  @param          : @return
	 *  @return 		: String
	 *  @Creation Date  : 2016年11月3日 下午3:52:07 
	 *  @Author         : qiudequan
	 */
	public static String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
	
	/**
	 *  @Description	: qiudequan 获取classpath
	 *  @param          : @return
	 *  @return 		: String
	 *  @Creation Date  : 2016年11月3日 下午3:52:47 
	 *  @Author         : qiudequan
	 */
	public static String getClassResources(){
		String path =  (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
	
	public static String pathAddress() {
		String strResult = "";

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		StringBuffer strBuf = new StringBuffer();

		strBuf.append(request.getScheme() + "://");
		strBuf.append(request.getServerName() + ":");
		strBuf.append(request.getServerPort() + "");

		strBuf.append(request.getContextPath() + "/");

		strResult = strBuf.toString();

		return strResult;
	}
	
	
}
