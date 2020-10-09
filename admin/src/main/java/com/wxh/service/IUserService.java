package com.wxh.service;

import com.wxh.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxh
 * @since 2020-10-09
 */
public interface IUserService extends IService<User> {

	void bindUser(User user);

}
