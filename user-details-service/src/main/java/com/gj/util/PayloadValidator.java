package com.gj.util;

import com.gj.model.User;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(User user){
		if (user.getId() > 0){
			return false;
		}
		return true;
	}

}
