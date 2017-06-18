package com.stixu.mirana;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class GenericTest {
	
	interface Person {
		
	}
	
	class Employee implements Person {
		
	}
	
	public void testInterface() {
		List<? super Employee> pp = Lists.newArrayList();
		List<? extends Employee> ps = Lists.newArrayList();
		//pp.addAll(Lists.newArrayList("String"));
	}

	@Test
	public void test() {
		List<? super String> allNames = Lists.newArrayList("yiifaa");
		List<? extends String> names = Lists.newArrayList("yiifee");
		allNames.addAll(names);
		//	names.add("yiifee");
		for(Object name: allNames) {
			System.out.println(name);
		}
	}
	
	@Test
	public void test1() {
		List<? extends String> names = Lists.newArrayList("yiifaa");
		for(String name: names) {
			System.out.println(name);
		}
		List<String> allNames = Lists.newArrayList("yiifee");
		allNames.addAll(allNames);
		//names.add();
	}
	
	@Test
	public void test2() {
		List<?> names = Lists.newArrayList("yiifaa");
		List<Object> allNames = Lists.newArrayList("yiifee");
		allNames.addAll(names);
	}

}
