/*
 * (c) Copyright 2017 STI技术小组
 * http://www.stixu.com
 */
package com.mirana.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * @author 甘焕
 *
 * 创建日期：2017年9月27日
 * version : 1.0.0
 */
public class FileWriteTester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("abc.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		//readFile(file);
		/**/FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		ByteBuffer buff = ByteBuffer.allocateDirect(1024);
		CharBuffer cbuff = buff.asCharBuffer();
		cbuff.put("疯了，国际米兰！");
		System.out.println(cbuff.position());
		System.out.println(cbuff.limit());
		cbuff.flip();
		System.out.println(cbuff.position());
		System.out.println(cbuff.limit());
		buff.flip();
		System.out.println(buff.position());
		System.out.println(buff.limit());
		fc.write(buff);
		fc.close();
		fos.close();
		/*FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		ByteBuffer buff = ByteBuffer.allocateDirect(1024);
		int pos = fc.read(buff);		
		buff.flip();
		if(pos > 0) {
			CharBuffer cbf = buff.asCharBuffer();
			System.out.println(cbf.toString());
		}
		fc.close();
		fis.close();*/
	}

	/**
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void readFile(File file) throws FileNotFoundException, IOException {
		//writeFile(file);
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		ByteBuffer buff = ByteBuffer.allocateDirect(1024);
		int pos = fc.read(buff);
		
		buff.flip();
		System.out.println(buff.position());
		System.out.println(buff.limit());
		byte[] buffer = new byte[1024];
		buff.get(buffer, 0, pos);
		System.out.println(new String(buffer, 0, pos));
		/*if(pos > 0) {
			CharBuffer cbf = buff.asCharBuffer();
			System.out.println(cbf.toString());
		}*/
		fc.close();
		fis.close();
	}

	/**
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void writeFile(File file) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		ByteBuffer buff = ByteBuffer.allocateDirect(1024);
		buff.put("国际米兰".getBytes());
		buff.flip();
		int pos = fc.write(buff);
		buff.clear();
		buff.put("重现辉煌".getBytes());
		buff.flip();
		fc.write(buff, pos);
		fc.close();
		fos.close();
	}

}
