package com.worldline.interview.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.worldline.interview.utils.DefaultApiException;

@ControllerAdvice
@Component
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<DefaultApiException> handleMyException(DefaultApiException apiError, WebRequest req) {
		System.out.println("DEFAULT EXCEPTION THROWN : \n " + ExceptionUtils.getStackTrace(apiError));
		return new ResponseEntity<DefaultApiException>(apiError, new HttpHeaders(), apiError.getMessage().contains("Validate")?HttpStatus.BAD_REQUEST:HttpStatus.NOT_MODIFIED);
	}

	
}
