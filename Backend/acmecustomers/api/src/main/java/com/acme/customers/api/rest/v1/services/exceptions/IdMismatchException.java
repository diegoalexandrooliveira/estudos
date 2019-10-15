package com.acme.customers.api.rest.v1.services.exceptions;

public class IdMismatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String pathId;
	private String entityId;

	public String getPathId() {
		return pathId;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public IdMismatchException(String pathId, String entityId) {
		super();
		this.pathId = pathId;
		this.entityId = entityId;
	}

}
