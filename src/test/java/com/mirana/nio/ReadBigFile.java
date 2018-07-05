package com.mirana.nio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.time.Duration;

import com.google.common.base.Stopwatch;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年9月30日 ： 上午9:59:04 
 */
public class ReadBigFile {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		//Thread.sleep(10 * 1000);
		Stopwatch stopwatch = Stopwatch.createStarted();
		int offset = 4096;
		long counts = 0;
		File file = new File("bigFile.txt");
		FileInputStream fis = new FileInputStream(file);
		long length = file.length();
		FileChannel fc = fis.getChannel();
		while(counts < length) {
			MappedByteBuffer mbf = fc.map(MapMode.READ_ONLY, counts, 4096 * 100);
			mbf.load();
			mbf.flip();
			counts = counts + offset;
			//if(counts > 9500000)
			/*if(mbf.hasRemaining()) {
				offset = mbf.remaining();
				
				break;
			} else {
				offset = 4096;
				counts = counts + offset;
			}*/
			/*if(counts >= length) {
				byte[] dst = new byte[offset];
				mbf.get(dst, 0, mbf.limit());
				System.out.println(new String(dst));
			}*/
		}
		
		fis.close();
		//long counts = readByChannel();
		//long counts = readByBos(file);
		//long counts = readByFis(file);
		//long counts = countChars(file);
		stopwatch.stop(); 
		Duration duration = stopwatch.elapsed();
		System.out.println(counts);
		System.out.println("time: " + duration);

	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static long readByChannel() throws FileNotFoundException, IOException {
		long counts = 0;
		File file = new File("bigFile.txt");
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		ByteBuffer bbuf = ByteBuffer.allocate(2048);
		int offset = 0;
		while((offset = fc.read(bbuf)) != -1) {
			counts = counts + offset;
			bbuf.clear();
		}
		fc.close();
		fis.close();
		return counts;
	}

	/**
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static long readByBos(File file) throws FileNotFoundException, IOException {
		long counts = 0;
		int offset = 0;
		BufferedInputStream bos = new BufferedInputStream(new FileInputStream(file));
		byte[] buff = new byte[4096];
		while((offset= bos.read(buff)) != -1) {
			counts = counts + offset;
		}
		bos.close();
		return counts;
	}

	/**
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static long readByFis(File file) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(file);
		byte[] buff = new byte[4096];
		long counts = 0;
		int offset = 0;
		while((offset = is.read(buff)) != -1) {
			counts = counts + offset;
		}
		is.close();
		return counts;
	}

	/**
	 * @param file
	 * @param counts
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static long countChars(File file) throws FileNotFoundException, IOException {
		long counts = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
		//CharBuffer cbuf = CharBuffer.allocate(4096);
		char[] cbuf = new char[4096];
		int reads = 0;
		while((reads = reader.read(cbuf)) != -1) {
			counts = reads + counts;
		}
		reader.close();
		return counts;
	}

}
