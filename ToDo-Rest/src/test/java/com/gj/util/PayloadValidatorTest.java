package com.gj.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gj.model.ToDo;
import com.gj.util.PayloadValidator;

public class PayloadValidatorTest {

	@Test
	public void validatePayLoad() {
		ToDo toDo = new ToDo(1, "Sample ToDo 1", true);
		assertEquals(false, PayloadValidator.validateCreatePayload(toDo));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		ToDo toDo = new ToDo(0, "Sample ToDo 1", true);
		assertEquals(true, PayloadValidator.validateCreatePayload(toDo));
	}
	
	

}
