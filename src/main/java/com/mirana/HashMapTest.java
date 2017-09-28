/*
 * (c) Copyright 2017 STI技术小组
 * http://www.stixu.com
 */
package com.mirana;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 甘焕
 *
 * 创建日期：2017年8月9日
 * version : 1.0.0
 */
public class HashMapTest {
	
	//final static ConcurrentHashMap<String, String> ch = new ConcurrentHashMap<>();
	final static HashMap<String, String> ch = new HashMap<>();
	
	final static StringBuffer buffer = new StringBuffer();
	
	//	final static ConcurrentHashMap<String, Integer> rs = new ConcurrentHashMap<String, Integer>();
	
	static volatile int times = 0;
	
	static AtomicInteger counter = new AtomicInteger(0);
	
	public static class R implements Runnable {

		private String end;
		
		
		/**
		 * @param end
		 */
		public R(String end) {
			super();
			this.end = end;
		}


		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
		/*	while(!this.end.equals(System.getProperty("end"))) {
				System.out.println(this.end + System.getProperty("end"));
			}*/
			Map<String, String> rs = new HashMap<String, String>();
			String end = null;
			boolean printed = false;
			int start = -1;
			while(!this.end.equals(end = ch.get("end"))) {
				//buffer.append(this.end);
				System.out.println(this.end + ":" + end);
				/**
				int intValue = counter.intValue();
				if(start != intValue) {
					start = intValue;
					System.out.println(this.end + ":" + start);
				}
				**/
				/**
				if(this.end.equals(String.valueOf(times))) {
					System.out.println(ch.get("end") + ":"+this.end);
				}
				**/
				//String r = ch.get("end");
				//rs.put(end, end);
			/*	if(r != null) {
					rs.put(r, r);
				}*/
				/*System.out.println(this.end + ":" +end);
				if(rs.size() > Integer.parseInt(this.end) && !printed) {
					System.out.println(this.end + ":"+ rs);
					printed = true;
				}*/
				//Integer.parseInt(ch.get("end")) > 9
			}
			
			System.out.println(this.end + ": is End!");
		}
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File a = new File("output.txt");
		if(a.exists()) {
			a.createNewFile();
		}		System.setOut(new PrintStream(a));	
		for(int i = 0; i < 10; i++) {
			new Thread(new R(String.valueOf(i))).start();
		}
		new Thread(() -> {
			int j = 0;
			while(j < 10) {
				ch.put("end", String.valueOf(j));
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.setProperty("end", String.valueOf(j));
				//Thread.yield();
				//times ++;
				j ++;
				//System.out.println("--------");
				//times ++;
				//counter.getAndIncrement();
				//buffer.append("S");
				System.out.println("start j : -----" + j);
			}
		}).start();
		
	}

}
