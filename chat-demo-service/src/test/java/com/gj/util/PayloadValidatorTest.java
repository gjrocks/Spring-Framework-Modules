package com.gj.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gj.model.User;
import com.gj.util.PayloadValidator;

public class PayloadValidatorTest {

	@Test
	public void validatePayLoad() {
		User toDo = new User(1, "Sample User 1", true);
		assertEquals(false, PayloadValidator.validateCreatePayload(toDo));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		User toDo = new User(0, "Sample User 1", true);
		assertEquals(true, PayloadValidator.validateCreatePayload(toDo));
	}
	
	

}
