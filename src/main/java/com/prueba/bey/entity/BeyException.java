package com.prueba.bey.entity;

/**
 * Exception creada para manejar los errores en la app
 * 
 * @author Jhon Lara
 *
 */
public class BeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BeyException(String message) {
		super(message);
	}
}
