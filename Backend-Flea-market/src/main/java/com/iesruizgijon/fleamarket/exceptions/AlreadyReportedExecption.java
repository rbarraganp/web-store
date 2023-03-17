package com.iesruizgijon.fleamarket.exceptions;

import com.iesruizgijon.fleamarket.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class AlreadyReportedExecption extends FleaMarketException{

    private static final long serialVersionUID = 1L;


    public AlreadyReportedExecption(final String message) {
        super(HttpStatus.ALREADY_REPORTED.value(), message);
    }

    public AlreadyReportedExecption(final String message, final ErrorDto data) {
        super(HttpStatus.ALREADY_REPORTED.value(), message, Arrays.asList(data));
    }

}
