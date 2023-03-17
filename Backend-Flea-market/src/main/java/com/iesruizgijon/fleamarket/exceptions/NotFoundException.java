package com.iesruizgijon.fleamarket.exceptions;

import com.iesruizgijon.fleamarket.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.Arrays;

public class NotFoundException extends FleaMarketException {

	@Serial
	private static final long serialVersionUID = 1L;

	public NotFoundException(final String message) {
		super(HttpStatus.NOT_FOUND.value(), message);
	}

	public NotFoundException(final String message, final ErrorDto data) {
		super(HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}
}
