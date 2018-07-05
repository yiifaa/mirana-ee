package com.mirana.stream;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年9月27日 ： 下午2:55:04 
 */
public class FlatMapReduceTest {
	
	private List<String> values = Lists.newArrayList("2", "3", "4", "7", "1", "1", "2");
	
	private List<String> joins = Lists.newArrayList("1, 2, 3", "2, 3, 4", "3, 4, 5");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	public Stream<Integer> getStream() {
		return joins.stream().flatMap(str -> {
			String[] nums = str.split(",");
			return Arrays.asList(nums).stream().map(num -> {
				return Integer.parseInt(num.trim());
			});
		});
	}
	
	@Test
	public void testReStream() {
		List<String> joins = Lists.newArrayList("1, 2, 3", "2, 3, 4", "3, 4, 5");
		//Stream<Integer> items = 
		Supplier<Stream<Integer>> supplier = () -> joins.stream().flatMap(str -> {
			String[] nums = str.split(",");
			return Arrays.asList(nums).stream().map(num -> {
				return Integer.parseInt(num.trim());
			});
		});
		
		//
		assertThat(supplier.get().count(), IsEqual.equalTo(9L));
		int sum = supplier.get().reduce((a, b) -> a + b).get();
		assertThat(sum, IsEqual.equalTo(27));
	}
	
	@Ignore
	@Test
	public void testFlatMap() {
		Stream<Integer> items = joins.stream().flatMap(str -> {
			String[] nums = str.split(",");
			return Arrays.asList(nums).stream().map(num -> {
				return Integer.parseInt(num.trim());
			});
		});
		//
		assertThat(items.sorted().count(), IsEqual.equalTo(9L));
		int sum = items.reduce((a, b) -> a + b).get();
		assertThat(sum, IsEqual.equalTo(27));
	}

	@Test
	public void testReduce() {
		assertThat(values, IsNull.notNullValue());
		int result = values.stream().map(str -> {
			try {
				return Integer.parseInt(str);
			} catch(NumberFormatException ex) {
				return 0;
			}
		}).reduce((a, b) -> a + b).get();
		assertThat(result, IsEqual.equalTo(20));
	}
	
	@Test
	public void testReduceMerge() {
		Map<String, Integer> results = values.stream().reduce(new HashMap<String, Integer>(),
				(container, item) -> {
					Integer value = container.get(item);
					if(value == null) {
						value = 1;
					} else {
						value = value + 1;
					}
					container.put(item, value);
					return container;
				}
				, (c1, c2) -> {
					for(String key : c2.keySet()) {
						Integer value2 = c2.get(key);
						Integer value1 = c2.get(key);
						if(value1 == null) {
							c1.put(key, value2);
						} else {
							c2.put(key, value2 + value1);
						}
					}
					return c1;
				});
		assertThat(results.get("1"), IsEqual.equalTo(2));
		assertThat(results.get("2"), IsEqual.equalTo(2));
		assertThat(results.get("3"), IsEqual.equalTo(1));
		assertThat(results.get("4"), IsEqual.equalTo(1));
		assertThat(results.get("7"), IsEqual.equalTo(1));
	}

}
