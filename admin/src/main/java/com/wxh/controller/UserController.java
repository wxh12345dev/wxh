package com.wxh.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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
import com.wxh.util.Constants;
import com.wxh.util.EmailSender;
import com.wxh.util.HttpUtils;
import com.wxh.util.InvalidException;
import com.wxh.util.RedisUtil;
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
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/login")
	public Result login(String code, String username, String password, HttpSession session) throws IOException {
		if (!StringUtil.isEmpty(code)) {
			String userId = checkUserInfo(code);
			session.setAttribute(Constants.USER_SESSION_INFO, userId);
			return ResultUtil.getData(userId);
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
			session.setAttribute(Constants.USER_SESSION_INFO, user.getId());
			user.setPassword(null);
			return ResultUtil.getData(user);
		}
		
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
	public Result register(String username, String password, String code,HttpSession session) throws IOException {
		if (StringUtil.isEmpty(username)) {
			throw new InvalidException("用户名不能为空！");
		}
		if (StringUtil.isEmpty(password)) {
			throw new InvalidException("密码不能为空！");
		}
		if (StringUtil.isEmpty(code)) {
			throw new InvalidException("验证码不能为空！");
		}
//		String registerCode = getInfo(session, Constants.REGISTER_CODE);
//		if(!code.equals(registerCode)) {
//			throw new InvalidException("验证码有无！");
//		}
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
		//新增用户
		userService.save(user);
		return ResultUtil.getInfo("注册成功！");
	}

	@RequestMapping("/getCode")
	public Result getCode(String email,HttpServletRequest request) {
		if (!StringUtil.isEmail(email)) {
			throw new InvalidException("邮箱格式有误！");
		}
		String ipAddress = StringUtil.getIpAddress(request);
		Object object = redisUtil.get(ipAddress);
		if(object!=null) {
			throw new InvalidException("验证码发送间隔不能小于30秒");
		}
		try {
			String randomCode = StringUtil.getRandomCode();
			EmailSender.sendEmail(email, "小华备忘录注册验证码", "您好！您注册的验证码为"+randomCode+"，,有效期为30分钟，请及时使用！【小华备忘录】");
			redisUtil.set(ipAddress, new Date().getTime(),30);
			//有效期30分钟
//			redisUtil.set(email+Constas'snts.REGISTER_CODE, randomCode, 30*60);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidException("验证码发送失败！");
		}
		return ResultUtil.getInfo("验证码发送成功！");
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
		user.setCode(getInfo(session,Constants.USER_SESSION_INFO));
		userService.bindUser(user);
		return ResultUtil.getInfo("绑定成功！");
	}

	@RequestMapping("/getBindUser")
	public Result getBindUser(HttpSession session) throws IOException {
		String userId = getInfo(session,Constants.USER_SESSION_INFO);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("code", userId);
		return ResultUtil.getData(userService.getOne(queryWrapper));
	}

	private String getInfo(HttpSession session,String key) {
		return (String) session.getAttribute(key);
	}

}
