package com.wxh.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxh.model.User;
import com.wxh.service.IUserService;
import com.wxh.util.HttpUtils;
import com.wxh.util.InvalidException;
import com.wxh.util.Result;
import com.wxh.util.ResultUtil;
import com.wxh.util.StringUtil;

/**
 * <p>
 * 前端控制器 wxebeeea286b852dc1 63e415a83351e61b78ab56a014813f07
 * </p>
 *
 * @author wxh
 * @since 2019-09-18
 */
@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/login")
	public Result login(String code, String username, String password, HttpSession session) throws IOException {
		if (!StringUtil.isEmpty(code)) {
			session.setAttribute("userId", checkUserInfo(code));
		} else {
			if (StringUtil.isEmpty(username)) {
				throw new InvalidException("用户名不能为空！");
			}
			if (StringUtil.isEmpty(password)) {
				throw new InvalidException("密码不能为空！");
			}
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			QueryWrapper<User> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("username", username);
			queryWrapper.eq("password", password);
			User user = userService.getOne(queryWrapper);
			if (user == null) {
				throw new InvalidException("用户名或密码错误！");
			}
			session.setAttribute("userId", user.getId());
		}
		return ResultUtil.getInfo("登陆成功！");
	}

	private String checkUserInfo(String code) throws IOException {
		String result = HttpUtils.httpGet("https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=wxebeeea286b852dc1&secret=63e415a83351e61b78ab56a014813f07&grant_type=authorization_code&js_code="
				+ code);
		JSONObject object = JSON.parseObject(result);
		String openid = object.getString("openid");
		if (!"0".equals(object.getString("errcode")) || StringUtil.isEmpty(openid)) {
			// 失败了
			throw new InvalidException("登陆码校验失败！");
		}
		return openid;
	}

	@RequestMapping("/register")
	public Result register(String username, String password, String code) throws IOException {
		if (StringUtil.isEmpty(username)) {
			throw new InvalidException("用户名不能为空！");
		}
		if (StringUtil.isEmpty(password)) {
			throw new InvalidException("密码不能为空！");
		}
		if (StringUtil.isEmpty(code)) {
			throw new InvalidException("验证码不能为空！");
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		User user = userService.getOne(queryWrapper);
		if (user != null) {
			throw new InvalidException("用户名 " + username + " 已存在！");
		}
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setId(StringUtil.getUuid());
		user.setCreated(new Date());
		return ResultUtil.getInfo("注册成功！");
	}

	@RequestMapping("/bindUser")
	public Result bindUser(String username, String password, String code, HttpSession session) throws IOException {
		if (StringUtil.isEmpty(username)) {
			throw new InvalidException("用户名不能为空！");
		}
		if (StringUtil.isEmpty(password)) {
			throw new InvalidException("密码不能为空！");
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		queryWrapper.eq("username", username);
		queryWrapper.eq("password", password);
		User user = userService.getOne(queryWrapper);
		if (user == null) {
			throw new InvalidException("该用户信息尚未注册，请注册后再进行绑定！");
		}
		user.setCode(getUserId(session));
		userService.bindUser(user);
		return ResultUtil.getInfo("绑定成功！");
	}

	@RequestMapping("/getBindUser")
	public Result getBindUser(HttpSession session) throws IOException {
		String userId = getUserId(session);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("code", userId);
		return ResultUtil.getData(userService.getOne(queryWrapper));
	}

	private String getUserId(HttpSession session) {
		return (String) session.getAttribute("userId");
	}

}
