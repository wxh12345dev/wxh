package com.wxh.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wxh.util.QiniuUpload;
import com.wxh.util.Result;
import com.wxh.util.ResultUtil;
import com.wxh.util.StringUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wxh
 * @since 2019-09-18
 */
@RestController
public class UploadController {
	@Autowired
	private QiniuUpload qiniuUpload;

	@RequestMapping("/upload")
	public Result uplaod(MultipartFile file,String oriName) throws IOException {
		String fileName = file.getOriginalFilename();
		if(!"1".equals(oriName)) {
			fileName = StringUtil.getUuid() + "_" + fileName;
			fileName = fileName.replace(".png", ".jpg");
		}
		try(InputStream inputStream = file.getInputStream()){
			qiniuUpload.upload(inputStream, fileName);
        }
		return ResultUtil.getData(fileName);
	}
	
	
	

}
