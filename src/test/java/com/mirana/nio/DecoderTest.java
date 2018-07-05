package com.mirana.nio;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.mysql.cj.core.util.Base64Decoder;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年9月30日 ： 上午10:14:06 
 */
public class DecoderTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
	/*	String key = "";
		Base64Decoder decoder = new Base64Decoder();
		byte[] b;
		b = decoder.decode(in, pos, length);
		key= new String(b,"utf-8"); 
		System.out.println(key);*/
		String key = "Naiu8lqmc6QWvI7G5sCo8UIAApqLD6pNsFjuSQ==";
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] buff = decoder.decode(key);
		
		/*for(byte b : buff) {
			//System.out.print(Integer.toHexString(b) + "\t");
			//System.out.print(b + "\t");
			if(b < 0) {
				b = Math.
			}
		}*/
		/*for(int i =0 ; i < buff.length; i ++) {
			if(buff[i] < 0) {		
				buff[i] = (byte)Math.abs(buff[i]);
			}
		}
		byte[] buf2 = decoder.decode(buff);
		System.out.println("");*/
		String pass= new String(buff, "GBK"); 
		System.out.println(pass);

	}

}
