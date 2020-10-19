package com.morrisons.services.demo.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class DemoServiceUtil {
	
	public static HttpEntity<Void> prepareAuthRequest(String token) {
		HttpHeaders authHeaders = new HttpHeaders();
		String dlHeader = String.format("DirectLogin token=%s", token);
		authHeaders.add(HttpHeaders.AUTHORIZATION, dlHeader);
		return new HttpEntity<>(null, authHeaders);
	}

}
