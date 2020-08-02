package com.gj.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gj.model.ToDo;
import com.gj.util.PayloadValidator;
public class GJValidatorTest {

	@Test
	public void testTodoForFalse(){
		ToDo testTodo=new ToDo(1,"test",false);
		assertFalse(PayloadValidator.validateCreatePayload(testTodo));
	}
	
	@Test
	public void testTodoForTrue(){
		ToDo testTodo=new ToDo(0,"test",false);
		assertTrue(PayloadValidator.validateCreatePayload(testTodo));
	}
}
