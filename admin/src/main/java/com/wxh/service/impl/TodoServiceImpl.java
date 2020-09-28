package com.wxh.service.impl;

import com.wxh.model.Todo;
import com.wxh.mapper.TodoMapper;
import com.wxh.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxh
 * @since 2020-09-28
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

}
