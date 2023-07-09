package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExcepionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ApiResult<?> handlerNullPointerException(NullPointerException e) {
        return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotAuthenticatedException.class)
    public ApiResult<?> handlerNotAuthenticatedException(NotAuthenticatedException e) {
        return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult<?> handlerIllegalArgumentException(IllegalArgumentException e) {
        return ApiUtils.error(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}

