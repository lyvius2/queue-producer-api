package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;

public interface KafkaService {
	Result sendMessage(String message);
}
