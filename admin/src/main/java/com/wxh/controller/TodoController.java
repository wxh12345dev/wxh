package com.wxh.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxh.model.Todo;
import com.wxh.service.ITodoService;
import com.wxh.util.InvalidException;
import com.wxh.util.ParamUtil;
import com.wxh.util.Result;
import com.wxh.util.ResultUtil;
import com.wxh.util.StringUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxh
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private ITodoService todoService;

	@RequestMapping("/edit")
	public Result edit(@RequestBody Todo todo) {
		if (StringUtils.isEmpty(todo.getContent())) {
			throw new InvalidException("请输入日记内容！");
		}
		String id = todo.getId();
		if (StringUtils.isEmpty(id)) {
			// 新增
			todo.setId(StringUtil.getUuid());
			todo.setCreated(new Date());
			todo.setFinish(0);
		}else if(todo.getFinish()!=null&&todo.getFinish()!=0&&todo.getFinish()!=1) {
			throw new InvalidException("参数有误！");
		}
		todoService.saveOrUpdate(todo);
		return ResultUtil.getInfo("操作成功");
	}

	@RequestMapping("/delete")
	public Result delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new InvalidException("请选择要数据！");
		}
		todoService.removeById(id);
		return ResultUtil.getInfo("操作成功");
	}

	@RequestMapping("/page")
	public Result page(@RequestBody Todo todo) throws Exception {
		QueryWrapper<Todo> queryWrapper = new ParamUtil<Todo>().getQueryWrapper(todo);
		queryWrapper.orderByDesc("created");
		IPage<Todo> result = todoService.page(new Page<Todo>(todo.getCurrentPage(), todo.getPageSize()),
				queryWrapper);
		return ResultUtil.getData(result);
	}
	
}
