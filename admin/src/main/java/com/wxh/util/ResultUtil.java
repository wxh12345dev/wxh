package com.wxh.util;

public class ResultUtil {
	public static Result getData(Object data) {
		return new Result(200, null, data);
	}
	public static Result getInfo(String info) {
		return new Result(200, info, null);
	}
	public static Result getObject(String info,Object data) {
		return new Result(200, info, data);
	}
}
