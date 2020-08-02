package com.gj.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gj.model.User;
import com.gj.util.PayloadValidator;
public class GJValidatorTest {

	@Test
	public void testTodoForFalse(){
		User testTodo=new User(1,"test",false);
		assertFalse(PayloadValidator.validateCreatePayload(testTodo));
	}
	
	@Test
	public void testTodoForTrue(){
		User testTodo=new User(0,"test",false);
		assertTrue(PayloadValidator.validateCreatePayload(testTodo));
	}
}
