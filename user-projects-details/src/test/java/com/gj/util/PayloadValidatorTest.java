package com.gj.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gj.model.UserProject;
import com.gj.util.PayloadValidator;

public class PayloadValidatorTest {

	@Test
	public void validatePayLoad() {
		UserProject toDo = new UserProject(1, "Sample ToDo 1", true);
		assertEquals(false, PayloadValidator.validateCreatePayload(toDo));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		UserProject toDo = new UserProject(0, "Sample ToDo 1", true);
		assertEquals(true, PayloadValidator.validateCreatePayload(toDo));
	}
	
	

}
