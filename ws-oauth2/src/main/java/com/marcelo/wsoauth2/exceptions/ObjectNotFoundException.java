package com.marcelo.wsoauth2.exceptions;

import java.io.Serializable;

public class ObjectNotFoundException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException (String message) {
		super(message);
	}
}
