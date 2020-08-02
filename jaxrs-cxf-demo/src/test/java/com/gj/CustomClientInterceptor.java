package com.gj;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Provider
public class CustomClientInterceptor implements ReaderInterceptor, WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext arg0)
			throws IOException, WebApplicationException {
		System.out.println("aroundWriteTo Interceptor");
     arg0.proceed();
	}

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext arg0)
			throws IOException, WebApplicationException {
		System.out.println("aroundReadFRom Interceptor");
		return arg0.proceed();
	}

}
