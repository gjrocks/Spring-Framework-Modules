package com.gj.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gj.model.UserProject;
import com.gj.util.PayloadValidator;
public class GJValidatorTest {

	@Test
	public void testTodoForFalse(){
		UserProject testTodo=new UserProject(1,"test",false);
		assertFalse(PayloadValidator.validateCreatePayload(testTodo));
	}
	
	@Test
	public void testTodoForTrue(){
		UserProject testTodo=new UserProject(0,"test",false);
		assertTrue(PayloadValidator.validateCreatePayload(testTodo));
	}
}
