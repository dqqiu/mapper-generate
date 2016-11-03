package org.spirit.mapper.generate.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PublicUtils {
	
	public static void main(String[] args) {
		System.out.println("本机的ip=" + PublicUtils.getIp());
	}
	
	public static String getPorjectPath(){
		String nowpath = "";
		nowpath=System.getProperty("user.dir")+"/";
		
		return nowpath;
	}
	
	/**
	 *  @Description	: qiudequan 获取本机ip
	 *  @param          : @return
	 *  @return 		: String
	 *  @Creation Date  : 2016年11月3日 下午3:53:42 
	 *  @Author         : qiudequan
	 */
	public static String getIp(){
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
			//System.out.println("本机的ip=" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ip;
	}
	
}