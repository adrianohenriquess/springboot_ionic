package br.com.inovacenter.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8165504244908307958L;

	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
