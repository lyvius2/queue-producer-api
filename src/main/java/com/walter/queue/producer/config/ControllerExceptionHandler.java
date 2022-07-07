package com.walter.queue.producer.config;

import com.walter.queue.producer.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(Exception.class)
	public Result onHandleException(Exception ex) {
		ex.printStackTrace();
		return new Result(ex);
	}
}
