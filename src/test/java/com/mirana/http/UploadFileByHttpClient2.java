package com.mirana.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2018年7月5日 ： 下午3:42:44
 */
public class UploadFileByHttpClient2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// 上传文件的地址，此处为示例
		String url = "http://localhost/watermark/addmark/upload";
		//	自定义构造HttpClient
		CloseableHttpClient httpClient = HttpClients.custom().addInterceptorFirst(new HttpResponseInterceptor() {

			@Override
			public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
				//	提前消费HTTP InputStream
				HttpEntity resEntity = response.getEntity();
				BufferedInputStream ins = new BufferedInputStream(resEntity.getContent());
				FileOutputStream fos = new FileOutputStream("water1.png");
				byte[] buffer = new byte[4096];
				int len = 0;
				while ((len = ins.read(buffer)) > -1) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
		}).build();
		
		// 准备上传的文件
		FileBody bin = new FileBody(new File("test.png"));
		HttpPost httpPost = new HttpPost(url);
		HttpEntity reqEntity = MultipartEntityBuilder.create()
				// 表单文件项
				.addPart("srcImg", bin)
				// 类似于表单输入项
				.addTextBody("user", "yiifaa").build();
		httpPost.setEntity(reqEntity);
		//	提交表单
		CloseableHttpResponse response = httpClient.execute(httpPost);
		//	关闭HTTP连接
		response.close();
	}

}
