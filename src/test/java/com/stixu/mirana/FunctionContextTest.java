package com.stixu.mirana;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.google.common.collect.Lists;

public class FunctionContextTest {

	@Test
	public void test() {
		Function<Integer, Integer> val = sum(1, 2);
		System.out.println(val.apply(3));
		Function<Integer, Integer> vals = sum(4, 5);
		System.out.println(vals.apply(6));
		//System.out.println(this::test);
		//	this::add(2, 3);
	}
	
	@Test
	public void testFunction() {
		IntFunction<Integer> addFun = this.addNum(3, items -> {
			return items.stream().reduce(0, (x, y) -> x + y);
		});
		addFun.apply(1);
		addFun.apply(2);
		addFun.apply(3);
		int result = addFun.apply(4);
		assertThat(result, IsEqual.equalTo(6));
	}
	
	public int add(int a, int b) {
		return a + b;
	}
	
	/**
	 * 输入一定数量的参数，然后统一求值
	 * @param size 需要求值的个数
	 * @param fn 求值函数
	 * @return 函数对象
	 */
	public IntFunction<Integer> addNum(int size, ToIntFunction<List<Integer>> fn) {
		final List<Integer> args = Lists.newArrayList();
		return new IntFunction<Integer>() {
			
			@Override
			public Integer apply(int value) {
				int result = -1;
				if(args.size() == size) {
					result = fn.applyAsInt(args);
				} else {
					args.add(value);
				}
				return result;
			}
			
		};
		
	}
	
	public Function<Integer, Integer> sum(int a, int b) {
		int count = a + b;
		return new Function<Integer, Integer>() {

			@Override
			public Integer apply(Integer t) {
				return count + t;
			}
			
		};
	}
	
	public IntFunction<Integer> curry(Function<List<Integer>, Integer> fn) {
		
		return new IntFunction<Integer>() {

			@Override
			public Integer apply(int value) {
				
				return null;
			}
			
		};
	}
	
	public int tail(int n, int total) {
		if(n == 1) {
			return total;
		}
		return tail(n-1, n * total);
	}
	
	public IntFunction<Integer> factor(int start, int end) {
		int result = 0;
		if(start == end) {
			result = start;
		}
		
		return new IntFunction<Integer>() {

			@Override
			public Integer apply(int value) {
				return 0; 
				//	return ;
			}
			
		};
	}
	
	//	public In 

}
