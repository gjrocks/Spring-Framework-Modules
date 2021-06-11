package com.gj.util;

import com.gj.model.UserProject;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(UserProject toDo){
		if (toDo.getId() > 0){
			return false;
		}
		return true;
	}

}
