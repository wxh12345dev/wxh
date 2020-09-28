package com.wxh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.qiniu.cdn.CdnManager;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.BucketManager.FileListIterator;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

@Component
public class QiniuUpload {
	// 构造一个带指定 Region 对象的配置类  
	Configuration cfg = new Configuration(Zone.zone2());
	// ...其他参数参考类注释
	UploadManager uploadManager = new UploadManager(cfg);
	// ...生成上传凭证，然后准备上传
	String accessKey = "yE6HBw0ExlMywztKbg1utFWEdm3VCkhRCJIPY1Nm";
	String secretKey = "AFCP6ZexKtMbYAvaH_Yn5PvXvfMuOJG1kC117hzS";
	String bucket = "yqlqny";

	public void upload(InputStream inputStream, String name) throws QiniuException {
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = name;
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		Response response = uploadManager.put(inputStream, key, upToken, null, null);
		if(response.statusCode!=200) {
			throw new InvalidException("上传失败！");
		}
	}
	
	
	public static void uploadNuxt() throws QiniuException, FileNotFoundException {
		// 构造一个带指定 Region 对象的配置类  
		Configuration cfg = new Configuration(Zone.zone2());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		String accessKey = "yE6HBw0ExlMywztKbg1utFWEdm3VCkhRCJIPY1Nm";
		String secretKey = "AFCP6ZexKtMbYAvaH_Yn5PvXvfMuOJG1kC117hzS";
		String bucket = "yqlqny";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		CdnManager c = new CdnManager(auth);
		
		File file = new File("D:\\project\\yql\\nuxtApp\\.nuxt\\dist\\client");
		FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "_nuxt", 1000, "");
		while (fileListIterator.hasNext()) {
		    //处理获取的file list结果
		    FileInfo[] items = fileListIterator.next();
		    for (FileInfo item : items) {
		    	System.out.println(item.key);
		        bucketManager.delete(bucket, item.key);
		    }
		}
		
		uploadNuxt(file,upToken,uploadManager,c);
	}


	private static void uploadNuxt(File file2,String upToken,UploadManager uploadManager,CdnManager c) throws QiniuException, FileNotFoundException {
		File[] listFiles = file2.listFiles();
		for (File file : listFiles) {
			if(file.isFile()) {
				String path = file.getAbsolutePath().replace("D:\\project\\yql\\nuxtApp\\.nuxt\\dist\\client\\", "");
				path = "_nuxt/"+path.replace("\\", "/");
				uploadManager.put(new FileInputStream(file), path, upToken, null, null);
//				String[] urls = new String[] {"http://wxh.wucunhua.com/_nuxt/"+path};
//				c.refreshUrls(urls);
			}else {
				uploadNuxt(file,upToken,uploadManager,c);
			}
		}
	}
	
	
	public static void uploadAdmin() throws QiniuException, FileNotFoundException {
		// 构造一个带指定 Region 对象的配置类  
		Configuration cfg = new Configuration(Zone.zone2());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		String accessKey = "yE6HBw0ExlMywztKbg1utFWEdm3VCkhRCJIPY1Nm";
		String secretKey = "AFCP6ZexKtMbYAvaH_Yn5PvXvfMuOJG1kC117hzS";
		String bucket = "yqlqny";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		CdnManager c = new CdnManager(auth);
		
		
		FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "admin", 1000, "");
		while (fileListIterator.hasNext()) {
		    //处理获取的file list结果
		    FileInfo[] items = fileListIterator.next();
		    for (FileInfo item : items) {
		    	System.out.println(item.key);
		        bucketManager.delete(bucket, item.key);
		    }
		}
		
		File file = new File("D:\\project\\yql\\vueAdmin\\admin");
		uploadAdmin(file,upToken,uploadManager,c);
	}


	private static void uploadAdmin(File file2,String upToken,UploadManager uploadManager,CdnManager c) throws QiniuException, FileNotFoundException {
		File[] listFiles = file2.listFiles();
		for (File file : listFiles) {
			if(file.isFile()) {
				String path = file.getAbsolutePath().replace("D:\\project\\yql\\vueAdmin\\admin", "");
				path = "admin"+path.replace("\\", "/");
				
				uploadManager.put(new FileInputStream(file), path, upToken, null, null);
				String[] urls = new String[] {"http://wxh.wucunhua.com/admin/"+path};
				c.refreshUrls(urls);
			}else {
				uploadAdmin(file,upToken,uploadManager,c);
			}
		}
	}
	
	public static void uploadDir(String dir) throws QiniuException, FileNotFoundException {
		// 构造一个带指定 Region 对象的配置类  
		Configuration cfg = new Configuration(Zone.zone2());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		String accessKey = "yE6HBw0ExlMywztKbg1utFWEdm3VCkhRCJIPY1Nm";
		String secretKey = "AFCP6ZexKtMbYAvaH_Yn5PvXvfMuOJG1kC117hzS";
		String bucket = "yqlqny";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		CdnManager c = new CdnManager(auth);
		
		
		FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "push", 1000, "");
		while (fileListIterator.hasNext()) {
		    //处理获取的file list结果
		    FileInfo[] items = fileListIterator.next();
		    for (FileInfo item : items) {
		    	System.out.println(item.key);
		        bucketManager.delete(bucket, item.key);
		    }
		}
		
		File file2 = new File(dir);
		File[] listFiles = file2.listFiles();
		for (File file : listFiles) {
			if(file.isFile()) {
				String path = file.getAbsolutePath().replace("\\", "/").replace(dir, "");
				path = "push"+path;
				uploadManager.put(new FileInputStream(file), path, upToken, null, null);
				String[] urls = new String[] {"http://wxh.wucunhua.com/"+path};
				c.refreshUrls(urls);
			}
		}
	}
	
	public static void uploadFile(String dir,String fileName) throws QiniuException, FileNotFoundException {
		// 构造一个带指定 Region 对象的配置类  
		Configuration cfg = new Configuration(Zone.zone2());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		String accessKey = "yE6HBw0ExlMywztKbg1utFWEdm3VCkhRCJIPY1Nm";
		String secretKey = "AFCP6ZexKtMbYAvaH_Yn5PvXvfMuOJG1kC117hzS";
		String bucket = "yqlqny";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		CdnManager c = new CdnManager(auth);
		Boolean isDelete = false;
		try {
			bucketManager.delete(bucket, "file"+fileName);
			isDelete = true;
		}catch(Exception e) {
		}
		File file = new File(dir+fileName);
		uploadManager.put(new FileInputStream(file), "file"+fileName, upToken, null, null);
		if(isDelete) {
			String[] urls = new String[] {"http://wxh.wucunhua.com/file"+fileName};
			c.refreshUrls(urls);
		}
	}
	public static void main(String[] args) throws QiniuException, FileNotFoundException {
//		uploadAdmin();
		uploadNuxt();
//		uploadDir("D:/push");
//		uploadFile("D:/push","/2.jpg");
	}
}
