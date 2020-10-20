package com.wxh.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KaptchaController {
	@GetMapping("/test")
	public String getKaptchaImage(HttpServletResponse response, HttpSession session) throws Exception {
		return "";
	}
}