package com.walter.queue.producer.controller;

import com.walter.queue.producer.response.Result;
import com.walter.queue.producer.service.KafkaService;
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
	private final KafkaService kafkaService;

	public ProducerController(SqsService sqsService, KafkaService kafkaService) {
		this.sqsService = sqsService;
		this.kafkaService = kafkaService;
	}

	@Operation(summary = "메시지 SQS Queue 전송", description = "SQS 기본 전송 서비스")
	@PostMapping("/sqs/queue")
	public Result sendMessageToQueue(@RequestParam(name = "message") String message) {
		return sqsService.simpleSendMessageByQueueMessagingTemplate(message);
	}

	@Operation(summary = "메시지 SQS Fifo Queue 전송", description = "SQS Fifo Queue 전송 서비스")
	@PostMapping("/sqs/fifo.queue")
	public Result sendMessageToFifoQueue(@RequestParam(name = "message") String message) {
		return sqsService.sendMessageForFifoQueue(message);
	}

	@Operation(summary = "메시지 Kafka Topic 전송", description = "Kafka 기본 전송 서비스")
	@PostMapping("/kafka/topic")
	public Result sendMessageToTopic(@RequestParam(name = "message") String message) {
		return kafkaService.sendMessage(message);
	}
}
