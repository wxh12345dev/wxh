package com.wxh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxh.mapper.UserMapper;
import com.wxh.model.User;
import com.wxh.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxh
 * @since 2020-10-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public void bindUser(User user) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("code", user.getCode());
		User entity = new User();
		entity.setCode("");
		//清除原来绑定的信息
		userMapper.update(entity , updateWrapper);
		//绑定当前账号
		userMapper.updateById(user);
	}

}
