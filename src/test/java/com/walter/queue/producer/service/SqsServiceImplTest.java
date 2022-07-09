package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SqsServiceImplTest {
	@Autowired
	SqsService sqsService;

	private final String message = "Test Message.";

	@Test
	@DisplayName("Queue에 메시지 보내기 (간단), 성공하면 isError = false, 실패하면 isError = true")
	void simpleSendByQueueMessagingTemplate_Test() {
		final Result result = sqsService.simpleSendMessageByQueueMessagingTemplate(message);
		assertFalse(result.isError());
	}

	@Test
	@DisplayName("FIFO Queue에 메시지 보내기, 성공하면 isError = false, 실패하면 isError = true")
	void sendForFifoQueue_Test() {
		final Result result = sqsService.sendMessageForFifoQueue(message);
		assertFalse(result.isError());
	}
}