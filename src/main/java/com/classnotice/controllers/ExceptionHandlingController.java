package com.classnotice.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.dao.EmptyResultDataAccessException;

import com.classnotice.exceptions.NoPermissionException;

@ControllerAdvice
public class ExceptionHandlingController{

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NoPermissionException.class)
	public String handleForbiddenException(){
		return "forbiddenError";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleNotFoundException(){
		return "notFoundError";
	}
}
