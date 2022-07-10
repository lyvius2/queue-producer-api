package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SqsTransferServiceTest {
	@Autowired
	SqsTransferService sqsTransferService;

	private final String message = "Test Message.";
	private final String queue = "test-queue";

	@Test
	@DisplayName("Queue에 메시지 보내기, 성공하면 isError = false, 실패하면 isError = true")
	void sendMessage_Test() {
		final Result result = sqsTransferService.sendMessage(queue, message);
		assertFalse(result.isError());
	}
}