package com.ionix.testdev.common;

public class CommonConstants {

	public static final int NOTFOUND_CODE = 404;
	public static final int SUCCESS_CODE = 200;
	public static final int ERROR_CODE = 500;
	public static final int BADREQUEST_CODE = 400;
	
	
	public static final String NOTFOUND_MESSAGE = "No se encontró data";
	public static final String SUCCESS_MESSAGE = "Proceso exitoso";
	public static final String ERROR_MESSAGE = "Error Interno";
	public static final String BADREQUEST_MESSAGE = "Error en solicitud";
	
	public static final String NOT_EMPTY_NOT_NULL = " no debe ser nulo o vacio";
	public static final String NAME_FIELD = "Nombre";
	public static final String EMAIL_FIELD = "Correo";
	public static final String PHONE_FIELD = "Teléfono";
	
	public static final String EMAIL_NOT_VALID = "Debe indicar un email válido ";
	public static final String URLS_USER_LIST = "/users/list";
	public static final String URLS_USER_BY_EMAIL = "/users/getbyemail/*";
	
	//jwt
	public static final String ISSUER_INFO = "/swagger-ui.html";	
	public static final String ACCESS_KEY_TOKEY = "482fe36cb1f40fddce8ccb19bc7f585d";
	public static final String CIPHER_KEY = "ionix123456";


	
	
}
