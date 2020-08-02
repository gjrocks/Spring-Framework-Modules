package com.gj.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gj.model.User;
import com.gj.util.PayloadValidator;
public class GJValidatorTest {

	@Test
	public void testTodoForFalse(){
		User testTodo=new User(1,"fname", "lname", "address", "userid", "mobileNumber", "email@email.com");
		assertFalse(PayloadValidator.validateCreatePayload(testTodo));
	}
	
	@Test
	public void testTodoForTrue(){
		User testTodo=new User("fname", "lname", "address", "userid", "mobileNumber", "email@email.com");
		assertTrue(PayloadValidator.validateCreatePayload(testTodo));
	}
}
