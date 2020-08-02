package com.gj.ws.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
@Provider
@Logged
public class LoggingFilter implements ContainerRequestFilter,
		ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext arg0,
			ContainerResponseContext arg1) throws IOException {
		System.out.println("Response Context :" +arg1.getEntity());

	}

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		System.out.println("Request Context :" +arg0.getRequest());

	}

}