package com.classnotice.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.classnotice.exceptions.NoPermissionException;

@ControllerAdvice
public class ExceptionHandlingController{

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NoPermissionException.class)
	public String handleForbiddenException(){
		return "forbiddenError";
	}
}
