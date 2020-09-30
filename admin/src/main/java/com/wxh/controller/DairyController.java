package com.wxh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxh.model.Dairy;
import com.wxh.service.IDairyService;
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
@RequestMapping("/dairy")
public class DairyController {
	
	@Autowired
	private IDairyService dairyService;

	@RequestMapping("/edit")
	public Result edit(@RequestBody Dairy dairy) {
		if (StringUtils.isEmpty(dairy.getTitle())) {
			throw new InvalidException("请输入日记标题！");
		}
		if (StringUtils.isEmpty(dairy.getContent())) {
			throw new InvalidException("请输入日记内容！");
		}
		String id = dairy.getId();
		if (StringUtils.isEmpty(id)) {
			// 新增
			dairy.setId(StringUtil.getUuid());
			dairy.setCreated(new Date());
		}
		dairyService.saveOrUpdate(dairy);
		return ResultUtil.getInfo("操作成功");
	}

	@RequestMapping("/delete")
	public Result delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new InvalidException("请选择要删除数据！");
		}
		dairyService.removeById(id);
		return ResultUtil.getInfo("操作成功");
	}

	@RequestMapping("/get")
	public Result get(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new InvalidException("请选择要查看的数据！");
		}
		Dairy dairy = dairyService.getById(id);
		if (dairy==null) {
			throw new InvalidException("该数据不存在，请返回上级菜单重试！");
		}
		return ResultUtil.getData(dairy);
	}
	
	@RequestMapping("/page")
	public Result page(@RequestBody Dairy dairy) throws Exception {
		QueryWrapper<Dairy> queryWrapper = new ParamUtil<Dairy>().getQueryWrapper(dairy);
		queryWrapper.orderByDesc("created");
		IPage<Dairy> result = dairyService.page(new Page<Dairy>(dairy.getCurrentPage(), dairy.getPageSize()),
				queryWrapper);
		List<Dairy> records = result.getRecords();
		for (Dairy dairy2 : records) {
			String url = dairy2.getUrl();
			if(!StringUtil.isEmpty(url)) {
				dairy2.setUrlFirst(url.split(";")[0]);
			}
		}
		return ResultUtil.getData(result);
	}
	
}
