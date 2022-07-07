package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SqsServiceImplTest {
	@Autowired
	SqsService sqsService;

	private final String message = "Test Message.";

	@Test
	@DisplayName("Queue에 메시지 보내기 (간단), 성공하면 isError = false, 실패하면 isError = true")
	void simpleSendByQueueMessagingTemplate_Test() {
		final Result result = sqsService.simpleSendByQueueMessagingTemplate(message);
		assertTrue(result.isError());
	}

	@Test
	@DisplayName("FIFO Queue에 메시지 보내기, 성공하면 isError = false, 실패하면 isError = true")
	void sendForFifoQueue() {
		final Result result = sqsService.sendForFifoQueue(message);
		assertFalse(result.isError());
	}
}