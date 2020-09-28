package com.wxh.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆拦截器
 * @author Administrator
 *
 */
@Controller
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		if(servletPath.endsWith(".html")||servletPath.endsWith(".css")||servletPath.endsWith(".js")
				||servletPath.endsWith(".jpg")||servletPath.endsWith(".png")) {
			return true;
		}
		return true;
//		User user = (User) session.getAttribute("user-login");
//		if (user == null) {
//			response.setCharacterEncoding("UTF-8");
//			PrintWriter writer = response.getWriter();
//			writer.write(JSON.toJSONString(new Result(101,"尚未登录",null)));
//			writer.close();
//			return false;
//		} else {
//			return true;
//		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}
