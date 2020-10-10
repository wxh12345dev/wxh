package com.wxh.config;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wxh.util.Result;

/**
 * 登陆拦截器
 * @author Administrator
 *
 */
@Controller
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		response.setHeader("JSESSIONID", session.getId());
		String servletPath = request.getServletPath();
		List<String> exc = new ArrayList<>();
		exc.add("/login");
		exc.add("/register");
		exc.add("/error");
		exc.add("/getCode");
		if(servletPath.endsWith(".html")||servletPath.endsWith(".css")||servletPath.endsWith(".js")
				||servletPath.endsWith(".jpg")||servletPath.endsWith(".png")) {
			return true;
		}
		if(exc.contains(servletPath)) {
			return true;
		}
		Object user = session.getAttribute("userInfo");
		if (user == null) {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
            response.setHeader("Access-Control-Max-Age", "36000");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
            response.setHeader("Access-Control-Allow-Credentials","true");
			PrintWriter writer = response.getWriter();
			writer.write(JSON.toJSONString(new Result(101,"尚未登录",null)));
			writer.close();
			return false;
		} else {
			return true;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		System.out.println(response);
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		System.out.println(response);
	}
}
