package com.wxh.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.wxh.model.Dairy;
import com.wxh.service.IDairyService;

@RestController
public class KaptchaController {
	@Autowired
	private IDairyService dairyService;
	@GetMapping("/test")
	public String getKaptchaImage(HttpServletResponse response, HttpSession session) throws Exception {
		return "";
	}
}