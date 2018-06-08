package com.tsinghuadtv.www.test;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsinghuadtv.www.mapper.InformMapper;

public class InformMapperTest extends AbstractPersistenceTest {

	@Autowired
	private InformMapper informMapper;
	
//	@Test
	public void getInform() {
		System.out.println(ReflectionToStringBuilder.toString(informMapper.getInform(1)));
	}
}
