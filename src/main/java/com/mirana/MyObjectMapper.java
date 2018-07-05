package com.mirana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年9月11日 ： 下午4:49:28 
 */
public class MyObjectMapper extends ObjectMapper {

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ObjectMapper#readValue(java.io.InputStream, com.fasterxml.jackson.databind.JavaType)
	 */
	@Override
	public <T> T readValue(InputStream src, JavaType valueType)
			throws IOException, JsonParseException, JsonMappingException {
/*		BufferedReader reader = new BufferedReader(new InputStreamReader(src, Charset.defaultCharset()));
		String line = null;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}*/
		return super.readValue(src, valueType);
	}
	
	

}
