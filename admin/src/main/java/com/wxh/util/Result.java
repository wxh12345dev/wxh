package com.wxh.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private String info;
	private Object data;
	// 200-成功，412-自定义异常，500-系统繁忙，101-未登录
	private Integer code;

	public Result(Integer code, String info, Object data) {
		super();
		this.info = info;
		this.code = code;
		this.data = data;
	}

}
