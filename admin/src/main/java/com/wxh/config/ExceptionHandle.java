package com.wxh.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxh.util.InvalidException;
import com.wxh.util.Result;

@ControllerAdvice
public class ExceptionHandle {
	@ExceptionHandler(value = InvalidException.class)
    @ResponseBody
    public Result Handle(InvalidException e){
		e.printStackTrace();
        return new Result(412,e.getMessage(),null);
    }
}