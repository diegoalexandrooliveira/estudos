package com.acme.customers.api.rest.v1.services.exceptions;

public class EmptyPayloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String resource;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public EmptyPayloadException(String resource) {
		super();
		this.resource = resource;
	}

}
