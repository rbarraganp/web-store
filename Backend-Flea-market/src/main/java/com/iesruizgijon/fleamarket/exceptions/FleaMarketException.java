package com.iesruizgijon.fleamarket.exceptions;

import com.iesruizgijon.fleamarket.model.dto.ErrorDto;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class FleaMarketException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    private final int code;

    private final List<ErrorDto> errorList = new ArrayList<>();


    public FleaMarketException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public FleaMarketException(final int code, final String message, final List<ErrorDto> errorList) {
        super(message);
        this.code = code;
        this.errorList.addAll(errorList);
    }

    public int getCode() {
        return this.code;
    }

    public List<ErrorDto> getErrorList() {
        return errorList;
    }



}
