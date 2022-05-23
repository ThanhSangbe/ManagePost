package com.pts.managepost.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowed extends RuntimeException {

	public MethodNotAllowed(String msg)
	{
		super(msg);

	}
}
