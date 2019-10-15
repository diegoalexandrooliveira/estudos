package com.acme.customers.api.rest.v1.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String resource;
	private String identifier;
	
	
	public ResourceNotFoundException(String resource, String identifier) {
		super();
		this.resource = resource;
		this.identifier = identifier;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
	

}
