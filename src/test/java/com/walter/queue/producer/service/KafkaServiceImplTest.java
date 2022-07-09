package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class KafkaServiceImplTest {
	@Autowired
	KafkaService kafkaService;

	private final String message = "Test Message.";

	@Test
	@DisplayName("Kafka Topic에 메시지 보내기, 성공하면 isError = false, 실패하면 isError = true")
	void sendMessage_Test() {
		final Result result = kafkaService.sendMessage(message);
		assertFalse(result.isError());
	}
}