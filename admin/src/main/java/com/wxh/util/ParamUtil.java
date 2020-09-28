package com.wxh.util;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class ParamUtil<T> {
	public QueryWrapper<T> getQueryWrapper(T t) throws Exception {
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(t);
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			MyQueryAnnotation annotation = field.getAnnotation(MyQueryAnnotation.class);
			if (annotation == null) {
				continue;
			}
			String name = StringUtil.isEmpty(annotation.field())?field.getName():annotation.field();
			switch (annotation.value()) {
			case "like":
				queryWrapper.like(name, value);
				break;
			case "eq":
				queryWrapper.eq(name, value);
				break;
			case "lt":
				queryWrapper.lt(name, value);
				break;
			case "gt":
				queryWrapper.gt(name, value);
				break;
			default:
				break;
				
			}
		}
		return queryWrapper;
	}
}
