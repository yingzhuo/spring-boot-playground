package com.github.yingzhuo.playground.controller;

import com.github.yingzhuo.playground.include.model.ApiResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import spring.turbo.exception.BusinessException;
import spring.turbo.exception.DataBindingException;

@RestControllerAdvice
public class _ExceptionHandlerAdvice_ extends ResponseEntityExceptionHandler implements ErrorController {

    // 200 业务异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public Object handleBusinessException(BusinessException ex) {
        return ApiResult.error("100", ex.toString(getRequiredMessageSource()));
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleWrongRequestParametersException(DataBindingException ex) {
        var joinMsg = String.join(",", ex.getMessages(getRequiredMessageSource()));
        return ApiResult.error("400", joinMsg);
    }

    // 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var api = ApiResult.error("404", "No API");
        return new ResponseEntity<>(api, null, HttpStatus.NOT_FOUND.value());
    }

    // 404
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var api = ApiResult.error("404", "No API");
        return new ResponseEntity<>(api, null, HttpStatus.NOT_FOUND.value());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @NonNull
    private MessageSource getRequiredMessageSource() {
        var messageSource = super.getMessageSource();
        if (messageSource == null) {
            throw new AssertionError();
        }
        return messageSource;
    }

}
