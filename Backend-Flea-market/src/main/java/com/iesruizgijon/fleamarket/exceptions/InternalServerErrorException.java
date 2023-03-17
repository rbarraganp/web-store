package com.iesruizgijon.fleamarket.exceptions;

import java.io.Serial;
import java.util.Arrays;

import com.iesruizgijon.fleamarket.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;


public class InternalServerErrorException extends FleaMarketException {


	@Serial
	private static final long serialVersionUID = 4L;

	public InternalServerErrorException(final String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public InternalServerErrorException(final String message, final ErrorDto data) {
		super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}
}
