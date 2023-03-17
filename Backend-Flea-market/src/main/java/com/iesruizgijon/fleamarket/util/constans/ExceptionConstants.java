package com.iesruizgijon.fleamarket.util.constans;

public class ExceptionConstants {

	public static final String ERROR = "ERROR";

	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR - An internal server error has ocurred";

	public static final String MESSAGE_ALREADY_EXIST_USER = "ALREADY_EXIST_USER- This user is already registered";
	public static final String MESSAGE_INEXISTENT_USER = "INEXISTENT_USER- This user not registered ";

	public static final String MESSAGE_INEXISTENT_AC = "INEXISTENT_AC- This AC not registered ";

	public static final String MESSAGE_INEXISTENT_PROVINCE = "INEXISTENT_PROVINCE This province not registered ";

	public static final String MESSAGE_INEXISTENT_LOCALITY = "INEXISTENT_LOCALITY This locality not registered ";

	public static final String MESSAGE_INEXISTENT_CATEGORY = "INEXISTENT_CATEGORY This category not registered ";

	public static final String MESSAGE_INEXISTENT_SUBCATEGORY = "INEXISTENT_SUBCATEGORY This subcategory not registered ";

	public static final String MESSAGE_PASSWORD_USER_FAIL = "PASSWORD_USER- This password user is not correct  ";

	public static final String MESSAGE_INEXISTENT_AD = "INEXISTENT_AD- This ad not registered ";

	public static final String MESSAGE_INEXISTENT_USER_NICK = "INEXISTENT_USER_NICK- This nick not used ";

	public static final String MESSAGE_INEXISTENT_USER_EMEAL = "INEXISTENT_USER_EMEAL- This nick not used ";

	public static final String MESSAGE_USER_PASSWORD_FAIL= "USER_PASSWORD_FAIL- Wrong username or password ";

	private ExceptionConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
