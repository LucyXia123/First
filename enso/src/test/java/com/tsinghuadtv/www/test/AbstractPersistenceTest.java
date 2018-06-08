package com.tsinghuadtv.www.test;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public abstract class AbstractPersistenceTest {

	protected <T> void printList(List<T> list) {
    	System.out.println(list.size());
    	int count = 0;
    	for (T t : list) {
    		System.out.println(ReflectionToStringBuilder.toString(t));
    		if (++ count >= 3) {
    			break;
    		}
    	}
    }
}
