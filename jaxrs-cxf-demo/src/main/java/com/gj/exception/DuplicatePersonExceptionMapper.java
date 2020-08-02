package com.gj.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class DuplicatePersonExceptionMapper implements
		ExceptionMapper<DuplicatePersonException> {

	@Override
	public Response toResponse(DuplicatePersonException arg0) {
		
		return Response.status(Response.Status.CONFLICT).build();
	}

}
