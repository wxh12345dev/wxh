package com.wxh.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxh.model.Todo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxh
 * @since 2020-09-28
 */
@Mapper
public interface TodoMapper extends BaseMapper<Todo> {

}
