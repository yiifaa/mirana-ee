package com.mirana.nio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;

import com.google.common.base.Stopwatch;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年9月28日 ： 上午10:23:21 
 */
public class CreateBigFile {
	static final String word64 = "协程,趋势,只是,一种,解决,问题,思维,方式,不能,解决,所有,问题,例如,对于,计算,现在,协程,问题,协程,简单,好问题,";
	static final String word128 = word64 + word64;
	static final String word256 = word128 + word128;
	static final String word512 = word256 + word256;
	static final String word1024 = word512 + word512;
	static final String word2048 = word1024 + word1024;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("a6.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		//Thread.sleep(10000);
		Stopwatch stopwatch = Stopwatch.createStarted();
		//writeBuffer(file);
		//writeFileXTimes(file);
		//writeWithFos(file);
		//writeFileXTimes(file);
		//writeWithMap(file);
		//writeBuffer(file);
		writeFileATimes(file);
		//writeFileXTimes(file);
		//readChannel(file);
		//cbuf
		//System.out.println(word512.getBytes().length);
		//bbuf.put(word2048.getBytes());
		//bbuf.flip();
		stopwatch.stop(); 
		Duration duration = stopwatch.elapsed();
		System.out.println("time: " + duration); // formatted string like "12.3 ms"

	}
	
	private static void writeWithFos(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		byte[] bs = word2048.getBytes();
		int i = 1000000;
		while(i > 0) {
			fos.write(bs);
			i = i - 1;
		}
		fos.close();
	}
	
	private static void writeWithMap(File file) throws IOException {
		RandomAccessFile acf = new RandomAccessFile(file, "rw");
		//FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = acf.getChannel();
		byte[] bs = word2048.getBytes();
		int len = bs.length * 1000;
		long offset = 0;
		
		int i = 2000000;
		while(i > 0) {
			MappedByteBuffer mbuf = fc.map(FileChannel.MapMode.READ_WRITE, offset, len );
			for(int j = 0; j < 1000; j ++) {
				mbuf.put(bs);
			}
			offset = offset + len;
			i = i - 1000;
		}
		//	fos.close();
		fc.close();
	}


	/**
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void readChannel(File file) throws FileNotFoundException, IOException {
		//	writeFile(file);
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		ByteBuffer bbuf = ByteBuffer.allocateDirect(1200);
		long offset = 0;
		long nums = 0;
		while((nums = fc.read(bbuf, offset)) > 0) {
			bbuf.flip();
			CharBuffer cbuf = bbuf.asCharBuffer();
			System.out.println(cbuf);
			bbuf.clear();
			offset = offset + nums;
		}
		
		fc.close();
		fis.close();
	}
	
	
	/**
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void writeFile(File file) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		
		ByteBuffer bbuf = ByteBuffer.allocate(4800);
		CharBuffer cbuf = bbuf.asCharBuffer();
		cbuf.put(word2048);
		cbuf.flip();
		bbuf.position(cbuf.position()).limit(cbuf.limit() * 2);
		//
		fc.write(bbuf);
		fc.close();
		fos.close();
	}
	
	
	private static void writeFileXTimes(File file) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		int times = 100;
		byte[] datas = word2048.getBytes();
		ByteBuffer bbuf = ByteBuffer.allocate(4800 * times);
		int i = 10000;
		while(i > 0) {
			for(int j = 0; j < times; j++) {
				bbuf.put(datas);
			}
			bbuf.flip();
			fc.write(bbuf);
			bbuf.clear();
			i --;
		}
		//CharBuffer cbuf = bbuf.asCharBuffer();
		//cbuf.put(word2048);
		//cbuf.flip();
		//bbuf.position(cbuf.position()).limit(cbuf.limit() * 2);
		//
		fc.write(bbuf);
		fc.close();
		fos.close();
	}
	
	private static void writeBuffer(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		int i = 1000000;
		while(i > 0) {
			writer.write(word2048);
			i --;
		}
		writer.close();
		fos.close();
	}
	
	private static void writeFileATimes(File file) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		ByteBuffer bbuf = ByteBuffer.allocateDirect(1024);
		long value = 10 * 1024 * 1024;
		for(int i = 1; i < 1025; i = i * 2) {
			bbuf.put((byte)1);
			bbuf.flip();
			fc.position(i * value);
			fc.write(bbuf);
			bbuf.clear();
		}
		fc.close();
		fos.close();
	}

}
