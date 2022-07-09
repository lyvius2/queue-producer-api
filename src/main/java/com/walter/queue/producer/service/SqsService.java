package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;

public interface SqsService {
	Result simpleSendMessageByQueueMessagingTemplate(String message);
	Result sendMessageForFifoQueue(String message);
}
