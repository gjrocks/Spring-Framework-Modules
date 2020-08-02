package com.gj.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gj.model.User;
import com.gj.util.PayloadValidator;

public class PayloadValidatorTest {

	@Test
	public void validatePayLoad() {
		User user = new User(1,"fname", "lname", "address", "userid", "mobileNumber", "email@email.com");
		assertEquals(false, PayloadValidator.validateCreatePayload(user));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		User user =new User("fname", "lname", "address", "userid", "mobileNumber", "email@email.com");
		assertEquals(true, PayloadValidator.validateCreatePayload(user));
	}
	
	

}
