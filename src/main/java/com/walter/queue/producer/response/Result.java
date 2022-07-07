package com.walter.queue.producer.response;

import lombok.Getter;

@Getter
public class Result {
	public static final Result OK = new Result(200, "Success", false);
	public static final Result FAILURE = new Result(404, "Failure. Bad Request", true);

	private final int status;
	private final String message;
	private final boolean error;

	public Result(int status, String message, boolean error) {
		this.status = status;
		this.message = message;
		this.error = error;
	}

	public Result(Exception e) {
		this.status = 400;
		this.message = e.getMessage();
		this.error = true;
	}
}
