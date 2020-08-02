package com.gj.util;

import com.gj.model.User;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(User toDo){
		if (toDo.getId() > 0){
			return false;
		}
		return true;
	}

}
