package com.gj;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
@Provider
public class LoggingClientFilter implements ClientRequestFilter,
		ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext arg0, ClientResponseContext arg1)
			throws IOException {
		System.out.println("In client ClientResposeFilter ");

	}

	@Override
	public void filter(ClientRequestContext arg0) throws IOException {
		System.out.println("In client ClientRequestFilter ");

	}

}
