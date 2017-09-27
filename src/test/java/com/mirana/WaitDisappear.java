/*
 * (c) Copyright 2017 STI技术小组
 * http://www.stixu.com
 */
package com.mirana;

/**
 * @author 甘焕
 *
 * 创建日期：2017年7月21日
 * version : 1.0.0
 */
public class WaitDisappear {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		synchronized (WaitDisappear.class) {
			WaitDisappear.class.wait();
		}
	}

}
