package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.rmi.UnexpectedException;

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

    @ExceptionHandler(DataAccessException.class)
    public ApiResult<?> handlerDataAccessException(DataAccessException e) {
        return ApiUtils.error(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    public ApiResult<?> handlerUnexpectedTypeException(UnexpectedTypeException e) {
        return ApiUtils.error(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ApiUtils.error(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}

