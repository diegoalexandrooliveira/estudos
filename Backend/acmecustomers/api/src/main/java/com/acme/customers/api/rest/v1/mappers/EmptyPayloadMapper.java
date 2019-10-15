package com.acme.customers.api.rest.v1.mappers;

import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.acme.customers.api.rest.v1.services.exceptions.EmptyPayloadException;
import com.acme.customers.lib.v1.common.ApiError;

@Provider
public class EmptyPayloadMapper implements ExceptionMapper<EmptyPayloadException> {

	@Override
	public Response toResponse(EmptyPayloadException exception) {

		ApiError error = new ApiError();
		error.setRef(UUID.randomUUID());
		error.setStatus(400);
		error.setCode("resource.empty.payload");

		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
	}

}
