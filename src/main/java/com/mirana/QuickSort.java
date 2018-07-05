/*
 * (c) Copyright 2017 STI技术小组
 * http://www.stixu.com
 */
package com.mirana;

import java.util.Stack;

/**
 * @author 甘焕
 *
 * 创建日期：2017年8月12日
 * version : 1.0.0
 */
public class QuickSort {
	
	static int sort(int[] nums, int start, int end) {
		int pivot = nums[start];
		int k = start;
		while(end > start) {
			while(end > start && nums[end] >= pivot) {
				end --;
			}
			if(end > start) {
				nums[k] = nums[end];
				k = end;
			}
			while(end > start && nums[start] <= pivot) {
				start ++;
			}
			if(end > start) {
				nums[k] = nums[start];
				k = start;
			}
			nums[k] = pivot;
		}
		return k;
	}
	
	static void quickSort(int[] nums, int start, int end) {
		int mid = sort(nums, start, end);
		if(mid - 1 > start) {
			quickSort(nums, start, mid - 1);
		}
		if(mid + 1 < end) {
			quickSort(nums, mid + 1, end);
		}
		/**
		if(start < end) {
			quickSort(nums, start, mid - 1);
			quickSort(nums, mid + 1, end);
		}
		**/
	}
	
	static void quickSortArr(int[] nums) {
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {0, nums.length - 1});
		while(!stack.isEmpty()) {
			int[] index = stack.pop();
			int start = index[0];
			int end = index[1];
			//	第一种解决办法
			int i = start + 1;
			int j = end;
			int k = start;
			int pivot = nums[start];
			while(i < j) {
				while(i < j && nums[j] > pivot) {
					j --;
				}
				if(i < j) {
					nums[k] = nums[j];
					k = j;
				}
				//	第二种解决办法
				//	while(i < j && nums[i] <= pivot)
				while(i < j && nums[i] < pivot) {
					i ++;
				}
				if(i < j) {
					nums[k] = nums[i];
					k = i;
				}
				nums[k] = pivot;
			}
			if(k - 1 > start) {
				stack.push(new int[] {start, k - 1});
			}
			if(k + 1 < end) {
				stack.push(new int[] {k + 1, end});
			}
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = {1, 3, 2, 4, 2, 9, 5, 7, 3};
		quickSort(nums, 0, nums.length - 1);
		// 1. 
		//	quickSortArr(nums);
		for(int num : nums) {
			System.out.print(num + ",");
		}
	}

}
