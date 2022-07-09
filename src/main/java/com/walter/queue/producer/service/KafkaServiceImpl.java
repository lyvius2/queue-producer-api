package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

	@Value("${spring.kafka.producer.topic:}")
	private String topic;

	private final KafkaTemplate kafkaTemplate;

	public KafkaServiceImpl(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public Result sendMessage(String message) {
		final ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable ex) {
				ex.printStackTrace();
				throw new RuntimeException();
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Kafka sent message='{}'", message);
			}
		});
		return Result.OK;
	}
}
