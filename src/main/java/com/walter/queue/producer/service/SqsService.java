package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;

public interface SqsService {
	Result simpleSendByQueueMessagingTemplate(String message);
	Result sendForFifoQueue(String message);
}
