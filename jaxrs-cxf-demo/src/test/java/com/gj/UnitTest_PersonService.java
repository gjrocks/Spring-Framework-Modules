package com.gj;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UnitTest_PersonService {

	ApplicationContext ctx=null;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("beans.xml");
		System.out.println(ctx);
	}
	
	//writing the httpclient based rest client
	@Test
	public void unitTest_PersonService(){
		
	}
}
