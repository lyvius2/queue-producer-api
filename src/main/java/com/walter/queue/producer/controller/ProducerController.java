package com.walter.queue.producer.controller;

import com.walter.queue.producer.response.Result;
import com.walter.queue.producer.service.SqsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/producer")
@Tag(name = "메시지 송신")
@ApiResponses(value = {
		@ApiResponse(responseCode = "404", description = "Invalid Parameters"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error")})
public class ProducerController {
	private final SqsService sqsService;

	public ProducerController(SqsService sqsService) {
		this.sqsService = sqsService;
	}

	@Operation(summary = "메시지 SQS Queue 전송", description = "SQS 기본 전송 서비스")
	@PostMapping("/sqs/queue")
	public Result sendToQueue(@RequestParam(name = "message") String message) {
		return sqsService.simpleSendByQueueMessagingTemplate(message);
	}

	@Operation(summary = "메시지 SQS Fifo Queue 전송", description = "SQS Fifo Queue 전송 서비스")
	@PostMapping("/sqs/fifo.queue")
	public Result sendToFifoQueue(@RequestParam(name = "message") String message) {
		return sqsService.sendForFifoQueue(message);
	}
}
