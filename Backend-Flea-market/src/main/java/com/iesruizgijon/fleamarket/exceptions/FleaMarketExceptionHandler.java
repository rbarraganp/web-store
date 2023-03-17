package com.iesruizgijon.fleamarket.exceptions;


import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FleaMarketExceptionHandler {

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public FleaMarketResponse unhandledErrors(HttpServletRequest req, Exception ex) {
		return new FleaMarketResponse(ExceptionConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
	}

	@ExceptionHandler({ FleaMarketException.class })
	@ResponseBody
	public FleaMarketResponse handleLmException(final HttpServletRequest request, final HttpServletResponse response,
			final FleaMarketException ex) {
		response.setStatus(ex.getCode());
		return new FleaMarketResponse(ExceptionConstants.ERROR, String.valueOf(ex.getCode()), ex.getMessage(),
				ex.getErrorList());
	}
}
