package com.wxh.mapper;

import com.wxh.model.User;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxh
 * @since 2020-10-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
