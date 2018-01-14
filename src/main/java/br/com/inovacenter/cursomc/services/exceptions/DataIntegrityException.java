package br.com.inovacenter.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = -8165504244908307958L;

	public DataIntegrityException(String message) {
		super(message);
	}
	
	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
