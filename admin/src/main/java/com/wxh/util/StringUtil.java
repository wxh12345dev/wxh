package com.wxh.util;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class StringUtil {
	public static Map<String,Long> cache = new ConcurrentHashMap<>();
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static String getRandomCode() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int nextInt = random.nextInt(10);
			builder.append(nextInt);
		}
		return builder.toString();
	}
	public static boolean isEmpty(String sentence) {
		return sentence == null || sentence.trim().equals("");
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean isEmail(String str) {
		if(StringUtil.isEmpty(str)) {
			return false;
		}
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();

	}

	public static String clearHtml(String content, Integer length) {
		if (StringUtil.isEmpty(content)) {
			return content;
		}
		// 标签过滤
		Document document = Jsoup.parse(content);
		content = document.text();
		// 截取前30位
		if (content.length() > length) {
			content = content.substring(0, length);
		}
		return content + "...";
	}
	
	public static String getIpAddress(HttpServletRequest request) {  
		String ip = request.getHeader("x-forwarded-for");  
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Real-IP");  
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip; 
    } 
}
