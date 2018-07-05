package com.mirana.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2018年7月5日 ： 下午3:29:31
 */
public class UploadFileByHttpClient {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// 上传文件的地址，此处为示例
		String url = "http://localhost/watermark/addmark/upload";
		// 声明HTTP客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
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
		try {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				BufferedInputStream ins = new BufferedInputStream(resEntity.getContent());
				//	准备输出到文件
				FileOutputStream fos = new FileOutputStream("water1.png");
				byte[] buffer = new byte[4096];
				int len = 0;
				while ((len = ins.read(buffer)) > -1) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
		} finally {
			response.close();
		}
	}

}
