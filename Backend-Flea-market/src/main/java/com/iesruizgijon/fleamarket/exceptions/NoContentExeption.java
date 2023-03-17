package com.iesruizgijon.fleamarket.exceptions;

import com.iesruizgijon.fleamarket.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;


public class NoContentExeption extends  FleaMarketException{

    public NoContentExeption(final String message) {
        super(HttpStatus.NO_CONTENT.value(), message);
    }

    public NoContentExeption(final String message, final ErrorDto data) {
        super(HttpStatus.NO_CONTENT.value(), message, Arrays.asList(data));
    }

}
