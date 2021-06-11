package com.gj;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestHolder {

	private final static ThreadLocal<Map<String, String>> headers = new ThreadLocal<>();
	private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

	public static void addHeaders(String key, String value) {
		if(headers.get()==null){
			headers.set(new HashMap<String,String>());
		}
		headers.get().put(key, value);
	}

	public static void add(HttpServletRequest request) {
		requestHolder.set(request);
	}

	public static Map<String,String> getHeaders() {
		return headers.get();
	}

	public static HttpServletRequest getCurrentRequest() {
		return requestHolder.get();
	}

	public static void remove() {
		headers.remove();
		requestHolder.remove();
	}

	public static String getTraceState() {
		if(headers!=null && headers.get()!=null && headers.get().size()>0) {
			StringBuilder traceState=new StringBuilder();
			for (String key : headers.get().keySet()) {
				traceState.append(key).append("=").append(headers.get().get(key)).append(",");
			}
			return traceState.toString();
		}
		return null;
	}
}
