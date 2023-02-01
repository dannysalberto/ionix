package com.ionix.testdev.exceptions;

import com.ionix.testdev.common.CommonConstants;

public class BadRequestException  extends RuntimeException {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public BadRequestException(){
	        super(CommonConstants.BADREQUEST_MESSAGE);
	    }

	    public BadRequestException(String message) {
	        super(message);
	    }

	    public BadRequestException(Throwable cause) {
	        super(cause);
	    }

	    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }

}
