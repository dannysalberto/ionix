package com.ionix.testdev.exceptions;

public class UserNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(){
        super("Usuario no existe");
	}

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
